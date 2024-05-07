# Projeto: Audsat - Teste técnico de backend

Repositório contendo o projeto: Audsat.

### Sumário:

* [Tecnologias](#tecnologias)
* [Ferramentas utilizadas](#ferramentas-utilizadas)
* [Arquitetura Proposta](#arquitetura-proposta)
* [Execução do projeto](#execu%C3%A7%C3%A3o-do-projeto)
    * [01 - Execução geral via docker-compose](#01---execu%C3%A7%C3%A3o-geral-via-docker-compose)
    * [02 - Executando manualmente via CLI](#04---executando-manualmente-via-cli)

## Tecnologias

[Voltar ao início](#sumário)

* **Java 17**
* **Spring Boot 3**
* **API REST**
* **H2 Database**
* **Docker**
* **docker-compose**
* **Postman**

# Ferramentas utilizadas

[Voltar ao início](#sumário)

* **IntelliJ IDEA**
* **Docker**
* **Gradle**

# Arquitetura Proposta

[Voltar ao início](#sumário)

Foi desenvolvida a seguinte aquitetura:

Em nossa arquitetura, teremos 1 serviço:

* **audsat**: serviço responsável por cadastrar, deletar, atualizar e buscar orçamento de seguro de veículos.
  Aqui que teremos endpoints REST para inciar o processo e fazer as operações necessárias
  O banco de dados utilizado será o H2.
* 
O serviço irá subir através do arquivo **docker-compose.yml** ou simplesmente **rodando pela IDE**.

## Execução do projeto

[Voltar ao início](#sumário)

Há várias maneiras de executar o projeto:

1. Executando tudo via `docker-compose`
2. Executando tudo via `IDE`

Para rodar as aplicações, será necessário ter instalado:

* **Docker** caso queira executar pelo mesmo;
* **Java 17**
* **Gradle 7.6 ou superior**

### 01 - Execução geral via docker-compose

[Voltar ao nível anterior](#execução-do-projeto)

Basta executar o comando no diretório raiz do repositório:

`docker-compose up --build -d`

**Obs.: para rodar tudo desta maneira, é necessário realizar o build da aplicação, veja nos passos abaixo sobre como fazer isto.**

### 02 - Executando manualmente via CLI

[Voltar ao nível anterior](#execução-do-projeto)

Antes da execução do projeto, realize o `build` da aplicação indo no diretório raiz e executando o comando:

`gradle build -x test`

Para executar os projetos com Gradle, basta entrar no diretório raiz, e executar o comando:

`gradle bootRun`

Ou então, entrar no diretório: `build/libs` e executar o comando:

`java -jar nome_do_jar.jar`

## Acessando a aplicação

[Voltar ao início](#sumário)

Para acessar a aplicação e recuperar um orçamento de seguro, basta acessar a URL:

http://localhost:8080/swagger-ui/index.html#/

As aplicações executarão nas seguintes portas:

* audsat: 8080

## Autor

### Francisco Edglei de Sousa
### Desenvolvedor de Software Back-End
