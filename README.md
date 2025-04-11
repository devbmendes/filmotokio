
# Filmotokio

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/SpringBoot-2.6.1-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![License](https://img.shields.io/badge/license-MIT-lightgrey?style=for-the-badge)

**Filmotokio** é um portal para cinéfilos apaixonados pela sétima arte. Com ele, você pode registar filmes, avaliar, escrever reviews e explorar obras de diversos gêneros e diretores.

## Visão Geral

Este projeto faz parte do desafio final da formação Programação Spring Boot **Tokio School**. O sistema está dividido em três partes:

1. **Aplicação Web** – Interface web para utilizadores interagirem com filmes, pontuações e críticas.
2. **API REST** – Fornece endpoints para consumir dados dos filmes, reviews e utilizadores.
3. **Processo Batch** – Processo agendado para tarefas periódicas, como migração ou atualização de dados.

## Demonstração


## Funcionalidades

- Registro e login de utilizadores
- Papéis: ADMIN, USER
- Registro de filmes com ficha técnica
- Avaliações (pontuação de 1 a 5)
- Reviews dos utilizadores
- Associação de artistas (atores, diretores, músicos etc.)
- Tarefas batch agendadas para migração/atualização de filmes

---

## Tecnologias

- Java 17
- Spring Boot 2.6.1
- Spring Security + JWT
- Spring Data JPA / JDBC
- MySQL 8.0
- Thymeleaf
- Lombok
- Bootstrap 5.1.3
- Maven
- Docker (opcional)

---

## Como executar localmente

### 1. Clone o repositório

```bash
git clone https://github.com/devbmendes/filmotokio.git
cd filmotokio
```

### 2. Configure o banco de dados

No arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/filmotokio
spring.datasource.username=seu_utilizador
spring.datasource.password=sua_password
spring.jpa.hibernate.ddl-auto=update
```

> O banco será criado automaticamente ao iniciar o projeto.

### 3. Execute o projeto

No IntelliJ ou terminal:

```bash
./mvnw spring-boot:run
```

---

## Estrutura do Projeto

```
filmotokio
├── src
│   ├── main
│   │   ├── java/com/filmotokio
│   │   │   ├── controller
│   │   │   ├── model
│   │   │   ├── repository
│   │   │   └── service
│   │   └── resources/templates
├── pom.xml
└── README.md
```

---

## Contribuições

Sinta-se livre para fazer um fork do repositório e contribuir com PRs!

---

## Autor

**Balduino Mendes**  
[GitHub](https://github.com/devbmendes) | [LinkedIn](https://linkedin.com/in/devbmendes)

---

## Licença
Este projeto é desenvolvido para fins educativos e acadêmicos na Tokio School.
