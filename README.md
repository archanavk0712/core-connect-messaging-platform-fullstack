# CoreConnect Messaging Platform

CoreConnect is a full-stack messaging application designed to demonstrate secure authentication, persistent messaging, and full-stack integration between a Java Spring Boot backend and a React frontend.

## Technology Stack

Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* PostgreSQL / H2

Frontend

* React
* React Router
* Context API
* Axios
* HTML
* CSS

## Project Goal

The goal of this project is to build a functional and secure messaging platform that demonstrates:

* Full-stack application development
* Authentication and authorization
* REST API integration
* Database persistence
* Modern frontend architecture

## System Architecture

Frontend (React)
Handles user interface, authentication flow, and messaging interactions.

Backend (Spring Boot)
Provides REST APIs, authentication logic, database access, and message storage.

Database
Stores users, messages, and conversation history.

## Features

User Authentication
Secure registration and login using JWT tokens.

User Directory
Users can view a list of registered users and select a contact to chat with.

Persistent Messaging
Messages are stored in the database and remain available after page refresh.

Chat History
Users can load previous conversations with selected contacts.

Secure Communication
All protected endpoints require authentication.

## Project Structure

CoreConnect Messaging Platform

backend
Spring Boot application handling authentication, messaging APIs, and database interaction

frontend
React application providing the messaging user interface

## How the Application Works

1. User registers or logs in.
2. Backend returns a JWT authentication token.
3. Frontend stores the token and attaches it to API requests.
4. User selects a contact from the sidebar.
5. Frontend fetches conversation history from the backend.
6. Messages are sent via API and stored in the database.

## Future Improvements

* Real-time messaging using WebSockets
* Online/offline status indicators
* Typing indicators
* File and media attachments

## Learning Outcomes

This project demonstrates practical implementation of:

* Full-stack development
* Secure authentication using JWT
* RESTful API design
* Database relationships using JPA
* React state management
* Backend–frontend integration
