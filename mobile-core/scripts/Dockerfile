# Use uma imagem base com Python
FROM python:3.9-slim

# Instale o MySQL Connector
RUN pip install mysql-connector-python

# Copie o script de inicialização para o container
COPY scripts/init_db.py /docker-entrypoint-initdb.d/scripts/init_db.py

# Defina o comando padrão para executar o script de inicialização
CMD ["python", "/docker-entrypoint-initdb.d/scripts/init_db.py"]
