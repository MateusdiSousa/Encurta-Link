# 🔗 Encurta Link - URL Shortener Service


A high-performance Spring Boot URL shortener with JWT authentication, Redis caching, and automated link expiration. Designed to showcase Java/Spring Boot best practices in building scalable web services.

## 📋 Prerequisites  

- Java 21
- Docker e Docker Compose
- Maven 3.6+
- Git

## ✨ Key Features  
- **URL Shortening**: Convert long URLs into compact, shareable links  
- **Smart Redirection**: High-performance cached redirects using Redis  
- **Secure Authentication**: JWT-based access control  
- **Automatic Cleanup**: Scheduled removal of expired links  
- **Production-Ready**: Dockerized with PostgreSQL and Redis 
- **Interactive Documentation:** Swagger UI for test and API documentation 

## ⚒️ Tech Stack  
- **Backend**: Java 21, Spring Boot 3.5.4  
- **Security**: JWT, Spring Security  
- **Database**: PostgreSQL (persistent storage)  
- **Caching**: Redis (performance optimization)  
- **Scheduling**: Spring Scheduler (automatic expiration handling)  
- **Containerization**: Docker (easy deployment)  
- **Documentation**: SpringDoc OpenAPI 

## ✈ Quick Start (Docker Setup)  

### Method 1: Docker Compose (Recomended)  
```bash
# Clone the repository
git clone https://github.com/MateusdiSousa/ShortLink.git
cd ShortLink

# Run the project (automatic build and execute)
make prod

# Or run the steps manually:
docker compose up --build
```

### Method 2: Local Development
```bash
# Clone the repository
git clone https://github.com/MateusdiSousa/ShortLink.git
cd ShortLink

# Build the project
mvn clean package

# Run docker compose
docker compose up

# Or run in develop mode:
make dev
```

## Useful Commands:

```bash
# Build 
make build

# Development mode
make dev

# Production mode
make prod

# Run test
make test

# Show application logs
make logs

# Stop application
make stop

# Complete clean 
make deep-clean

# Containers status
make status
```

## API Documentation
* Note: Most endpoints require JWT authentication (obtained via login)

### Authentication

#### 👤 Register New User
📝 Method: POST
🔗 Endpoint: /api/auth/register
📝 Request:
```json
{
    "email": "user@example.com",
    "password": "securePassword123"
}
```

#### User Login
📝 Method: POST
🔗 Endpoint: /auth/login
```json
{
    "email": "user@example.com",
    "password": "securePassword123"
}
```
Response: Returns JWT token for authenticated requests

### Link Management
#### Create Short Link
📝 Method: POST
🔗 Endpoint: /link
📌  Headers: Authorization: Bearer <JWT_TOKEN>
📝 Request:
```
{
    "link": "https://www.string.com",
    "shortLink": "string"
}
```

### Redirect to Original URL
📝 Method: GET
🔗 Endpoint: /link/{shortCode}
(No authentication required)

### User Operations
#### Get User's Links
📝 Method: GET
📌  Headers: Authorization: Bearer <JWT_TOKEN>
Response:
```
[
    {
        "id": "abc123",
        "originalLink": "https://original.com",
        "shortLink": "xyz789",
        "userEmail": "user@example.com"
    }, ...
]
```

## Accessing Swagger UI
For interactive API documentation and testing, visit:
http://localhost:3000/swagger-ui/index.html

## Development
* The API runs on port 3000 by default

* Swagger UI provides full endpoint documentation

* Docker environment includes:
    *   Spring Boot application
    * PostgreSQL database
    * Redis cache
