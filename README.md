
# Filmotokio

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/SpringBoot-2.6.1-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![License](https://img.shields.io/badge/license-MIT-lightgrey?style=for-the-badge)

**Filmotokio** é um portal para cinéfilos apaixonados pela sétima arte. Com ele, você pode cadastrar filmes, avaliar, escrever reviews e explorar obras de diversos gêneros e diretores.

## Demonstração

> (Substituir por suas imagens reais ou GIFs)

![Tela inicial](docs/tela-inicial.png)
![Detalhes do filme](docs/detalhes-filme.png)

---

## Funcionalidades

- Cadastro e login de usuários
- Papéis: ADMIN, MODERATOR, USER
- Cadastro de filmes com ficha técnica
- Avaliações (pontuação de 1 a 5)
- Reviews dos usuários
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
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

> O banco será criado automaticamente ao iniciar o projeto.

### 3. Execute o projeto

No IntelliJ ou terminal:

```bash
./mvnw spring-boot:run
```

---

## Docker (opcional)

Você pode rodar a aplicação com Docker e Docker Compose.

### 1. Crie o `docker-compose.yml`

```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8
    container_name: filmotokio_db
    environment:
      MYSQL_DATABASE: filmotokio
      MYSQL_ROOT_PASSWORD: root
    ports:
      - "3306:3306"

  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mysql
```

### 2. Suba o ambiente

```bash
docker-compose up --build
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

**Bruno Mendes**  
[GitHub](https://github.com/devbmendes) | [LinkedIn](https://linkedin.com/in/SEU_USUARIO)

---

## Licença

Este projeto está licenciado sob a licença MIT.
