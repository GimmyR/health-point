# Health Point

[![Health Point](https://github.com/GimmyR/health-point/actions/workflows/ci.yaml/badge.svg)](https://github.com/GimmyR/health-point/actions/workflows/ci.yaml)

Health Point is a web application that lets a doctor/nurse to save patient's data in relation to some parameters and to visualize changes in these parameters.

It is built with:

- **React** for the frontend  
- **Spring Boot** for the backend API  
- **PostgreSQL** for data persistence  
- **Docker** for containerized deployment

## Prerequisites

Before building or running the application, make sure you have the following installed :

* **Docker** 29.0.2
* **Docker Compose** 2.40.3

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
VITE_BACKEND_URL=http://localhost:8080
```

If you want to use a `.env` file, place it in the project's root directory.

## Launch the application

Open a terminal in the project's root directory and run the following command :

```bash
docker compose --profile prod up --build
```

You can access the frontend application in your browser at http://localhost:5173.

The API documentation is available at http://localhost:8080/docs. (WITH SWAGGER LATER)

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.