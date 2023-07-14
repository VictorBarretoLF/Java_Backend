```bash
	docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=teste POSTGRES_DB=curso-api-rest-spring -p 5432:5432 -d postgres:12
```

```
	docker run --name pgadmin -p 8080:80 -e 	"PGADMIN_DEFAULT_EMAIL=admin@mydomain.com" -e 	"PGADMIN_DEFAULT_PASSWORD=teste" -d dpage/pgadmin4
	
```