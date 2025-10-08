# 🚀 Crowdfunding Backend

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen)
![JDBI](https://img.shields.io/badge/JDBI-3.45-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

REST API para plataforma de financiamento coletivo usando **Spring Boot 3** + **JDBI** + **MySQL**.

## 📋 Tecnologias

- **Java 21**
- **Spring Boot 3.3**
- **JDBI 3.45** (sem JPA/Hibernate)
- **MySQL 8.0**
- **JWT Authentication**
- **Swagger/OpenAPI**
- **Lombok**

## 🏗️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/crowdfunding/
│   │   ├── config/          # Configurações (Security, JDBI, CORS)
│   │   ├── controller/      # Controllers REST
│   │   ├── service/         # Lógica de negócio
│   │   ├── repository/      # DAOs com JDBI
│   │   ├── model/           # Entidades
│   │   ├── dto/             # DTOs
│   │   └── security/        # JWT e autenticação
│   └── resources/
│       ├── application.yml  # Configurações da aplicação
│       └── db/migration/    # Scripts SQL
```

## ⚙️ Configuração

### 1. Banco de Dados

Configure as variáveis de ambiente:

```bash
export DATABASE_URL=jdbc:mysql://localhost:3306/crowdfunding
export DATABASE_USER=root
export DATABASE_PASSWORD=yourpassword
export JWT_SECRET=your-secret-key-min-256-bits
```

### 2. Executar SQL Migrations

```bash
mysql -u root -p < src/main/resources/db/migration/V001__create_users_table.sql
mysql -u root -p < src/main/resources/db/migration/V002__create_campaigns_table.sql
mysql -u root -p < src/main/resources/db/migration/V003__create_contributions_table.sql
```

### 3. Rodar a Aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

Swagger UI: `http://localhost:8080/swagger-ui.html`

OpenAPI JSON: `http://localhost:8080/api-docs`

## 🔐 Endpoints Principais

| Método | Endpoint                    | Descrição                  | Auth |
|--------|-----------------------------|----------------------------|------|
| GET    | `/api/health`               | Health check               | ❌   |
| POST   | `/api/auth/register`        | Registro de usuário        | ❌   |
| POST   | `/api/auth/login`           | Login (retorna JWT)        | ❌   |
| GET    | `/api/campaigns`            | Lista campanhas            | ✅   |
| POST   | `/api/campaigns`            | Cria campanha              | ✅   |
| GET    | `/api/campaigns/{id}`       | Detalhes da campanha       | ✅   |
| POST   | `/api/contributions`        | Apoiar campanha            | ✅   |
| GET    | `/api/stats`                | Estatísticas agregadas     | ✅   |

## 🐳 Docker (Opcional)

```bash
docker-compose up -d
```

## 🚀 Deploy (Render)

1. Crie um Web Service no [Render](https://render.com)
2. Configure as variáveis de ambiente
3. Deploy automático via GitHub Actions

## 📝 Licença

MIT

---

**Desenvolvido por [Matheus](https://github.com/macmatheus)** 💪
