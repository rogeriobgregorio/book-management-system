
# Book Management System - API Rest
[![Status](https://img.shields.io/badge/Status-Concluído-brightgreen.svg)](https://github.com/seu-usuario/seu-projeto)

## Descrição
Book Management System é uma aplicação Web, robusta e eficiente, para o gerenciamento completo de livros. Consiste em uma API Rest feita com Java e Spring, e uma interface com HTML, CSS e Javascipt. Construído com boas práticas de programação, inclui: autenticação segura com Spring Security e JWT; operações CRUD; testes unitários com JUnit e Mockito; logging com log4j2 para rastrear eventos e diagnosticar problemas; tratamento de erros e exceções; documentação interativa com Swagger.


## Funcionalidades

- Autenticação e Autorização
- Busca de todos os livros disponíveis
- Pesquisa por ID, título ou autor
- Cadastro de novos livros
- Edição de informações de livros
- Exclusão de livros 



## Demonstração

Vídeo demonstração: 
https://youtu.be/Ghs7_J_VMt0?si=IdRPn4Lrdd-BNu11


## Layout Web

**Tela Login**
![Login Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/screenshotLogin.png)

**Tela Inicial**
![Index Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/screenshotIndex.png)

**Tela Buscar Todos**
![FindAllBooks Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/screenshotFindAllBooks.png)

**Tela Buscar por Id**
![FindById Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/screenshotfindById.png)

**Tela Buscar por Título ou Autor**
![FindByTitleOrAuthor Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/screenshotFindBooksByTitleOrAuthor.png)

**Tela Criar Livro**
![FindByTitleOrAuthor Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/screenshotCreateBook.png)

**Tela Atualizar Livro**
![FindByTitleOrAuthor Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/screenshotUpdateBook.png)

**Tela Deletar Livro**
![FindByTitleOrAuthor Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/screenshotDeleteBook.png)

## Modelo Conceitual

**Diagrama de Classes**
![DiagramaDeCLasses Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/diagramaDeClasses.png)

**Diagrama de Casos de Uso**
![DiagramaDeCasosDeUso Screenshot](https://github.com/rogeriobgregorio/book-management-system/raw/main/screenshots/diagramaDeCasosDeUso.png)


## Documentação da API

### Autenticar Usuário

```http
  POST /auth/login
```

#### Descrição

Este endpoint realiza a autenticação de um usuário.

#### Parâmetros

| Nome          | Tipo   | Descrição                                   |
| ------------- | ------ | ------------------------------------------- |
| `login`       | `String` | **Obrigatório**. Nome de usuário (login).    |
| `password`    | `String` | **Obrigatório**. Senha do usuário.           |

#### Respostas

| Código | Descrição                        | Corpo                                   |
| ------ | -------------------------------- | --------------------------------------- |
| 200    | Login realizado com sucesso.     | ```json { "token": "token_gerado" }```  |
| 401    | Credenciais inválidas.           | -                                       |
| 500    | Erro ao logar.                   | -                                       |

### Registrar Usuário

```http
  POST /auth/register
```

#### Descrição

Este endpoint registra um novo usuário.

#### Parâmetros

| Nome          | Tipo   | Descrição                                   |
| ------------- | ------ | ------------------------------------------- |
| `login`       | `String` | **Obrigatório**. Nome de usuário (login).    |
| `password`    | `String` | **Obrigatório**. Senha do usuário.           |
| `role`        | `String` | **Opcional**. Papel ou função do usuário.    |

#### Respostas

| Código | Descrição                        | Corpo                                   |
| ------ | -------------------------------- | --------------------------------------- |
| 200    | Registro realizado com sucesso.  | -                                       |
| 400    | Usuário já existente.             | -                                       |
| 500    | Erro ao cadastrar-se.             | -                                       |

### Buscar Todos os Livros

```http
  GET /api/books
```

#### Descrição

Este endpoint retorna todos os livros disponíveis.

#### Respostas

| Código | Descrição               | Corpo                                |
| ------ | ----------------------- | ------------------------------------ |
| 200    | Busca realizada com sucesso. | ```json [{ "id": 1, "title": "Nome do Livro", "author": "Nome do Autor", "description": "Descrição", "price": 10.99 }]``` |
| 403    | Acesso não autorizado.      | -                                    |
| 404    | Nenhum livro encontrado.    | -                                    |
| 500    | Erro ao buscar livros.      | -                                    |

### Buscar Livro por ID

```http
  GET /api/books/{id}
```

#### Descrição

Este endpoint retorna um livro específico com base no ID fornecido.

#### Parâmetros

| Nome  | Tipo   | Descrição                            |
| ----- | ------ | ------------------------------------ |
| `id`  | `Long`  | **Obrigatório**. ID do livro desejado |

#### Respostas

| Código | Descrição               | Corpo                                |
| ------ | ----------------------- | ------------------------------------ |
| 200    | Busca realizada com sucesso. | ```json { "id": 1, "title": "Nome do Livro", "author": "Nome do Autor", "description": "Descrição", "price": 10.99 }``` |
| 403    | Acesso não autorizado.      | -                                    |
| 404    | Nenhum livro encontrado.    | -                                    |
| 500    | Erro ao buscar o livro.     | -                                    |

### Criar Novo Livro

```http
  POST /api/books
```

#### Descrição

Este endpoint cria um novo livro com base nos dados fornecidos.

#### Respostas

| Código | Descrição               | Corpo                                |
| ------ | ----------------------- | ------------------------------------ |
| 200    | Livro criado com sucesso.   | ```json [{ "id": 1, "title": "Nome do Livro", "author": "Nome do Autor", "description": "Descrição", "price": 10.99 }]``` |
| 403    | Acesso não autorizado.      | -                                    |
| 500    | Erro ao criar o livro.      | -                                    |

### Editar Livro

```http
  PUT /api/books
```

#### Descrição

Este endpoint edita um livro existente com base nos dados fornecidos.

#### Respostas

| Código | Descrição               | Corpo                                |
| ------ | ----------------------- | ------------------------------------ |
| 200    | Livro editado com sucesso. | ```json [{ "id": 1, "title": "Nome do Livro", "author": "Nome do Autor", "description": "Descrição", "price": 10.99 }]``` |
| 403    | Acesso não autorizado.      | -                                    |
| 404    | Nenhum livro encontrado.    | -                                    |
| 500    | Erro ao editar o livro.     | -                                    |

### Deletar Livro

```http
  DELETE /api/books/{id}
```

#### Descrição

Este endpoint deleta um livro específico com base no ID fornecido.

#### Parâmetros

| Nome  | Tipo   | Descrição                            |
| ----- | ------ | ------------------------------------ |
| `id`  | `Long`  | **Obrigatório**. ID do livro a ser deletado |

#### Respostas

| Código | Descrição               | Corpo                                |
| ------ | ----------------------- | ------------------------------------ |
| 200    | Livro deletado com sucesso. | ```json [{ "id": 1, "title": "Nome do Livro", "author": "Nome do Autor", "description": "Descrição", "price": 10.99 }]``` |
| 404    | Nenhum livro encontrado.    | -                                    |
| 500    | Erro ao deletar o livro.    | -                                    |

### Buscar por Título ou Autor

```http
  GET /api/books/search
```

#### Descrição

Este endpoint retorna livros com base no título ou autor fornecido.

#### Parâmetros

| Nome           | Tipo     | Descrição                                   |
| -------------- | -------- | ------------------------------------------- |
| `titleOrAuthor`| `String` | **Obrigatório**. Título ou autor a ser pesquisado. |

#### Respostas

| Código | Descrição               | Corpo                                |
| ------ | ----------------------- | ------------------------------------ |
| 200    | Busca realizada com sucesso. | ```json [{ "id": 1, "title": "Nome do Livro", "author": "Nome do Autor", "description": "Descrição", "price": 10.99 }]``` |
| 403    | Acesso não autorizado.      | -                                    |
| 404    | Nenhum livro encontrado.    | -                                    |
| 500    | Erro ao buscar livros.      | -                                    |

## Stack utilizada

**Back-end:** 
- Java
- Spring Boot
- JPA
- Hibernate
- Spring Security
- JWT 
- JUnit
- Mockito

**Front-end:** 
- HTML5
- CSS3
- Javascript




## Rodando localmente

Siga as etapas abaixo para configurar e executar o projeto Java com Spring localmente:

1. Certifique-se de ter o Java 17 JDK instalado. Caso não tenha, faça o download e a instalação a partir do site oficial do Java.

2. Clone o repositório do projeto:
```bash
  git clone https://github.com/rogeriobgregorio/book-management-system.git
```

3. Acesse o diretório do projeto:
```bash
  cd book-management-system
```

4. Execute o projeto:
```bash
  ./mvnw spring-boot:run
```
Se preferir, abra o projeto em uma IDE, como IntelliJ por exemplo, e execute o projeto.

Após concluir essas etapas, o seu servidor Spring estará em execução localmente na porta 8080.

A interface para consumir a API esta localizada na pasta "front-end".

5. Abra a página login.html no browser utilizando um servidor, como live server por exemplo.

6. Utilize o usuário **adminLogin** e a senha **adminPassword** para logar como administrador.
## Referência

 - [Documentação da API](http://localhost:8080/swagger-ui/index.html#)
A documentação completa da API pode ser acessada localmente ao executar a aplicação e visitar http://localhost:8080/swagger-ui/index.html#. Esta interface interativa, gerada pelo Swagger, fornece detalhes sobre cada endpoint, parâmetros de solicitação, respostas esperadas e exemplos práticos de uso.

 - [Spring Framework](https://spring.io/)
Consulte a documentação oficial do Spring Framework para obter informações detalhadas sobre o framework utilizado no backend. Esta referência abrange conceitos fundamentais, configurações avançadas e as melhores práticas recomendadas pela comunidade Spring.

 - [Swagger Documentation](https://swagger.io/)
O Swagger é integrado à API para facilitar a compreensão e interação. Obtenha mais informações sobre o Swagger em https://swagger.io/.
## Autores
#### Rogério Bernardo Gregório
- [Github](https://github.com/rogeriobgregorio)
- [LinkedIn](https://www.linkedin.com/in/rogeriogregorio/)


## Licença

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
