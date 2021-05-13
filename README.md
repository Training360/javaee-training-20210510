# Java EE képzés

## JPA

```shell
docker run -d -e POSTGRES_PASSWORD=password -p 5432:5432 --name employees-postgres postgres
```

A `bin/jboss-cli.bat` állományt elindítva a parancssorba beírandó:

```
connect
deploy "postgresql-42.2.20.jar"
data-source add --name=EmployeesDS --jndi-name=java:/jdbc/EmployeesDS \
  --driver-name=postgresql-42.2.20.jar \
  --connection-url=jdbc:postgresql:postgres \
  --user-name=postgres \
  --password=password
```
