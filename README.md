# FINANCE PERSONAL 💰💲



#### ABOUT
This project consists of a backend API for personal finance management. 
It includes user registration and login. Each user can view their income (gains and losses) 
and obtain a final report based on their expenses and revenues.


## Technologies ☕
```
- JAVA 17
- Maven
- SPRING BOOT
- SPRING SECURIRY
- JPA
- LOMBOK
- SPRING STARTER WEB
- DATABASE MYSQL
- JWT 
```


# ROUTES 📍

### ```USER```

| Método | Rota        | Descrição                              |
| ------ | ----------- | -------------------------------------- |
| POST   | `/register` | Register new user           |
| POST   | `/login`    | Authenticate user and generete token JWT |


### ```EXPENSES```

| Method | Route                                         | Description                                           |
| ------ | --------------------------------------------- | ----------------------------------------------------- |
| POST   | `/auth/expenses`                              | Create a new expense                                  |
| GET    | `/auth/expenses`                              | Get all user expenses                                 |
| GET    | `/auth/expenses/{year}/{month}`               | Get expenses by year and month                        |
| GET    | `/auth/expenses/{nameCategory}`               | Get expenses by category                              |
| GET    | `/auth/expenses/totalUser/{year}/{month}`     | Get total user expenses by year and month             |
| GET    | `/auth/expenses/totalCategory/{year}/{month}` | Get total expenses grouped by category for the period |
| DELETE | `/auth/expenses/{description}`                | Delete an expense by description                      |
| PUT    | `/auth/expenses/{description}`                | Update an expense by description                      |



### ```REVENUES```

| Method | Route                                         | Description                                           |
| ------ | --------------------------------------------- | ----------------------------------------------------- |
| POST   | `/auth/revenues`                              | Create a new revenue                                  |
| GET    | `/auth/revenues`                              | Get all user revenues                                 |
| GET    | `/auth/revenues/{year}/{month}`               | Get revenues by year and month                        |
| GET    | `/auth/revenues/{nameCategory}`               | Get revenues by category                              |
| GET    | `/auth/revenues/totalUser/{year}/{month}`     | Get total user revenues by year and month             |
| GET    | `/auth/revenues/totalCategory/{year}/{month}` | Get total revenues grouped by category for the period |
| DELETE | `/auth/revenues/{description}`                | Delete a revenue by description                       |
| PUT    | `/auth/revenues/{description}`                | Update a revenue by description                       |


## 🚀 Getting Started

Follow the steps below to run the project locally.

### 1 Clone the repository

bash
```
git clone https://github.com/your-username/your-repository-name.git
cd your-repository-name 
```

### 2 Configure application.properties

Open the file:

```
src/main/resources/application.properties
```
### Configure your MySQL, Hibernate, and JWT settings:

```
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

# Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# JWT configuration
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000
```
⚠️ Make sure your MySQL server is running before starting the application.


 # Thank you for taking the time to check out this project.

### Any help, suggestions, or improvements are more than welcome!  
### If you would like to contribute, feel free to open a **Pull Request (PR)** — all contributions are appreciated.

# Happy coding!

