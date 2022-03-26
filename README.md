*hotel-api*
# Spring boot kotlin backend app for hotel project

Spring Boot + Kotlin + Gradle + PostgreSQL, CRUD api for my [hotel](https://github.com/BSUIR-IIPEKOLICT/hotel) project

[Spring reference](HELP.md)

Environment variables:
- `PORT` used port by app
- `JWT_SECRET` secret key for JWT
- `JWT_EXPIRATION_HOURS` jwt expiration time in hours
- `ROOM_LIMIT` default limit for room pagination
- `BCRYPT_STRENGTH`
- `MIN_EMAIL_CHUNKS` min chunks separated by `.` in email
- `MIN_PASSWORD_LENGTH`
- `TOKEN_TYPE_KEY`
- `TOKEN_TYPE_VALUE`
- `SPRING_DATASOURCE_URL` db url string
- `SPRING_DATASOURCE_USERNAME` db username
- `SPRING_DATASOURCE_PASSWORD` db password

Setup database:
```shell
psql -U postgres
create database hotel;
\q
```

Load project:
```shell
git clone git@github.com:BSUIR-IIPEKOLICT/hotel-api.git
cd hotel-api
```

Bootstrap project (needed 17 Java):
```shell
./gradlew build -x test
java -jar build/libs/hotel-api-2.0.jar
```
