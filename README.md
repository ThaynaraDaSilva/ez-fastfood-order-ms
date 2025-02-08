# ez-fastfood-orders-ms

## Contextualização

## Desenho de arquitetura

## Apresentação da arquitetura: 

## Estrutura de diretórios do projeto
```
br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   │   └── controller
│   └── out
│       └── repository
├── application
│   ├── usecases
│   └── dto
├── domain
│   ├── model
│   └── repository
├── frameworks
│   ├── config
│   └── exception
├── infrastructure
│   ├── config
│   ├── mapper
│   └── persistence
├── APIApplication.java
k8s/                      # Arquivos de manifesto Kubernetes
postman-jmeter/            # Collection para testes no Postman e Apache JMeter
```

## Modelagem do banco de dados

## Pré requisitos:

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.3.1
- Hibernate
- RDS PostgreSQL 13.17
- DockerHub (https://hub.docker.com/repository/docker/dasilvathaynara/ez-fast-food-api/general)
- Kubernetes
- OpenApi

## Endpoints

### Como compilar o projeto (caso necessário)
```sh
git clone https://github.com/ThaynaraDaSilva/ez-fast-food-clean-architecture.git
cd ez-fast-food-clean-architecture
### Compilação local
mvn clean package -Pdev
## Compilação para publicação com K8s
### Desta forma o 'application.properties' terá parametros que serão injetados com valores no momento de subir os pods.
mvn clean package -Pprd
```
### Publicar imagem

docker build -t dasilvathaynara/ez-fastfood-order-ms:latest .
docker push dasilvathaynara/ez-fastfood-order-ms:latest

## CONFIG ALTERADA PARA CONSEGUIR VER OS PODS:
aws eks update-cluster-config --name ez-fastfood-cluster-dev --region us-east-1 --resources-vpc-config endpointPublicAccess=true


## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>
