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
3. Crie alguns dados para o sistema: [http://www.lvh.me:8080/icardapio/createMasterData](http:///www.lvh.me:8080/icardapio/createMasterData)
4. Acesse em [http:///www.lvh.me:8080/icardapio](http:///www.lvh.me:8080/icardapio)
5. Login: usuário: admin / senha: admin

obs: o domínio lvh.me é direciona sempre para localhost, mesmo quando usando um subdomínio, de modo que não precisamos criar um entrada em /etc/hosts para cada subdomínio criado durante desenvolvimento.


## Eclipse / SpringSource STS
1. Execute de dentro do diretório do projeto: ```mvn eclipse:eclipse -wtp.version="2.0"```
2. Importe o projeto no eclipse (Import existing project)
