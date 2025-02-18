# ez-fastfood-orders-ms

## Contextualização
O "**ez-fastfood-order-ms**" é um microsserviço responsável gestão de **pedido**, desenvolvido para uma lanchonete em expansão. Ele adota a arquitetura limpa (clean architecture) e é implantado com AWS EKS, aproveitando os recursos de orquestração de contêineres para otimizar a disponilidade.

## Desenho de arquitetura

**inserir imagem aqui**

## Video de apresentação da arquitetura

**inserir link aqui**

## Estrutura de diretórios do projeto
```
├── src/main/java
│   └── br.com.fiap.ez.fastfood
│       ├── adapters
│       │   ├── in.controller
│       │   │   └── OrderController.java
│       │   └── out
│       │       ├── http
│       │       ├── messaging
│       │       └── repository
│       ├── application
│       │   ├── dto
│       │   └── usecases
│       ├── domain
│       │   ├── model
│       │   └── repository
│       ├── frameworks
│       │   ├── config
│       │   ├── exception
│       ├── infrastructure
│       │   ├── config
│       │   ├── mapper
│       │   ├── persistence
│       │   └── ApiApplication.java
│
├── src/main/resources
├── src/test/java
├── k8s
```
## Modelagem do banco de dados

Durante o processo de segregação do monolito para microsserviços, optamos por manter o banco de dados **PostgreSQL** para o microsserviço **ez-fastfood-order-ms**. Essa decisão foi tomada para **minimizar custos e evitar retrabalho** na migração de um domínio considerado ***core*** para a lanchonete. Além disso, o PostgreSQL, por ser um banco relacional, garante a integridade dos dados e a confiabilidade das operações de pedidos.

![Image](https://github.com/user-attachments/assets/863479e4-9731-421e-925c-4bfe32604248)

OBS...: Foi criada uma instancia RDS Postgres e schema: **ez_fastfood_order** para isolar o banco dos demais microsserviços.

## Pré requisitos

Tecnologias utilizadas:

- Java 17
- Spring Boot 3.3.1
- Hibernate
- RDS PostgreSQL 13.17
- DockerHub (https://hub.docker.com/repository/docker/dasilvathaynara/ez-fastfood-order-ms/general)
- Kubernetes
- OpenApi

## Endpoints

**inserir imagem aqui**

### Como compilar o projeto (caso necessário)
```sh
git clone https://github.com/ThaynaraDaSilva/ez-fastfood-order-ms.git
cd ez-fastfood-order-ms
### Compilação local
mvn clean package -Pdev
## Compilação para publicação com K8s
### Desta forma o 'application.properties' terá parametros que serão injetados com valores no momento de subir os pods.
mvn clean package -Pprd
```

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>
