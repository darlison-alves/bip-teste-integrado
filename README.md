## BACKEND
 ### ejb module

 ajustado modulo para rodar junto ao springboot instalando repositorio local

```shell
  mvn -Didea.version=2025.2.4 -Dmaven.ext.class.path=/home/darlison/Programs/ideaIC-2025.2.4/idea-IC-252.27397.103/plugins/maven/lib/maven-event-listener.jar -Djansi.passthrough=true -Dstyle.color=always -Dmaven.repo.local=/home/darlison/.m2/repository install -f pom.xml
```
caso precise instalar o modulo local ajustar repository local

-Dmaven.repo.local=/home/darlison/.m2/repository

Após instalar as dependencias do modulo SpringBoot

````shell
  mvn clean install
````
### API

Rodar API

Irá subir um servidor na porta 8080

http://localhost:8080

````shell
  mvn spring-boot:run
````

Doc Swagger: http://localhost:8080/swagger-ui/index.html#/

### FRONTEND

Front feito em Angular 21

  - ações de lista contar
  - transferir saldos

Rodar Front

```shell
    cd frontend/bip
```

```shell
    npm install
```

```shell
    npm start
```

Irá subir servidor: http://localhost:4200