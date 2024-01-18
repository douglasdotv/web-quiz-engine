# web-quiz-engine

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/web-quiz-engine/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/web-quiz-engine/blob/master/README.pt-br.md)

## Introdução
Esta é uma API construída com Spring Boot para criação, resolução e obtenção de quizzes que pode ser utilizada como backend para uma aplicação web ou mobile.

## Funcionalidades
- Registro e autenticação de usuários
- Operações CRUD para quizzes
- Acompanhamento de quizzes respondidos corretamente para o usuário autenticado
- Suporte à paginação
- Tratamento de exceções para uma gestão robusta de erros

## Endpoints

| Método | Endpoint                 | Descrição                                  |
| ------ | ------------------------ | ------------------------------------------ |
| POST   | `/api/register`          | Registrar um novo usuário                  |
| GET    | `/api/quizzes`           | Recuperar todos os quizzes com paginação   |
| POST   | `/api/quizzes`           | Criar um novo quiz                         |
| GET    | `/api/quizzes/{id}`      | Recuperar um quiz pelo ID                  |
| DELETE | `/api/quizzes/{id}`      | Deletar um quiz pelo ID                    |
| PUT    | `/api/quizzes/{id}`      | Atualizar um quiz pelo ID                  |
| POST   | `/api/quizzes/{id}/solve`| Enviar uma resposta para um quiz           |
| GET    | `/api/quizzes/completed` | Obter quizzes completos para o usuário logado |

## Tecnologias
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Bean Validation
- H2
- Lombok
