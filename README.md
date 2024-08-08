# Leaderboard Management Service

## Overview

This is a Spring Boot application for managing a leaderboard in a coding contest. The application allows CRUD operations on users, including the ability to register, update scores, and award badges based on user performance.

## Features

- Register users with unique IDs and usernames.
- Update user scores.
- Award badges based on user scores:
    - **Code Ninja** for scores 1-29
    - **Code Champ** for scores 30-59
    - **Code Master** for scores 60-100
- Retrieve users sorted by score.
- Delete users.

## Technology Stack

- **Java 21**
- **Spring Boot**
- **MongoDB**
- **JUnit 5** for testing
- **Maven** for project management

## How to Run

1. **Prerequisites**:
    - Java 21
    - Maven
    - MongoDB

2. **Setup**:
    - Clone the repository.
    - Configure MongoDB in `application.properties`.

3. **Run the Application**:
    - Use Maven: `mvn spring-boot:run`
    - Use your IDE: Run the `Main` class.

4. **Testing**:
    - Run the tests using Maven: `mvn test`
    - Or use your IDE to run the `UserServiceTests` class.

## API Endpoints

- **Get all users**: `GET /users`
- **Get a user by ID**: `GET /users/{userId}`
- **Create a user**: `POST /users`
- **Update user score**: `PUT /users/{userId}?score={newScore}`
- **Delete a user**: `DELETE /users/{userId}`