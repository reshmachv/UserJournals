User Journal Project
*****

User Journal is a learning project designed to allow users to record and analyze their daily thoughts and feelings. The project includes user authentication using Spring Security, REST APIs for data management, and sentiment analysis for journal entries.

## Features

- **User Authentication**: Secure login and registration using Spring Security.
- **REST API**: CRUD operations for journal entries.
- **Sentiment Analysis**: Analyze the sentiment of journal entries.

## Table of Contents

1. [Installation](#installation)
2. [Usage](#usage)
3. [API Endpoints](#api-endpoints)
4. [Technologies Used](#technologies-used)
5. [Contributing](#contributing)
6. [License](#license)
7. [Acknowledgements](#acknowledgements)

## Installation

### Prerequisites

- Java 11+
- Maven
- Mongodb
- Docker (optional, for containerization)
- Postman



## Usage
- User Registration and Login
- Register a new user by accessing the registration endpoint.
- Log in with your credentials to access the journal features.
###### Journal Entries
-Create, read, update, and delete journal entries via the REST API.
###### Sentiment Analysis
- Sentiment analysis is automatically performed on each journal entry to categorize it as positive, neutral, or negative.
### API Endpoints
##### Authentication
- POST /api/auth/register: Register a new user.
- POST /api/auth/login: Authenticate a user and return a JWT token.
##### Journal Entries
- GET /api/journal: Get all journal entries for the authenticated user.
- POST /api/journal: Create a new journal entry.
- GET /api/journal/{id}: Get a specific journal entry.
- PUT /api/journal/{id}: Update a journal entry.
- DELETE /api/journal/{id}: Delete a journal entry.
#### Technologies Used
- Spring Boot: Backend framework.
- Spring Security: Authentication and authorization.
- JWT: JSON Web Tokens for securing the API.
- REST API: For CRUD operations.
- MongoDb: Database for storing user data and journal entries.
- Docker: (Optional) For containerizing the application.
- Sentiment Analysis: Using a sentiment analysis library (e.g., TextBlob, VADER).
