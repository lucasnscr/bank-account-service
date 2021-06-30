# bank-account-service

##Project description
🚀 Project that simulates the creation of a digital account and transfer between the created accounts.


## Installation ##

It is necessary to install some items:
- Docker
- Java 11
- Maven

### Commands needed to run Redis ###

After installing Docker, open the terminal in the root directory of the project, which has already configured a docker-compose. This file serves to upload a Redis container, the database used to store the data. The command that needs to be run in the terminal is as follows: docker-compose up -d

#### RabbitMq

To have transactional control in the transfer layer, a Message Broker, RabbitMQ, was used. to use Rabbit, you need to run one more command. Open the terminal and run the following command:
docker run --rm -it --hostname localhost -p 15672:15672 -p 5672:5672 rabbitmq:3-management

### Features

- [x] Customer registration
- [x] Customer query
- [x] Customer registration per account
- [x] Transfer query per customer
- [x] Transfer of up to R$1000.00 between two registered customers

### Collections

After uploading the application, use the Postman collections provided to make the requests. https://www.postman.com/collections/91fb55fa953240feab88

### Dockerfile

A dockerfile is being made available to run the application in a container environment. Using a plugin provided by Spotify, where once inserted the plugin and its dockerfile, via maven you run the command __ mvn package __ , this command will generate your image.

After running __ mvn package __ , the next command that will be run is __ docker images __ which will list your docker images.

Now you will need to run the command __ docker run -p 8000:8000 lucasnscr/spring-docker-spotify:1.0.0-SNAPSHOT __ done that the application is already running in the environment with the docker.



Note: It is necessary to run the commands to expose Redis and RabbitMQ, otherwise the application will not be able to connect.


Note: If you cannot connect via docker, you can run the service as follows. With the terminal, enter the project's root folder and execute the command __ mvn clean install -U __ after this command, enter the project's target directory, in this folder is the .jar of the service, execute the following command __ java -jar bank -count-service.jar __

### Technologies

The following technologies were used to carry out the project:
- Java 11
- Maven
- SpringBoot
- Spring Cloud Stream
- Rabbit
- Redis
- Swagger
- Junit
- Docker
- Postman
- Lombok
- Docker Maven Plugin
