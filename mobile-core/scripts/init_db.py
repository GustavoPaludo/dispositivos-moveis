import time
import mysql.connector

def initialize_db():
    connection = mysql.connector.connect(
        host='localhost',
        user='root',
        password='root'
    )

    cursor = connection.cursor()
    
    # Setup replication user
    cursor.execute("SET SQL_LOG_BIN=0;")
    cursor.execute("CREATE USER IF NOT EXISTS 'replication_user'@'%' IDENTIFIED BY 'replication_password';")
    cursor.execute("GRANT REPLICATION SLAVE ON *.* TO 'replication_user'@'%';")
    cursor.execute("FLUSH PRIVILEGES;")
    cursor.execute("SET SQL_LOG_BIN=1;")
    
    # Change master for group replication
    cursor.execute("CHANGE MASTER TO MASTER_USER='replication_user', MASTER_PASSWORD='replication_password' FOR CHANNEL 'group_replication_recovery';")
    
    # Install and start group replication plugin
    cursor.execute("INSTALL PLUGIN group_replication SONAME 'group_replication.so';")
    cursor.execute("SET GLOBAL group_replication_bootstrap_group=OFF;")
    
    cursor.execute("START GROUP_REPLICATION;")

    cursor.close()
    connection.close()

# Wait for database to be ready
time.sleep(20)
initialize_db()

# Keep container running
while True:
    time.sleep(1000)
