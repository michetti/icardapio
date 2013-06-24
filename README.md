# iCardapio

Aplicação para criação de cardápios eletrônicos para restaurantes.


## Pré Requisitos
* Java >= 1.6
* MySQL >= 5.5
* Maven
* Git


## Pré Configuração MySQL

No console do MySQL:

1. Criar o database: ```create database icardapio;```
2. Dar permissão para o usuário: ```grant all privileges on icardapio.*
   to icardapio@localhost identified by 'icardapio';```
3. Flush: ```flush privileges;```


## Executando o projeto

1. Clone (ou faça um fork e clone): ```git clone https://github.com/michetti/icardapio.git```
2. De dentro do diretório do projeto: ```mvn tomcat:run```


## Eclipse / SpringSource STS
1. Execute de dentro do diretório do projeto: ```mvn eclipse:eclipse -wtp.version="2.0"```
2. Importe o projeto no eclipse (Import existing project)
