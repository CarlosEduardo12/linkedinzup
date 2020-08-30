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
 
 Eu escolhi a rede social [LinkedIn](https://www.linkedin.com/in/carloseduardojr/), e a funcinalidade para ser
  meu **MVP**  foi de adiconar uma vaga, inspirada em uma tela do próprio [LinkedIn](https://www.linkedin.com/in/carloseduardojr/).
 ![linkedIn Vagas](https://i.ibb.co/0KT80D5/Linkend-In-Zup.png)
 
 ### Desenhar o Problema
Com a rede social escolhida e a funcionalidade principal decidida, meu próximo passo foi fazer um diagrama UML de classe
para vizualizar qual rumo o projeto iria tomar.
![linkedInZup UML](https://i.ibb.co/tBvxHrp/UMLLinked-In-Zup.png)

### Parte Técnica

#### Arquitetura Hexagonal
Arquitetura hexagonal, consiste em dividir uma aplicação em camadas de acordo com suas responsabilidades e 
enfatizar uma camada em especial, onde ficará a lógica principal da aplicação, a camada de domínio ou domain
(do termo original).

![Arquitetura Hexagonal](https://miro.medium.com/max/638/1*EJUMMag-_MvUP1GuDRJHEA.jpeg)

#### Arquitetura do Projeto
No Projeto usei com base a arquitetura hexagonal, buscando sempre separar a comunicação que a API tem com o mundo 
externo com as regras de negócios, casos de usos e entidades, arquitetado da seguinte forma:
* API:
    * Nessa camada encontramos os controllers responsável por lidar com as chamadas HTTPs e toda entrada(Requests) e
     saida(Response) de dados é controlada por DTOs, um padrão de projeto que basicamente é uma classe com atributos simples,
     que usamos para otimizar a comunicação entre o client e o servidor.
* Domain:
    * A camada de domínio é a principal camada da aplicação, nela encontramos o model que define as entidades, o 
    service com as regras de negócio e o repository que não faz acesso direto ao banco, sendo apenas uma ‘interface’
     que serve como adapter para persistência.
* Config:
    * Camada de configuração do projeto, podemos declarar bibliotecas de terceiros que não faz parte do ambiente
    Spring, definindo @Beans para que o nosso Framework possa gerenciar-lo também.


![Estrutura Hexagonal](https://i.ibb.co/TYg6S63/carbon.png)

#### Como rodar
docker-compose basta clonar, buildar o projeto com mvn clean install e docker-compose up --build --force-recreate
#### Documentação api

 

