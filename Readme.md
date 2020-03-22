# SITESTAT

Web application, allowing for users to track attendance of website.

## Getting Started
### The simplest and quickest way to run the application is using Docker

#### Install docker on your local machine
* Windows: https://docs.docker.com/docker-for-windows/install/ 
* Mac: https://docs.docker.com/docker-for-mac/install/
* Linux: `sudo apt install docker-ce`

#### Open the terminal (or cmd) and type following commands:
```
docker run --name database -p 5432:5432 -e POSTGRES_PASSWORD=password -d kvmchik/sitestatdb 
```
##### (It can take some time, wait until the end)
#### Then: 
```
docker run --name application -p 8080:8080 kvmchik/sitestat
```
##### (It can also take some time, wait until the end)
### The application now is running on the port 8080
#### You can interact with the application by Swagger API. All you need to do is follow the link http://localhost:8080/

### To stop the application you need to type following commands (one by one): 
```
docker container stop database
```
```
docker container rm database
```
```
docker container stop application
```
```
docker container rm application
```

