# manifold-sample-rest-api

A sample REST API application using [Manifold JSON Schema](http://manifold.systems/docs.html#json-and-json-schema) and
[SparkJava](http://sparkjava.com/).

Notable:
* Demonstrates **API-first** and **single source of truth** solely with JSON Schema. **No code gen step!**, no POJOs, no annotations.
* A **REST API server** with SparkJava is a natural fit with Manifold's JSON support as defined in `UserServer`
* **REST API client** code is made simple as illustrated in the `UserClient` class
* **Rich development experience**. With Manifold's IntelliJ IDEA plugin you can rapidly develop REST APIs with JSON
Schema. Quickly navigate between JSON Schema files and usages in your code, refactor, find usages, code completion,
incremental compilation, etc. All without the burden of a code generation step or maintaining POJOs -- your JSON Schema
API files *are* your API!

## Usage

### IntelliJ IDEA
Manifold is best experienced in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
* Install the Manifold IntelliJ plugin directly from IntelliJ IDEA:

   <kbd>Settings</kbd> ➜ <kbd>Plugins</kbd> ➜ <kbd>Browse repositories</kbd> ➜ search: `Manifold`

* Close and relaunch IDEA
* Open this project: `manifold-sample-rest-api`
* Be sure to setup an SDK for <b>Java 11</b>:

  <kbd>Project Structure</kbd> ➜ <kbd>SDKs</kbd> ➜ <kbd>+</kbd> ➜ <kbd>JDK</kbd>
* Or change the `pom.xml` file to use a JDK of your choosing, Manifold fully supports Java 8 - 11

### Running the `UserServer`
* Run the `restapi.UserServer` class directly with Java
* _or_ load this project in IntelliJ and run the `restapi.UserServer` class
* Launch a browser and go to `http://localhost:4567/Users` to see the JSON results for all Users
* Run the `restapi.UserServerTest`

### Running the `UserClient`
* The `restapi.UserClient` is a simple client to illustrate basic client API code with the `request()` API method.