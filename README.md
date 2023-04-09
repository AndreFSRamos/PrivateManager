# PrivateManager
Projeto Desenvolvido usando Vue JS para o front, Java, Spring Boot e Swagger para o back-end(API Rest), para o banco de dados MYSQL 8. O back-end e a base de dados estão rodando em dois container Docker linkados.


COMANDOS DOCKER PARA CRIAR A IMAGEM DO PROJETO

CRIA UMA IMAGEM
--> docker build -t app .

INICIARO CONATINER 
--> docker run -d -p 5001:8080 --name back-end-private_manager --link db-private_manager:db-private-manager app
