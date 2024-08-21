@echo off

docker-compose up --build -d

:WAIT_FOR_DB

docker-compose exec -T db mysqladmin ping -h "localhost" --silent >nul 2>&1
if not errorlevel 1 (
    goto CONTINUE
)
timeout /t 1 >nul
goto WAIT_FOR_DB

:CONTINUE
echo Banco de dados pronto. Iniciando a aplicação...
docker-compose exec -T app java -jar /app/mobile-app-final-0.0.1-SNAPSHOT.jar
