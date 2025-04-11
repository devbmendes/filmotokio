# Filmotokio

**Filmotokio** é um portal para cinéfilos desenvolvido com **Spring Boot**, que permite o registo de filmes, avaliações e reviews por utilizadores apaixonados pela sétima arte.

## Visão Geral

Este projeto faz parte do desafio final da formação no curso de Programação FullStack com Spring Boot e React da **Tokio School**. O sistema está dividido em três partes:

1. **Aplicação Web** – Interface web para utilizadores interagirem com filmes, pontuações e críticas.
2. **API REST** – Fornece endpoints para consumir dados dos filmes, reviews e utilizadores.
3. **Processo Batch** – Processo agendado para tarefas periódicas, como migração ou atualização de dados.

## Tecnologias Utilizadas

- **Spring Boot 2.6.1**
- **Spring Web / MVC**
- **Spring Security**
- **Spring Data JPA + JDBC**
- **MySQL 8.0**
- **Lombok**
- **Thymeleaf**
- **JWT (Jjwt 0.9.1)**
- **Bootstrap 5.1.3**
- **Maven**

## Funcionalidades

- Registro e login de usuários
- Papéis de usuário (Admin, Moderador, Usuário)
- Cadastro de filmes
- Avaliações (pontuação de 1 a 5)
- Reviews escritas por usuários
- Listagem de filmes e detalhes
- Associação de artistas (diretores, atores, músicos etc.)
- Processos batch agendados para manutenção

## Estrutura do Projeto

