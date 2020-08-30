# Projeto LinkedInZup
![ZUP BootCamp](https://d335luupugsy2.cloudfront.net/cms/files/83508/1595957285/$59eegztemcq)

## Projeto Proposto
Sua missão é re-construir a API (uma versão **MVP**, claro) que serve o **Linkedin**, o Instagram OU o Youtube.
Neste cenário, estamos colocando como se tivéssemos criando uma rede social do zero e muitas funcionalidades podem ser 
escritas e desenvolvidas. Com isso em mente, queremos entender a sua **priorização** sobre qual **funcionalidade** é mais 
relevante no início.
Para este desafio você utilizará a **linguagem Java** e o framework **Spring** como tecnologias que formam a base do
 seu código.
 
 ## Solução Proposta
 
 Eu escolhi a rede social [LinkedIn](https://www.linkedin.com/in/carloseduardojr/), e a funcionalidade para ser
  meu **MVP**  foi de adicionar uma vaga, inspirada numa tela do próprio [LinkedIn](https://www.linkedin.com/in/carloseduardojr/).
 ![linkedIn Vagas](https://i.ibb.co/0KT80D5/Linkend-In-Zup.png)
 
 ### Desenhar o Problema
Com a rede social escolhida e a funcionalidade principal decidida, o meu próximo passo foi fazer um diagrama UML de classe
para visualizar qual rumo o projeto iria tomar.
![linkedInZup UML](https://i.ibb.co/tBvxHrp/UMLLinked-In-Zup.png)

### Parte Técnica

#### Arquitetura Hexagonal
Arquitetura hexagonal, consiste em dividir uma aplicação em camadas de acordo com as suas responsabilidades e 
enfatizar uma camada em especial, onde ficará a lógica principal da aplicação, a camada de domínio ou ___domain___
(do termo original).

![Arquitetura Hexagonal](https://miro.medium.com/max/638/1*EJUMMag-_MvUP1GuDRJHEA.jpeg)

#### Arquitetura do Projeto
No Projeto usei com base a arquitetura hexagonal, buscando sempre separar a comunicação que a API tem com o mundo 
externo com as regras de negócios, casos de usos e entidades, arquitetado da seguinte forma:
* API:
    * Nessa camada encontramos os ___controllers___ responsável por lidar com as chamadas ___HTTPs___ e toda entrada
    (___Requests___) e saida(___Response___) de dados é controlada por ___DTOs___, um padrão de projeto que basicamente
     é uma classe com atributos simples, que usamos para otimizar a comunicação entre o cliente e o servidor.
* Domain:
    * A camada de domínio é a principal camada da aplicação, nela encontramos o ___model___ que define as entidades, o 
    ___service___ com as regras de negócio e o ___repository___ que não faz acesso direto ao banco, sendo apenas uma 
    ___interface___ que serve como ___adapter___ para persistência de dados.
* Config:
    * Camada de configuração do projeto, podemos declarar bibliotecas de terceiros que não faz parte do ambiente
    ___Spring___, definindo ___@Beans___ para que o nosso ___Framework___ possa gerenciar-lo também.


![Estrutura Hexagonal](https://i.ibb.co/Gc7gS1s/carbon-1.png)

#### Executando projeto local
Para criação dessa aplicação foi usado JDK 11 tendo com principais dependências:
* ___spring-boot-starter-web___
* ___spring-boot-starter-data-jpa___
* ___mysql-connector-java___
* ___lombok___
* ___validation-api___
* ___modelmapper___

Para compilar e gerenciar os pacotes de ___build___ e dependências estou usando o Maven que é baseado no conceito de ___project 
object model___ (POM).

Após Clonar o projeto, usaremos o comando: 
```sh
 $ mvn clean install
```
Para subir a nossa imagem que é formada por um ___docker-compose___ com um ___container___ para aplicação e um ___container___ para
o banco de dados, usaremos o comando:
```sh
 $ docker-compose up --build --force-recreate
```

#### Documentação api

Documentação da API publicada no [Postman](https://documenter.getpostman.com/view/8166550/TVCcXpSt) 

 

