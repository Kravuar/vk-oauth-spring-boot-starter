# vk-oauth-spring-boot-starter

# Spring Boot VK OAuth Starter

The Spring Boot VK OAuth Starter is a simple library that provides support for integrating VK (Vkontakte) OAuth authentication into your Spring Boot applications. This starter package includes an adapter for the VK API and offers default application properties configuration for VK registration and VK provider.

## Installation

To include the Spring Boot VK OAuth Starter in your project using JitPack, follow these steps:

1. Add the JitPack repository to your `pom.xml` file (for Maven) or `build.gradle` file (for Gradle):

   Maven:

   ```xml
   <repositories>
       <repository>
           <id>jitpack.io</id>
           <url>https://jitpack.io</url>
       </repository>
   </repositories>
   ```

   Gradle:

   ```groovy
   repositories {
       maven { url 'https://jitpack.io' }
   }
   ```

2. Add the Spring Boot VK OAuth Starter dependency to your `pom.xml` file (for Maven) or `build.gradle` file (for Gradle):

   Maven:

   ```xml
   <dependency>
	    <groupId>com.github.Kravuar</groupId>
	    <artifactId>vk-oauth-spring-boot-starter</artifactId>
	    <version>Tag</version>
	</dependency>
   ```

   Gradle:

   ```groovy
   dependencies {
	        implementation 'com.github.Kravuar:vk-oauth-spring-boot-starter:Tag'
	 }
   ```

## Configuration

To configure vk oauth support of the Spring Boot VK OAuth Starter you need to configure the VK registration and provider in your application.properties or application.yml file:
 - VK Registration properties (spring.security.oauth2.client.registration.vk):
   - `client-id`: Specify the VK application client ID.
   - `client-secret`: Specify the VK application client secret.
   - `redirect-uri`: Redirect uri for the oauth flow. By default its the common oauth redirect uri pattern: "{baseUrl}/{action}/oauth2/code/{registrationId}".
   - `scope` (optional): Scopes to request from user. By default its empty.
 
 - VK Provider properties (spring.security.oauth2.client.provider.vk):
   - `user-info-uri` (optional): Define a custom user info URI for retrieving user information from VK. If not specified, the default URI for VK API version 5.131 will be used.

And that's it, now VK OAuth authentication should be integrated into your Spring Boot application.
