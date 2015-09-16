# Extractor (WEB) version 1.0b (beta).

## GIN, UMRS 5296

This new version of the extractor make uses of state-of-the-art tools to design modern, RESTful, single-page applications. This stack is composed of Bootstrap, AngularJS and Spring (boot/Data) for the backend.

## Frameworks

### Front-end

#### Twitter Bootstrap
http://getbootstrap.com/

#### AngularJS
https://angularjs.org/

### Back-end

#### Spring Boot
http://projects.spring.io/spring-boot/

#### Spring Data JPA
http://projects.spring.io/spring-data/

## Installation
First install bower, maven and up-to-date java in your local computer. Eclipse (java EE), along with several plugins (spring tools suite, MercurialEclipse) is strongly advised as a development environment.

Installation is quite easy, first you will have to install some front-end dependencies using Bower:
```
bower install
```

Then you can run Maven to package the application:
```
mvn clean package
```

Now you can run the Java application quite easily:
```
cd target
java -jar extractor-1.0.0.jar
```

