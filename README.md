# adverts

```
1. docker run --name postgresAdverts -e POSTGRES_PASSWORD="postgres" -e POSTGRES_DB="adverts" -d -p 5433:5432 postgres:12.5
2. docker run --name activemqAdverts -p 61616:61616 -p 8161:8161 rmohr/activemq
3. run app
4. swagger http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```
