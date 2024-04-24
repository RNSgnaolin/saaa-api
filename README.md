# San Andreas Aviation Administration
![Badge in Development](https://img.shields.io/badge/status-in_development-red)
![Last Commit](https://img.shields.io/github/last-commit/RNSgnaolin/saaa-api)

A REST API for a fictional aviation state company that mimics the Federal Aviation Administration, created for the purpose of portfolio, to display CRUD functionality in Spring Boot with standard practices such as Data Transfer Object, JWT authentication, Spring Security's authorities and validations using Jarkata and regex.

The front-end counterpart can be found [here](https://github.com/RNSgnaolin/saaa-front).

## 🛠️ Technologies

* Java 21
* Spring Boot 3.2.4
* Apache Maven 4.0.0
* MySQL 8.0.34
* Spring Security
* Auth0 JWT 4.4.0

## ✔️ Deployment
⚠️ **Still in development. Some functionalities may not be implemented.**

To set up a local environment, use MySQL to create the saaa_aircraft_api database and saaa_aircraft_api_test database. Flyway will take care of schemas as set in [V1__create-initial-state.sql](https://github.com/RNSgnaolin/saaa-api/blob/main/src/main/resources/db/migration/V1__create-initial-state.sql).

Make sure to check the username, password and localhost's port on [application.yaml](https://github.com/RNSgnaolin/saaa-api/blob/main/src/main/resources/application.yaml) before running the project on IntelliJ, Eclipse or VSCode.

For production deployment, first build the .jar file. Standard profile is used for development, so running the .jar file from the terminal should specify production profile via ```java -jar -Dspring.profiles.active=prod filename.jar```.

When in [production profile](https://github.com/RNSgnaolin/saaa-api/blob/main/src/main/resources/application-prod.yaml), database URL, username and password are the variables `DATASOURCE_URL`, `DATASOURCE_USERNAME` and `DATASOURCE_PASSWORD` and should be specified when running the .jar file, such as:

```
java -jar -Dspring.profiles.active=prod -DDATASOURCE_URL=your_db_url -DDATASOURCE_USERNAME=your_db_username -DDATASOURCE_PASSWORD=your_db_password
```

