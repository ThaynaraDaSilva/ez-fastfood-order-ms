# LocalStack Commands

## Iniciar localstack 
localstack start

## Configurar creds da AWS
aws configure
set AWS_ACCESS_KEY_ID=test
set AWS_SECRET_ACCESS_KEY=test

### Criar dead letter Queue (DLQ)
// onde mensagens são enviadas após excederem o limite de tentativas
awslocal --endpoint-url=https://localhost.localstack.cloud:4566 sqs create-queue --queue-name order-payment-dlq --region us-east-1

### Criar fila de payments
awslocal --endpoint-url=https://localhost.localstack.cloud:4566 sqs create-queue --queue-name order-payment-queue --region us-east-1 --attributes "C:\THAYNARA_DEV\workspaces\ez-fastfood-order-ms\src\main\resources\attributes.json"

// comando que funcionou para criar fila
awslocal --endpoint-url=https://localhost.localstack.cloud:4566 sqs create-queue --queue-name order-payment-queue --region us-east-1 --attributes file://C:\THAYNARA_DEV\workspaces\ez-fastfood-order-ms\src\main\resources\attributes.json
