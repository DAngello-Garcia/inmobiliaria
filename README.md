# Inmobiliaria
Sistema de gestión de una inmobiliaria.
### Requisitos previos
- Instalar [Docker Desktop](https://www.docker.com/products/docker-desktop/)

- Clonar el repositorio
```
 git clone https://github.com/taroubghfhf/inmobiliaria.git
```
- Ingresar al directorio del repositorio
```
  cd inmobiliaria/
```
### Configuración de la base de datos
- Utilizar la imagen de Postgres
```
docker pull postgres
```
- Crear la base de datos en un contenedor con la imagen de Postgres
```
docker run --name inm-container -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -e POSTGRES_DB=inmobiliaria -d -p 5432:5432 postgres
```
- Copiar el script sql al contenedor
```
docker cp .\inmobiliaria\src\main\java\co\edu\uniquindio\inmobiliaria\utilidad\db.sql inm-container:/docker-entrypoint-initdb.d/
```
- Ejecutar el script sql
```
docker exec -it inm-container psql -U postgres -d inmobiliaria -f /docker-entrypoint-initdb.d/db.sql
```