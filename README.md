# WSO2 API Publisher Client

[![Build Status](https://travis-ci.org/michaelruocco/wso2-api-publisher-client.svg?branch=master)](https://travis-ci.org/michaelruocco/wso2-api-publisher-client)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/wso2-api-publisher-client/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/wso2-api-publisher-client?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ac2f41affcb8428eab0958bbfff36d9f)](https://www.codacy.com/app/michaelruocco/wso2-api-publisher-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/wso2-api-publisher-client&amp;utm_campaign=Badge_Grade)

This library provides an abstraction layer around the WSO2 API Manager Publisher
to try and make it as easy as possible to programatically interact with the publisher
API. It is built and tested around version 1.9.0 of the WSO2 API Manager, you can find the
API documenation [here](https://docs.wso2.com/display/AM190/Publisher+APIs).

## Usage

To use the library from a program you will need to add a dependency to your project. In
gradle you would do this by adding the following to your build.gradle file:

```
dependencies {
    compile group: 'com.github.michaelruocco', name: 'wso2-api-publisher-client', version: '1.0.0'
}
```

## Creating an Instance of the Client

Before making any calls to the publisher you need to create an instance of the
publisher client, all you need to do is pass the host url of your API manager instance
as shown below:

```
String host = "https://localhost:9443";
ApiPublisherClient client = new DefaultApiPublisherClient(host);
```

## Logging in

Once you have an instance of the client you need to log in, you do this by passing
credentials containing a user name and password as shown in the example below:

```
String username = "admin";
String password = "admin";
Credentials credentials = new Credentials(username, password);
client.login(credentials);
```

The login method will throw an exception if the login fails, otherwise it will return
a true boolean value.

## Listing APIs

Once you have logged in you can get a list of all the currently deployed
apis by calling the listAll method, it will return you a list of ApiSummary
objects containing some high level details about each api that is deployed.

```
List<ApiSummary> summaries = client.listAll();
```

You can iterate over the list and get details such as the API name, version,
provider and status.

## Getting a Specific API

It is also possible to get the details for a specific API, to do this you need
to provide the name, version and provider of the API using the SelectApiParams interface.
There is a default implementation that you can use as shown in the example below.
The ApiSummary class returned when listing all APIs also implements this interface
so it is possible to use those to get the full API details, this is also shown below.

Using DefaultSelectApiParams:

```
SelectApiParams params = new DefaultSelectApiParams()
params.setName("api-name");
params.setVersion("v1");
params.setProvider("api-provider")
Api api = client.getApi(params);
```

Using ApiSummary:

```
List<ApiSummary> summaries = client.listAll();
for (ApiSummary summary : summaries) {
    Api api = client.getApi(summary);
    ...
}
```

## Creating an API

To create an api you need to create an instance of DefaultAddApiParams,
there are various parameters that need to be set, you can find the details
by reading the [API manager documentation](https://docs.wso2.com/display/AM190/Publisher+APIs#PublisherAPIs-AddAPI).

The addApi call will return true if successful and will throw an exception if ApiPublisherException
if an error occurs.

An example of how you might create an API is shown below:

```
DefaultAddApiParams params = new DefaultAddApiParams();
params.setName("rest-product");
params.setContext("/product");
params.setVersion("v1");
params.setDescription("Rest Product API");
params.setTags("prod", "rest-product", "product");
params.setSwagger("{\"consumes\":[\"application/json\"],\"info\":{\"description\":\"rest-taxonomy : (build 20170103134850)\",\"title\":\"rest-taxonomy\",\"version\":\"v1\"},\"paths\":{\"/z20170103134850\":{\"get\":{\"responses\":{\"200\":{}},\"x-auth-type\":\"Application\",\"x-throttling-tier\":\"Unlimited\"}},\"/{catalog}/paged*\":{\"get\":{\"parameters\":[{\"description\":\"Catalog id (for ex. GroupMasterProductCatalog)\",\"in\":\"path\",\"name\":\"catalog\",\"required\":true,\"type\":\"string\"},{\"in\":\"query\",\"name\":\"limit\",\"required\":true,\"type\":\"integer\"},{\"in\":\"query\",\"name\":\"offset\",\"required\":true,\"type\":\"integer\"}],\"responses\":{\"200\":{}},\"x-auth-type\":\"Application\",\"x-throttling-tier\":\"Unlimited\"}}},\"produces\":[\"application/json\"],\"schemes\":[\"https\"],\"swagger\":\"2.0\"}");
params.setEndpointConfig("{\"production_endpoints\": {\"url\":\"http://ws-uat1.dev.tppim.co.uk/pimwebservices/services/rest-taxonomy-entity-v1/taxonomy\", \"config\": null},\"endpoint_type\":\"http\"}");
client.addApi(params);
```

## Checking that an API Exists by Name

You can also check whether or not an api exists simply by passing the name of the API as shown below:

```
String name = "rest-product";
boolean exists = client.apiExists(name);
```

## Updating an API

In order to update an API you will need to get the API details as described above,
those API details can then be used to create an instance of DefaultUpdateApiParams.
Once you have an instance you can override the properties that you want to update
and set them with a new value. Additionally, because of a bug with the publisher API
you will also always have to provide an up to date swagger definition of the API, even
if you are not changing the definition itself. An example is shown below:

```
// first you need to get the existing API details
SelectApiParams params = new DefaultSelectApiParams()
params.setName("api-name");
params.setVersion("v1");
params.setProvider("api-provider")
Api api = client.getApi(params);

// you can use the API details and a converter to create some update parameters
ApiToUpdateApiParamsConverter converter = new ApiToUpdateApiParamsConverter();
DefaultUpdateApiParams updateParams = converter.convert(api);
updateParams.setDescription(updatedDescription);
// swagger always needs to be set even if not updating due to a bug
// with the update API
updateParams.setSwagger(swagger);

client.updateApi(updateParams);
```

## Missing functionality

Functionality still needs to be added to allow the following:

* Updating the status of an API

## Running the tests

This project is covered by both unit tests and integration tests. The
integration tests make use of docker, so you will need to have a docker
daemon running on your machine for them to work.

The integration tests also take around 2 or 3 minutes to run, this is
why they have been split out from the unit tests so each set of tests
can be run independently.

### Running the unit tests

To run just the unit tests you can run the command:

```
gradlew clean build -x integrationTest
```

### Running the integration tests

The integration tests make use of docker, so you will need to have a docker
daemon running on your machine for them to work.

To run just the integration tests you can run the command:

```
gradlew clean build -x test
```

### Running all the tests

Finally to run all the integration tests you can run the command:

```
gradlew clean build
```

### Running the integration tests from IDE

If you are trying to run the integration tests directly in your IDE rather
than using the gradle tasks provided then you will need to set the JVM
argument to point at the truststore provided in the project at:

```
{projectDir}/trustore/cacerts
```

For example:

```
-Djavax.net.ssl.trustStore=/Users/michaelruocco/git/github/wso2-api-publisher-client/truststore/cacerts
```

## Checking dependencies

You can check the current dependencies used by the project to see whether
or not they are currently up to date by running the following command:

```
gradlew dependencyUpdates
```