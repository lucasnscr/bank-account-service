# bank-account-service

##Descrição do Projeto
🚀 Projeto que simula a criação de uma conta digital e transferência entre as contas criadas.



## Instalação ##

É necessário instalar alguns itens: 
- Docker 
- Java 11
- Maven

### Comandos necessários para rodar o Redis ###

Após instalar o Docker, abra o terminal no diretório raiz do projeto, que já vai configurado um docker-compose. Esse arquivo serve subir um container de Redis, banco utilizado para armazenar os dados. O comando para que precisa ser executado no terminal é o seguinte: docker-compose	up	-d 

#### RabbitMq

Para ter um controle transacional na camada de transferências, foi feita a utilização de um Message Broker, o RabbitMQ. para a utilização do Rabbit, é preciso rodar mais um comando. Abra o terminal e rode o seguinte comando: 
docker run --rm -it --hostname localhost -p 15672:15672 -p 5672:5672 rabbitmq:3-management

### Features

- [x] Cadastro de cliente
- [x] Consulta de cliente
- [x] Cadastro de cliente por conta
- [x] Consulta de Transferência por cliente
- [x] Transferência de até R$ 1000,00 entre dois clientes cadastrados

### Collections

Após subir a aplicação, utilize as collections do Postman disponibilizada para realizar as requisições. https://www.postman.com/collections/91fb55fa953240feab88

### Dockerfile

Está sendo disponibilizado um dockerfile para a execução da aplicação em ambiente de container

### Tecnologias

Para a realização do projeto foram utilizada as seguintes tecnologias: 
- Java 11
- Maven
- SpringBoot
- Spring Cloud Stream
- Rabbit
- Redis
- Swagger
- Junit
- ArchUnit
- Docker
- Postman
- Lombok