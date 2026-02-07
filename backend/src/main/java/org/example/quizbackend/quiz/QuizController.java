package org.example.quizbackend.quiz;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quiz")
@CrossOrigin(origins = "*")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/generate-quiz")
    public ResponseEntity<String> generateQuiz(@RequestBody QuizRequest quizRequest) {
        try {
            JsonNode quiz = quizService.generateQuiz(quizRequest.getTopic(), quizRequest.getNumberOfQuestions(), quizRequest.getDifficulty(), quizRequest.getNumberOfOptions());
            return ResponseEntity.ok(quiz.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating quiz: " + e.getMessage());
        }
    }
}
