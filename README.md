# 📝 Task Manager API

Uma API RESTful robusta para gerenciamento de tarefas (To-Do List), desenvolvida com **Java 24.0.2"**, **Spring Boot**, **Spring Security**, **JWT** e **PostgreSQL**. O projeto aplica as melhores práticas de desenvolvimento para criar uma aplicação segura, escalável e bem documentada.

![Java](https://img.shields.io/badge/Java‑24-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%234169E1.svg?&logo=postgresql&logoColor=white)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

---

## 🚀 Funcionalidades

- ✅ **CRUD de Tarefas**: Crie, visualize, atualize e exclua tarefas.
- 🔐 **Autenticação JWT**: Registro e login de usuários com tokens seguros.
- 🔒 **Autorização Individual**: Cada usuário só acessa suas próprias tarefas.
- 🔎 **Filtros de Tarefas**: Filtragem por status (`PENDING`, `COMPLETED`, `OVERDUE`).
- 🧾 **Validação de Dados**: Campos validados para garantir integridade.
- 📄 **Documentação Interativa**: Swagger/OpenAPI para explorar e testar endpoints.

---

## ⚙️ Tecnologias Utilizadas

- **Linguagem:** Java 24+
- **Frameworks:** Spring Boot, Spring Data JPA, Spring Security
- **Banco de Dados:** PostgreSQL
- **Segurança:** JSON Web Tokens (JWT)
- **Documentação:** Swagger (via `springdoc-openapi`)
- **Build Tool:** Maven

---

## 📋 Como Rodar o Projeto

### ✅ Pré-requisitos

- JDK 24+
- Maven
- PostgreSQL

### 🔽 1. Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/task-manager-api.git
cd task-manager-api
```

### 🛠️ 2. Configurar o Banco de Dados

1. Crie um banco chamado `taskmanagerdb` no PostgreSQL.
2. Configure o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanagerdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
```

> ⚠️ Em produção, substitua `ddl-auto=update` por `validate` e use ferramentas como Flyway ou Liquibase.

### ▶️ 3. Executar a Aplicação

Pela linha de comando:

```bash
./mvnw spring-boot:run
```

Ou execute a classe `TaskManagerApiApplication.java` pela sua IDE.

A API estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 📖 Documentação com Swagger

Acesse a documentação interativa gerada automaticamente:

📎 URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🔑 Autenticação JWT

### 📝 Registrar Usuário

**POST** `/api/auth/register`

```json
{
  "username": "meu_usuario",
  "password": "minha_senha"
}
```

### 🔐 Fazer Login

**POST** `/api/auth/login`

```json
{
  "username": "meu_usuario",
  "password": "minha_senha"
}
```

**Resposta:**

```json
{
  "token": "seu_token_jwt_aqui..."
}
```

### 📌 Acessar Endpoints Protegidos

Adicione o token no cabeçalho:

```
Authorization: Bearer seu_token_jwt_aqui...
```

---

## 👨‍💻 Contribuições

Contribuições são bem-vindas! Para reportar problemas ou sugerir melhorias:

- Abra uma **issue**
- Envie um **pull request**

---

## 👤 Autor

**Ryan Pavini**  
[LinkedIn](https://www.linkedin.com/in/ryan-pavini/)

---

## 📝 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
