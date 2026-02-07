# QuizAI

An AI-powered quiz generation application that creates custom quizzes on any topic using the Groq API with GPT models.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Development](#development)
- [Deployment](#deployment)
- [Troubleshooting](#troubleshooting)

## Overview

QuizAI is a full-stack application that generates educational quizzes using AI. Users specify a topic, number of questions, difficulty level, and options per question.

![QuizAI Overview](docs/images/QuizOverview.png)

## Features

- AI-powered quiz generation using Groq API with GPT models
- Customizable topic, question count (1-25), difficulty, and options (2-6)
- Modern Vue.js interface with real-time results
- Visual analytics with charts
- RESTful API with CORS support

## Architecture

```
┌─────────────────┐         ┌─────────────────┐         ┌─────────────────┐
│   Vue.js        │  HTTP   │   Spring Boot   │   API   │   Groq API      │
│   Frontend      │────────>│   Backend       │────────>│   (GPT Model)   │
│   (Port 8081)   │         │   (Port 8080)   │         │                 │
└─────────────────┘         └─────────────────┘         └─────────────────┘
```

## Technologies

### Backend
- Spring Boot 4.0.2 - Java framework for REST API
- Java 21
- Maven - Dependency management
- Jackson - JSON processing
- Groq API - AI quiz generation

### Frontend
- Vue.js 3.2.13
- ApexCharts - Data visualization
- Vue CLI 5.0
- ES6+

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Node.js 14+ and npm
- Groq API Key ([Get one here](https://console.groq.com/))

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/QuizAI.git
cd QuizAI
```

### 2. Backend Setup

```bash
cd backend
mvn clean install
```

### 3. Frontend Setup

```bash
cd frontend
npm install
```

## Configuration

### Backend Configuration

Set up environment variable for API key:

**Windows (PowerShell):**
```powershell
$env:GROQ_API="your-groq-api-key-here"
```

**For permanent setup:**
- Open System Properties → Advanced → Environment Variables
- Add new System Variable: `GROQ_API` with your API key

### Frontend Configuration

The frontend connects to the backend at `http://localhost:8080` by default. Modify `frontend/src/components/QuizService.vue` to change this.

## Running the Application

### Start Backend Server

```bash
cd backend
mvn spring-boot:run
```

The backend will start on **http://localhost:8080**

### Start Frontend Server

```bash
cd frontend
npm run serve
```

The frontend will start on **http://localhost:8081**

### Access the Application

Open your browser: `http://localhost:8081`

## API Documentation

### Generate Quiz Endpoint

**POST** `/api/v1/quiz/generate-quiz`

**Request Body:**
```json
{
  "topic": "Science",
  "numberOfQuestions": 10,
  "difficulty": "medium",
  "numberOfOptions": 4
}
```

**Parameters:**
- `topic` (String): The subject of the quiz
- `numberOfQuestions` (Integer): Number of questions (1-25)
- `difficulty` (String): Difficulty level - `easy`, `medium`, or `hard`
- `numberOfOptions` (Integer): Number of answer options per question (2-6)

**Response:**
```json
{
  "questions": [
    {
      "question": "What is photosynthesis?",
      "options": [
        {"text": "Process of making food in plants", "isCorrect": true},
        {"text": "Process of breathing", "isCorrect": false},
        {"text": "Process of digestion", "isCorrect": false},
        {"text": "Process of reproduction", "isCorrect": false}
      ]
    }
  ],
  "totalQuestions": 10,
  "difficulty": "medium"
}
```

**Status Codes:**
- `200 OK` - Quiz generated successfully
- `500 Internal Server Error` - Error generating quiz

## Development

### Backend

Run tests:
```bash
cd backend
mvn test
```

Build for production:
```bash
mvn clean package
```

### Frontend

Lint and fix:
```bash
cd frontend
npm run lint
```

Build for production:
```bash
npm run build
```

## Deployment

### Backend

Build and deploy:
```bash
cd backend
mvn clean package
java -jar target/QuizBackend-0.0.1-SNAPSHOT.jar
```

Set environment variable on server:
```bash
export GROQ_API=your-api-key-here
```

### Frontend

Build and deploy:
```bash
cd frontend
npm run build
```

Deploy the `dist/` folder to:
- Netlify: Drag and drop
- Vercel: Connect GitHub repo
- GitHub Pages: Push to gh-pages branch

## Troubleshooting

**Backend won't start:**
- Ensure Java 21 is installed: `java -version`
- Check if port 8080 is available
- Verify GROQ_API environment variable is set

**Frontend can't connect to backend:**
- Ensure backend is running on port 8080
- Check CORS settings in `QuizController.java`
- Verify API endpoint URL in frontend code

**API key issues:**
- Ensure GROQ_API environment variable is set correctly
- Verify API key is valid at [Groq Console](https://console.groq.com/)
- Check for any rate limiting on your API key

**Build errors:**
- Frontend: Delete `node_modules` and run `npm install` again
- Backend: Run `mvn clean install -U` to force update dependencies

