# WSO2 API Publisher Client

[![Build Status](https://travis-ci.org/michaelruocco/wso2-api-publisher-client.svg?branch=master)](https://travis-ci.org/michaelruocco/wso2-api-publisher-client)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/wso2-api-publisher-client/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/wso2-api-publisher-client?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ac2f41affcb8428eab0958bbfff36d9f)](https://www.codacy.com/app/michaelruocco/wso2-api-publisher-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/wso2-api-publisher-client&amp;utm_campaign=Badge_Grade)

This library provides an abstraction layer around the WSO2 API Manager Publisher
to try and make it as easy as possible to programatically interact with the publisher
API.

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

## Listing Apis

Once you have logged in you can get a list of all the currently deployed
apis by calling the listAll method, it will return you a list of ApiSummary
objects containing some high leve details about each api that is deployed.

```
List<ApiSummary> summaries = client.listAll();
```

You can iterate over the list and get details such as the API name, version,
provider and status.

## Getting a Specific API

It is also possible to get the details for a specific API, to do this you need
to provide the name, version and provider of the API using the GetApiParams interface.
There is a default implementation that you can use as shown in the example below.
The ApiSummary class returned when listing all APIs also implements this interface
so it is possible to use those to get the full API details, this is also shown below.

Using DefaultGetApiParams:

```
GetApiParams params = new DefaultGetApiParamsBuilder()
    .setName("api-name");
    .setVersion("v1");
    .setProvider("api-provider")
    .build();
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

## Missing functionality

Functionality still needs to be added to allow the following:

* Creation of new APIs
* Updating of existing APIs
* Checking that an API exists by Name

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