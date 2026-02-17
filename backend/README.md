# Health Point (Backend)

This is the backend and API for the entire Health Point project, built with Spring Boot.
You can build and run this application on its own, but the following configurations are required.

## Prerequisites

Before building or running the application, make sure you have the following installed :

* **Java** 21.0.9
* **Maven** 3.9.9
* **PostgreSQL**

## Environment variables

```bash
# Database (PostgreSQL)
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
DB_name=your_database_name
DB_URL=jdbc:postgresql://localhost:5432/${DB_NAME}

# Password hashing strength (bcrypt cost)
PASSWORD_STRENGTH=12

# JWT (HS512 requires at least 64 bytes)
JWT_SECRET=your_very_long_random_secret_key_here_at_least_64_bytes

# Frontend / CORS / API URLs
FRONTEND_URL=http://localhost:5173
```

If you want to use a `.env` file, place it in the project's root directory.

## Installation

You need to be in the backend folder before running :

```bash
mvn clean install
```

To skip tests during the build, use :

```bash
mvn clean install -DskipTests
```

## Launch the application

To run the application, execute the following command :

```bash
java -jar target/*.jar
```

The API documentation is available at http://localhost:8080/docs .
