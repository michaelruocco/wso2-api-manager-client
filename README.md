# WSO2 API Manager Client

[![Build Status](https://travis-ci.org/michaelruocco/wso2-api-publisher-client.svg?branch=master)](https://travis-ci.org/michaelruocco/wso2-api-publisher-client)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/wso2-api-publisher-client/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/wso2-api-publisher-client?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ac2f41affcb8428eab0958bbfff36d9f)](https://www.codacy.com/app/michaelruocco/wso2-api-publisher-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/wso2-api-publisher-client&amp;utm_campaign=Badge_Grade)

This library provides an abstraction layer around the WSO2 API Manager Publisher and Store
APIs to try and make it as easy as possible to programatically interact with them. It is built
and tested around version 1.9.0 of the WSO2 API Manager, you can find the
Publisher API documentation [here](https://docs.wso2.com/display/AM190/Publisher+APIs) and the
Store API documentation [here](https://docs.wso2.com/display/AM190/Store+APIs).

## Usage

To use the library from a program you will need to add a dependency to your project. In
gradle you would do this by adding the following to your build.gradle file:

```
dependencies {
    compile group: 'com.github.michaelruocco', name: 'wso2-api-manager-client', version: '1.0.0'
}
```

## Creating an Instance of the Publisher Client

Before making any calls to the publisher you need to create an instance of the
publisher client, all you need to do is pass the host url of your API manager instance
as shown below:

```
String host = "https://localhost:9443";
ApiPublisherClient publisherClient = new DefaultApiPublisherClient(host);
```

## Creating an Instance of the Store Client

Before making any calls to the store you need to create an instance of the
store client, all you need to do is pass the host url of your API manager instance
as shown below:

```
String host = "https://localhost:9443";
ApiStoreCient storeClient = new DefaultApiStoreClient(host);
```

The login method will throw an exception if the login fails, otherwise it will return
a true boolean value, it will behave in exactly the same way for the both the publisher
and store clients.

## Logging in

Once you have an instance of either client you need to log in, you do this by passing
credentials containing a user name and password as shown in the example below:

```
String username = "admin";
String password = "admin";
Credentials credentials = new Credentials(username, password);
publisherClient.login(credentials);
storeClient.login(credentials);
```

## Logging out

Once you have completed whatever actions you are looking to perform, you need to log out
you do this simply by calling the logout method.

```
publisherClient.logout();
storeClient.logout();
```

The logout method will throw an exception if the login fails, otherwise it will return
a true boolean value, again, it will behave in exactly the same way for the both the publisher
and store clients.

## Listing APIs (Publisher)

Once you have logged in you can get a list of all the currently deployed
apis by calling the listAll method, it will return you a list of ApiSummary
objects containing some high level details about each api that is deployed.

```
List<ApiSummary> summaries = publisherClient.listAll();
```

You can iterate over the list and get details such as the API name, version,
provider and status.

## Getting a Specific API (Publisher)

It is also possible to get the details for a specific API, to do this you need
to provide the name, version and provider of the API using the SelectApiParams interface.
There is a default implementation that you can use as shown in the example below.
The ApiSummary class returned when listing all APIs also implements this interface
so it is possible to use those to get the full API details, this is also shown below.

Using DefaultSelectApiParams:

```
SelectApiParams params = new DefaultSelectApiParams()
params.setApiName("api-name");
params.setApiVersion("v1");
params.setProvider("api-provider")
Api api = publisherClient.getApi(params);
```

Using ApiSummary:

```
List<ApiSummary> summaries = client.listAll();
for (ApiSummary summary : summaries) {
    Api api = publisherClient.getApi(summary);
    ...
}
```

## Creating an API (Publisher)

To create an api you need to create an instance of DefaultAddApiParams,
there are various parameters that need to be set, you can find the details
by reading the [API manager documentation](https://docs.wso2.com/display/AM190/Publisher+APIs#PublisherAPIs-AddAPI).

The addApi call will return true if successful and will throw an ApiPublisherException
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
publisherClient.addApi(params);
```

## Checking that an API Exists by Name (Publisher)

You can also check whether or not an api exists simply by passing the name of the API as shown below:

```
String name = "rest-product";
boolean exists = publisherClient.apiExists(name);
```

## Updating an API (Publisher)

In order to update an API you will need to get the API details as described above,
those API details can then be used to create an instance of DefaultUpdateApiParams.
Once you have an instance you can override the properties that you want to update
and set them with a new value. Additionally, because of a bug with the publisher API
you will also always have to provide an up to date swagger definition of the API, even
if you are not changing the definition itself. An example is shown below:

```
// first you need to get the existing API details
DefaultSelectApiParams params = new DefaultSelectApiParams()
params.setName("api-name");
params.setVersion("v1");
params.setProvider("api-provider")
Api api = publisherClient.getApi(params);

// you can use the API details and a converter to create some update parameters
ApiToUpdateApiParamsConverter converter = new ApiToUpdateApiParamsConverter();
DefaultUpdateApiParams updateParams = converter.convert(api);
updateParams.setDescription(updatedDescription);
// swagger always needs to be set even if not updating due to a bug
// with the update API
updateParams.setSwagger(swagger);

publisherClient.updateApi(updateParams);
```

## Setting API Status (Publisher)

In order to set the status of an API you will need to get the API details as described above,
those API details can then be used to create an instance of DefaultSetStatusParams.
Once you have an instance you will need to set the status using the ApiStatus enum,
you can also set whether to publish to the gateway and whether resubscription is required,
the default values are true and false respectively. An example is shown below:

```
// first you need to get the existing API details
DefaultSelectApiParams params = new DefaultSelectApiParams()
params.setName("api-name");
params.setVersion("v1");
params.setProvider("api-provider")
Api api = publisherClient.getApi(params);

// you can use the API details and a converter to create some update parameters
ApiToSetStatusParamsConverter converter = new ApiToSetStatusParamsConverter();
DefaultSetStatusParams setStatusParams = converter.convert(api);
setStatusParams.setStatus(ApiStatus.PUBLISHED);

publisherClient.setStatus(setStatusParams);
```

## Listing Applications (Store)

Once you have logged in you can get a list of all the currently created
applications by calling the listAllApplications method, it will return
you a list of ApiApplication objects containing some high level details
about each application that is deployed.

```
List<ApiApplication> applications = storeClient.listAllApplications();
```

You can iterate over the list and get details such as the API name, version,
provider and status.

## Creating an Application (Store)

To create an application you need to create an instance of AddApplicationParams,
there are various parameters that need to be set, you can find the details
by reading the [API manager documentation](https://docs.wso2.com/display/AM190/Store+APIs#StoreAPIs-AddanApplication).

The addApplication call will return true if successful and will throw an ApiStoreException
if an error occurs.

An example of how you might create an application is shown below:

```
DefaultAddApplicationParams params = new DefaultAddApplicationParams();
params.setApplicationName("my-application")
storeClient.addApplication(params);
```

## Removing an Application (Store)

To remove an application you need to pass the name of the application.
You can find the details by reading the [API manager documentation](https://docs.wso2.com/display/AM190/Store+APIs#StoreAPIs-RemoveanApplication).

The removeApplication call will return true if successful and will throw an ApiStoreException
if an error occurs.

An example of how you might create an application is shown below:

```
storeClient.removeApplication("my-application");
```

## Adding a Subscription (Store)

To add a subscription to an API for an application you need to create an instance of
AddSubscriptionParams, there are various parameters that need to be set, you can find
the details by reading the [API manager documentation](https://docs.wso2.com/display/AM190/Store+APIs#StoreAPIs-AddaSubscription).

The addSubscription call will return true if successful and will throw an ApiStoreException
if an error occurs.

An example of how you might create a subscription is shown below:

```
DefaultAddSubscriptionParams params = new DefaultAddSubscriptionParams();
params.setApplicationName("my-application");
params.setApiName("my-api");
params.setApiVersion("v1");
params.setProvider("admin");

storeClient.addSubscription(params);
```

## Removing a Subscription (Store)

To remove a subscription to an API for an application you need to create an instance of
RemoveSubscriptionParams, there are various parameters that need to be set, you can find
the details by reading the [API manager documentation](https://docs.wso2.com/display/AM190/Store+APIs#StoreAPIs-RemoveanApplication).

The removeSubscription call will return true if successful and will throw an ApiStoreException
if an error occurs.

An example of how you might remove a subscription is shown below:

```
DefaultRemoveSubscriptionParams params = new DefaultRemoveSubscriptionParams();
params.setApplicationName("my-application");
params.setApiName("my-api");
params.setApiVersion("v1");
params.setProvider("admin");

storeClient.removeSubscription(params);
```

## Get a Subscriptions by API (Store)

To get the subscriptions to an API you need to create an instance of SelectApiParams,
there are various parameters that need to be set, you can find the details by reading
the [API manager documentation](https://docs.wso2.com/display/AM190/Store+APIs#StoreAPIs-ListSubscriptionsbyAPI).

The getSubscriptionsByAPI call will return a list of subscriptions if successful and will throw
an ApiStoreException if an error occurs.

An example of how you might get subscriptions for an API is shown below:

```
DefaultSelectApiParams params = new DefaultSelectApiParams();
params.setApiName("my-api");
params.setApiVersion("v1");
params.setProvider("admin");

List<ApiSubscription> subscriptions = storeClient.getSubscriptionsByApi(params);
```

## Generating an Application Key (Store)

To generate an application key you need to create an instance of GenerateApplicationKeyParams,
there are various parameters that need to be set, you can find the details by reading
the [API manager documentation](https://docs.wso2.com/display/AM190/Store+APIs#StoreAPIs-GenerateanApplicationKey).

The generateApplicationKey call will return an ApplicationKey if successful and will throw
an ApiStoreException if an error occurs.

An example of how you might generate an application key is shown below:

```
DefaultGenerateApplicationKeyParams params = new DefaultGenerateApplicationKeyParams();
params.setApplicationName("my-application");

ApplicationKey key = storeClient.generateApplicationKey(params);
```

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
-Djavax.net.ssl.trustStore=/Users/michaelruocco/git/github/wso2-api-manager-client/truststore/cacerts
```

## Checking dependencies

You can check the current dependencies used by the project to see whether
or not they are currently up to date by running the following command:

```
gradlew dependencyUpdates
```