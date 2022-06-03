*hotel-api*
# Spring boot kotlin backend app for hotel project

Spring Boot + Kotlin + Gradle + PostgreSQL, CRUD api for my [hotel](https://github.com/BSUIR-IIPEKOLICT/hotel) project (minimal version)

[Spring reference](HELP.md)

Environment variables:
- `PORT` used port by app
- `SPRING_DATASOURCE_URL` db url string
- `SPRING_DATASOURCE_USERNAME` db username
- `SPRING_DATASOURCE_PASSWORD` db password

Setup database:
```shell
psql -U postgres
create database hotel_minimal;
\q
```

Load project:
```shell
git clone git@github.com:BSUIR-IIPEKOLICT/hotel-spring-backend.git
cd hotel-spring-backend
```

Bootstrap locally (needed 17 Java):
```shell
./gradlew build
./gradlew bootRun
```

Bootstrap on heroku (needed 17 Java):
```shell
java -Dserver.port=$PORT $JAVA_OPTS -jar build/libs/hotel-spring-backend-2.1.jar
```

Add heroku remote to project:
```shell
heroku login
git init
heroku git:remote -a iipekolict--hotel-sb
```

Deploy project (to heroku main branch):
```shell
git add .
git commit -m "commit message"
git push
git push heroku main
```
