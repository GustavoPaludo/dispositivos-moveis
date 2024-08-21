Projeto final da matéria 'Programação para Dispositivos Móveis', onde foi desenvolvida uma aplicação para android simples, utilizando como backend uma api REST para comunicação com o app, além da utilização de JPA para a persistência de dados. A inicialização da aplicação foi automatizada utilizando o Docker. Seguem os passos para iniciar a aplicação:

Na pasta mobile-core, executar o comando start (Se estiver no windows, executar o start.bat, se estiver no linux, o start.sh). Para tal se assegurar que o docker está instalado corretamente. No Windows o Docker Desktop deve estar instalado, e de preferência estar com o WSL instalado.

No Ubuntu, navegar até o diretório do bash start.sh (tambem pode ser aberto um novo terminal clicando com o botão direito dentro da pasta em que está o script), e executando o comando 'sudo ./start.sh'.

No Windows, o processo é o mesmo, mas o comando que será executado é o '.\start.bat'.

Ao executar o comando start, tanto no linux quanto no windows o docker irá subir o banco de dados e logo após o término do mesmo irá executar a aplicação.

Baixar o workbench ou o dBeaver e configurar a conexão com esse banco. O usuário e senha são 'master'. O nome da base é 'mobile' e a porta é a 3306.

No aplicativo Android Java deverá ser alterado o IP do servidor conforme o IPV4 da máquina. Para descobrir o ip da máquina, digite 'hostname -I' no Ubuntu ou 'ipconfig' no Windows. Com esse ip, vá até a classe AppConstants.java localizada em mobile-app\app\src\main\java\com\udesc\projetofinal\AppConstants.java. Subustituir o ip na constante 'serverUrl' pelo IPV4 da máquina em que o servidor está rodando.
