# Biblioteca API

Este é um projeto de uma API para gerenciamento de uma biblioteca, desenvolvido em **Java 21** utilizando o framework **Spring Boot**. 
Este projeto segue uma arquitetura em camadas, baseada nos princípios de **DDD**, separando claramente responsabilidades de domínio, aplicação, infraestrutura e interface (API).

## Funcionalidades

- **Gerenciamento de Livros**:
    - Cadastrar livros.
    - Listar todos os livros.
    - Listar livros disponíveis.

- **Gerenciamento de Usuários**:
    - Listar todos os usuários.

- **Empréstimos**:
    - Gerenciar empréstimos de livros.

## Tecnologias Utilizadas

- **Linguagem**: Java
- **Framework**: Spring Boot
- **Gerenciador de Dependências**: Maven
- **Banco de Dados**: PostgreSQL
- **Outras Dependências**:
    - Spring Data JPA
    - Spring Web
    - MapStruct
    - Lombok
    - Swagger/OpenAPI
  
## Estrutura do Projeto

- **Camada de Infraestrutura**:
  - **Camada de Application**:
      - Contém os serviços (`Service`) e mapeadores (`Mapper`) responsáveis pela lógica de aplicação e conversão de dados.

  - **Camada de Domain**:
      - Contém os modelos de domínio e interfaces de repositórios.

  - **Camada de JPA**:
      - Contém as implementações de repositórios JPA e entidades persistentes.
    
- **Camada de Rest**:
  - **Camada de Controllers**:
    - Contém os controladores REST que expõem os endpoints da API.

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```
2. Navegue até o diretório do projeto:  
  ```bash
  cd biblioteca-api
  ```
3. Compile o projeto:  
  ```bash
   mvn clean install
  ```
4. Execute a aplicação:  
    ```bash
   mvn spring-boot:run
    ```
5. Acesse a aplicação no Postman em: http://localhost:8080  


## Endpoints
- Livros:  
  - GET /livros: Lista todos os livros.
  - GET /livros/disponiveis: Lista livros disponíveis.
  - POST /livros: Cadastra um novo livro.
- Usuários:  
  - GET /usuarios: Lista todos os usuários.