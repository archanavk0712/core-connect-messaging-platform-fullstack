# CoreConnect Messaging Platform – Frontend

This repository contains the frontend implementation of the CoreConnect Messaging Platform built using React.

The frontend provides the user interface for authentication, user interaction, and messaging functionality.

## Technology Stack

React.js
React Router
Axios
Context API
HTML
CSS

## Features

* User login and registration
* Secure authentication using JWT tokens
* Contact list with search functionality
* Messaging interface for sending and receiving messages
* Persistent login using local storage
* Responsive layout

## UI Components

Login Page
Handles authentication and redirects authenticated users to the dashboard.

Dashboard
Displays the messaging interface.

Sidebar

* Contact list
* Search functionality

Chat Window

* Contact header
* Scrollable message area
* Input field for sending messages

Message Bubble

* Sent messages aligned to the right
* Received messages aligned to the left

## State Management

The application uses React Context API to manage global authentication state.

AuthContext stores:

* JWT token
* Logged-in user details

LocalStorage is used to maintain login state after page refresh.

## API Integration

Axios is used to communicate with the backend APIs.

An Axios interceptor automatically attaches the JWT token to every request:

Authorization: Bearer <token>

## Running the Frontend

Clone the repository

git clone <frontend-repository-url>

Install dependencies

npm install

Run the development server

npm run dev

The application will run on

http://localhost:5173
