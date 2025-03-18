# Demonstrate Intellij Issue loading a Maven Multi-Module project with Jax-rs stub-first Openapi Generation

There is an issue when you try to load a multi-module maven spring boot project that has an openapi contract generation module.

## Steps to reproduce

* `File -> Open -> multi-module-spring-boot-app-test/pom.xml`
* Open as project
* Views -> Maven -> testapp-parent -> Lifecycle -> Run clean, then install
* Run [DemoApplication.xml](.idea/runConfigurations/DemoApplication.xml)
* Spring boot app will work
* Run `GET http://localhost:8080/testapp/hello` and it works just fine.

```http request
GET http://localhost:8080/testapp/hello

HTTP/1.1 200 
Content-Type: text/plain
Content-Length: 13
Date: Tue, 18 Mar 2025 18:21:11 GMT

Hello, World!
```

* Now, update HelloResource.java
```
return "Hello, World updated!";
```

Stop the service, then run it again.

Actual result:

**Same result as last time - the update was ignored.**

```http request
GET http://localhost:8080/testapp/hello

HTTP/1.1 200 
Content-Type: text/plain
Content-Length: 13
Date: Tue, 18 Mar 2025 18:21:11 GMT

Hello, World!
```

Expected result:

`Hello, World updated!`