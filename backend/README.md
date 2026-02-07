# QuizAI Backend

A Spring Boot REST API that generates AI-powered quizzes using the Groq API. Create customized quizzes on any topic with adjustable difficulty levels and question formats.

## Technology Stack

- Java 21
- Spring Boot 4.0.2
- Maven
- Groq API (GPT model)
- Lombok, Jackson

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Groq API key from [Groq Console](https://console.groq.com)

## Setup

1. **Set Environment Variable**

Windows PowerShell:
```powershell
$env:GROQ_API="your-groq-api-key-here"
```

2. **Build and Run**

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

Application starts on `http://localhost:8080`

## API Reference

### Generate Quiz

**POST** `/api/v1/quiz/generate-quiz`

**Request:**
```json
{
  "topic": "JavaScript",
  "numberOfQuestions": 5,
  "difficulty": "MEDIUM",
  "numberOfOptions": 4
}
```

**Parameters:**
- `topic` (String) - Quiz subject
- `numberOfQuestions` (Integer) - Number of questions
- `difficulty` (String) - EASY, MEDIUM, or HARD
- `numberOfOptions` (Integer) - Options per question

**Response:**
```json
{
  "questions": [
    {
      "question": "What is a closure in JavaScript?",
      "options": [
        {"text": "A function that has access to variables in its outer scope", "isCorrect": true},
        {"text": "A way to close browser windows", "isCorrect": false},
        {"text": "A type of loop", "isCorrect": false},
        {"text": "A CSS property", "isCorrect": false}
      ]
    }
  ],
  "totalQuestions": 5,
  "difficulty": "MEDIUM"
}
```

## Configuration

`src/main/resources/application.properties`:
```properties
spring.application.name=QuizBackend
app.api.key=${GROQ_API}
```
