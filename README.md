Task Manager API
Uma API RESTful completa para gerenciar tarefas (To-Do List), construída com Java, Spring Boot, Spring Security, PostgreSQL e JWT para autenticação. Este projeto serve como um exemplo de uma aplicação back-end robusta e segura, utilizando as melhores práticas de desenvolvimento.

🚀 Funcionalidades
CRUD de Tarefas: Crie, visualize, atualize e exclua tarefas.

Autenticação Segura: Registro e login de usuários com JSON Web Tokens (JWT).

Autorização por Usuário: Cada usuário só pode gerenciar suas próprias tarefas, garantindo segurança e privacidade.

Filtros de Tarefas: Filtre tarefas por status (PENDING, COMPLETED, OVERDUE).

Validação de Dados: Validação de campos de entrada (tamanho do título, datas) para garantir a integridade dos dados.

Documentação Interativa: Use o Swagger/OpenAPI para explorar, testar e entender todos os endpoints da API de forma interativa.

⚙️ Tecnologias Utilizadas
Backend: Java 17+, Spring Boot 3.x

Frameworks: Spring Data JPA, Spring Security

Banco de Dados: PostgreSQL

Segurança: JWT (JSON Web Tokens)

Documentação: Swagger/OpenAPI (springdoc-openapi)

Build: Maven

📋 Como Configurar e Rodar o Projeto
Pré-requisitos
Certifique-se de ter instalado:

JDK 17 ou superior

Maven

PostgreSQL

1. Clonar o Repositório
Bash

git clone https://github.com/seu-usuario/task-manager-api.git
cd task-manager-api
2. Configurar o Banco de Dados
Crie um banco de dados no PostgreSQL com o nome taskmanagerdb.

Abra o arquivo src/main/resources/application.properties e configure as credenciais do seu banco de dados:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanagerdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
Aviso: O ddl-auto=update é ideal para desenvolvimento, mas em produção, considere usar validate e gerenciar as migrações com ferramentas como Flyway ou Liquibase.

3. Executar a Aplicação
Execute a classe principal TaskManagerApiApplication.java a partir da sua IDE, ou use o Maven via linha de comando:

Bash

./mvnw spring-boot:run
A API estará rodando em http://localhost:8080.

📖 Documentação da API (Swagger)
A documentação interativa, gerada pelo Swagger, está disponível em:

URL: http://localhost:8080/swagger-ui.html

Nesta interface, você pode ver todos os endpoints, modelos de dados e testar as requisições diretamente.

🔑 Autenticação e Uso da API
Todos os endpoints de tarefas são protegidos por JWT. Siga estes passos para usá-los:

Registrar um Usuário

Endpoint: POST /api/auth/register

Body:

JSON

{
  "username": "meu_usuario",
  "password": "minha_senha"
}
Fazer Login e Obter o Token

Endpoint: POST /api/auth/login

Body:

JSON

{
  "username": "meu_usuario",
  "password": "minha_senha"
}
Resposta:

JSON

{
  "token": "seu_token_jwt_aqui..."
}
Usar o Token para Acessar Endpoints Protegidos

Para qualquer endpoint em /api/tasks, inclua o cabeçalho Authorization com o token.

Header: Authorization: Bearer seu_token_jwt_aqui...

👨‍💻 Contribuições
Contribuições são bem-vindas! Se você encontrar bugs ou tiver sugestões de melhoria, sinta-se à vontade para abrir uma issue ou um pull request.

Autor: Seu Nome
