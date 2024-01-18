# web-quiz-engine

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/web-quiz-engine/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/web-quiz-engine/blob/master/README.pt-br.md)

## Introduction
This is an API built with Spring Boot for creating, solving and retrieving quizzes that can be used as the backend for a web or mobile application.

## Features
- User registration and authentication
- CRUD operations for quizzes
- Quiz completion tracking for the logged user (for quizzes answered correctly)
- Pagination support for quiz retrieval
- Exception handling for robust error management

## API Endpoints

| Method | Endpoint                 | Description                                |
| ------ | ------------------------ | ------------------------------------------ |
| POST   | `/api/register`          | Register a new user                        |
| GET    | `/api/quizzes`           | Retrieve all quizzes with pagination       |
| POST   | `/api/quizzes`           | Create a new quiz                          |
| GET    | `/api/quizzes/{id}`      | Retrieve a quiz by its ID                  |
| DELETE | `/api/quizzes/{id}`      | Delete a quiz by its ID                    |
| PUT    | `/api/quizzes/{id}`      | Update a quiz by its ID                    |
| POST   | `/api/quizzes/{id}/solve`| Submit an answer for a quiz                |
| GET    | `/api/quizzes/completed` | Get completed quizzes for the logged user  |

## Technologies
- Spring Boot 3
- Spring Security
- Spring Data JPA
- H2
- Bean Validation
- Lombok
