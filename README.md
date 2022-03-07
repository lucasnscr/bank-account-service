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

Está sendo disponibilizado um dockerfile para a execução da aplicação em ambiente de container. Utilizando um plugin disponibilizado pela Spotify, onde uma vez inserido o plugin e o seu arquivo dockerfile, via maven você executa o comando __ mvn package __ ,esse comando irá gerar sua imagem. 

Após a execução do  __ mvn package __ , o próximo comando que será executado é o  __ docker images __  que irá listar as suas imagens docker.

Agora precisara rodar o comando  __ docker run -p 8000:8000 lucasnscr/spring-docker-spotify:1.0.0-SNAPSHOT __  feito isso a aplicação já está rodando no ambiente com o docker.



Obs: É preciso executar os compandos para exposição do Redis e do RabbitMQ, se não a aplicação não irá conseguir conectar.


Obs: Caso não consiga conectar via docker, voce poderá executar o serviço da seguinte forma. Com o terminal, entra na pasta raiz do projeto e execute o comando __  mvn clean install -U  __  após esse comando, entra no diretório target do projeto, nessa pasta está o .jar do serviço, execute o seguinte comando __ java -jar bank-count-service.jar __

### Tecnologias

Para a realização do projeto foram utilizada as seguintes tecnologias: 
- Java 11
- Maven
- SpringBoot
	@@ -62,4 +61,4 @@ Para a realização do projeto foram utilizada as seguintes tecnologias:
- Docker
- Postman
- Lombok
- Docker Maven Plugin
