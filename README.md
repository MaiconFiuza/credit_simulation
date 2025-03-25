# credit_simulation
Teste Prático de Engenharia Backend - Simulador de Crédito

## Documentação do projeto :
A documentação do projeto foi feita de duas formas, em outro repositório dedicado apenas a documentação e pelo código utilizando o swagger.

### Link do repositório de docs:
 [Docs](https://github.com/MaiconFiuza/credit_simulation_docs)

### Documentação pelo Swagger
Para acessar a documentação pelo swagger é só rodar o projeto e acessar o link abaixo:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Para executar a aplicação pelo Docker:

### 1. Fazer o build dos containeres da aplicação:
Executar o seguinte comando:
    
    docker-compose up --build

O comando acima gerará os conteineres de aplicação.

### 2. Executar a aplicação através dos containeres criados:
Executar o seguinte comando para inicializar os containeres da aplicação, na raíz do projeto (onde se encontra o arquivo docker-compose.yml):

    docker-compose up

### 3. Acessar a aplicação
A aplicação estará disponível na seguinte URL:

    http://localhost:8080/


## Cobertura de testes
Para cobertura de testes foi utilizado a ferramenta [Jacoco](https://www.eclemma.org/jacoco/)

Rode os comandos: 

> ./gradlew jacocoTestCoverageVerification
> ./gradlew jacocoTestReport

O relatório de cobertura pode ser encontrado dentro da pasta `./build/jacocoHtml/index.html`. Para acessar o relatório web acesse:

![image](https://github.com/user-attachments/assets/96a0f085-a516-4402-99f3-556100217be2)


## Endpoint

### Caso de sucesso:

#### Request
```curl
curl --location 'http://localhost:8080/simulation?birthdate=07%2F12%2F1995&value=50000&paymentTerm=60'
```
#### Response
```json
{
    "totalValue": "R$ 53.906,07",
    "installmentValue": "R$ 898,43",
    "totalInterest": "R$ 3.906,07"
}
```

### Caso de falha:
#### Request
```curl
curl --location 'http://localhost:8080/simulation?birthdate=07%2F12%2F1995&value=0&paymentTerm=60'
```
#### Response
```json
{
    "message": "O valor do empréstimo deve ser maior que zero.",
    "status": 400
}
```

Mais informações sobre o funcionamento do endpoint de simulação de crédito pode ser encontrada na [Documentação](https://github.com/MaiconFiuza/credit_simulation_docs)

## Arquitetura escolhida
A arquitetura utilizada foi a clean arch, focando principalmente em isolar o core da aplicação do framework.

## Linter utilizado 
No projeto foi utilizado o [ktlint](https://pinterest.github.io/ktlint/latest/) que pode ser utilizado rodando o comando `./gradlew ktlintCheck` na raiz do projeto. 

