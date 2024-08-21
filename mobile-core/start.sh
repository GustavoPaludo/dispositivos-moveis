#!/bin/bash

# Inicia o container Docker com Docker Compose
docker-compose up --build -d

# Aguardar o banco de dados ficar pronto
echo "Aguardando o banco de dados ficar pronto..."
until docker-compose exec -T db mysqladmin ping -h "localhost" --silent; do
    sleep 1
done

# Inicia a aplicação
echo "Banco de dados pronto. Iniciando a aplicação..."
docker-compose exec -T app java -jar /app/mobile-app-final-0.0.1-SNAPSHOT.jar
