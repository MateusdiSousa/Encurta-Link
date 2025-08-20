# Variáveis
APP_NAME := encurta_link-app
DOCKER_COMPOSE := docker compose
MAVEN := mvn
DOCKER := docker

.PHONY: clean build build-services start stop logs test dev prod

# Limpeza completa
clean:
	$(DOCKER_COMPOSE) down --rmi all --volumes --remove-orphans
	$(DOCKER) image rm $(APP_NAME):latest 2>/dev/null || true
	$(MAVEN) clean

# Construir apenas os serviços de dependência
build-services:
	$(DOCKER_COMPOSE) up --detach redis db

# Construir a aplicação
build-app:
	$(MAVEN) clean package -DskipTests
	$(DOCKER) build -t $(APP_NAME):latest .

# Construir tudo
build: clean build-services build-app

# Iniciar a aplicação em modo detached
start:
	$(DOCKER_COMPOSE) up --detach app

# Parar a aplicação
stop:
	$(DOCKER_COMPOSE) stop app

# Ver logs
logs:
	$(DOCKER_COMPOSE) logs -f app

# Executar testes
test: clean build-services
	$(MAVEN) test

# Modo desenvolvimento (com hot reload se configurado)
dev: build-services
	$(MAVEN) spring-boot:run

# Modo produção
prod: build
	$(DOCKER_COMPOSE) up --detach

# Ver status dos containers
status:
	$(DOCKER_COMPOSE) ps

# Remover volumes também (limpeza mais profunda)
deep-clean: clean
	$(DOCKER_COMPOSE) down -v
	$(DOCKER) system prune -f

# Health check dos serviços
health-check:
	@echo "Verificando saúde dos serviços..."
	$(DOCKER_COMPOSE) ps
	@echo "Redis:"
	docker exec $$(docker compose ps -q redis) redis-cli ping
	@echo "PostgreSQL:"
	docker exec $$(docker compose ps -q db) pg_isready -U $(POSTGRES_USER)

# Help
help:
	@echo "Comandos disponíveis:"
	@echo "  make build      - Construir tudo (limpa, build services e app)"
	@echo "  make clean      - Limpar containers e imagens"
	@echo "  make start      - Iniciar aplicação"
	@echo "  make stop       - Parar aplicação"
	@echo "  make logs       - Ver logs da aplicação"
	@echo "  make test       - Executar testes"
	@echo "  make dev        - Modo desenvolvimento"
	@echo "  make prod       - Modo produção"
	@echo "  make status     - Ver status dos containers"
	@echo "  make deep-clean - Limpeza profunda"