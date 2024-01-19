# README - Bidding Scraping Gov - API

# Descrição

Este é um projeto Spring em Java 17 que realiza web scraping diário no site [http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp](http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp) para coletar dados de licitações. A aplicação possui funcionalidades para identificar registros novos e antigos, atualizar registros e salvar novos dados. Além disso, fornece três endpoints para facilitar a interação de usuários.

## Habilidades Principais

### 1. Web Scraping Diário

A aplicação realiza web scraping diário no link [http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp](http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp) para obter dados atualizados de licitações. Esse processo é agendado usando a funcionalidade de schedule do Spring.

### 2. Identificação de Registros Novos e Antigos

A aplicação é capaz de identificar automaticamente registros novos e antigos durante o web scraping. Isso permite a **atualização eficiente dos dados existentes preservando a flag lido/não lido** e a adição de novos registros ao banco de dados.

### 3. Três Endpoints para Interação

A aplicação disponibiliza três endpoints para interação:

### a. Endpoint de Busca

- Método: `GET`
- URL: `/``search`
- Parâmetros:
    
    `searchTerm`- termo de busca para filtrar licitações *(number, object, institution, modality, UASGCode, telephone*)
    
    - `viewed`- indica se o registro foi visto ou não (true/false)
    - `pageable`- número da página para paginação

### b. Endpoint de Busca por ID

- Método: `GET`
- URL: `by-id`
- Parâmetros:
    - `number`- número da licitação
    - `modality`- modalidade da licitação
    - `uasgCode` - código UASG (Unidade Administrativa Serviços Gerais) da licitação

### c. Endpoint de Atualização de Visto

- Método: `Patch`
- URL: `/``viewed`
- Parâmetros:
    - `number`- número da licitação
    - `modality`- modalidade da licitação
    - `uasgCode` - código UASG da licitação

## Configuração do Projeto

Certifique-se de ter o Java 17 instalado em sua máquina. O projeto utiliza o framework Spring, então é recomendado o uso do Maven para construir e gerenciar dependências.

1. Clone o repositório: `git clone <https://github.com/LuizFreitas225/bidding-scraping-gov.git`[>](https://github.com/LuizFreitas225/bidding-scraping-gov.git)
2. Navegue até o diretório do projeto: `cd bidding-scraping-gov`
3. Construa o projeto: `mvn clean install`
4. Construa a Base de dados com Docker: `docker-compose up` 
5. Execute a aplicação: `java -jar target/bidding-scraping-gov-0.0.1-SNAPSHOT.jar`

### **Documentação Swagger**

A documentação Swagger está disponível e pode ser acessada em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html#/bidding-controller/searchBiddings)

![Untitled](README%20-%20Bidding%20Scraping%20Gov%20-%20API%200ef771fe70114befbc47770d15727430/Untitled.png)

## Contribuições

Contribuições são bem-vindas! Se encontrar problemas ou tiver sugestões para melhorar o projeto, sinta-se à vontade para criar uma issue ou enviar um pull request.

##