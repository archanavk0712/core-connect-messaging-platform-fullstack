# CoreConnect Messaging Platform – Backend

This repository contains the backend implementation of the CoreConnect Messaging Platform built using Java and Spring Boot.

The backend is responsible for authentication, message persistence, user management, and API services that power the messaging system.

## Technology Stack

Java
Spring Boot
Spring Security
JWT Authentication
Spring Data JPA
Hibernate
PostgreSQL / H2 Database

## Backend Responsibilities

* User registration and authentication
* Secure login using JWT tokens
* Message storage and retrieval
* Managing user directory
* Conversation history retrieval
* API endpoints for frontend communication

## API Endpoints

Authentication

POST /api/auth/register
Register a new user

POST /api/auth/login
Authenticate user and return JWT token

Users

GET /api/users
Retrieve list of all registered users

Messages

GET /api/messages/{contactId}
Fetch conversation history between logged-in user and selected contact

POST /api/messages/send
Send and store a message in the database

## Database Design

User Entity

* id
* username
* password
* email

Message Entity

* id
* senderId
* receiverId
* content
* timestamp

Relationship
Messages maintain a Many-to-One relationship with users.

## Security

* Passwords are encrypted using BCrypt
* JWT tokens are used for secure authentication
* Protected endpoints require valid authentication tokens

## Running the Backend

Clone the repository

git clone <backend-repository-url>

Navigate to the project directory

Update the DB credentials in the application.properties file

Run the application using Maven or your IDE

The backend server will start on

http://localhost:8080
