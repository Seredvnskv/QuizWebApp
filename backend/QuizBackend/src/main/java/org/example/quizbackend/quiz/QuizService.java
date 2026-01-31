package org.example.quizbackend.quiz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class QuizService {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${app.api.key}")
    private String apiKey;

    public JsonNode generateQuiz(String topic, Integer numberOfQuestions, QuizDifficulty difficulty, Integer numberOfOptions) throws IOException, InterruptedException {
        String requestBody = String.format("""
        {
          "messages": [
            {
              "role": "user",
              "content": "Give me a quiz in json format about %s consisting of %d questions which difficulty should be %s, and each question should have number of options equal to %d. Json format should be {questions: [{question: string, options: [text: string, isCorrect: boolean}], totalQuestions: number, difficulty: string}}"
            }
          ],
          "model": "openai/gpt-oss-120b",
          "temperature": 1,
          "max_completion_tokens": 8192,
          "top_p": 1,
          "stream": false,
          "response_format": {"type": "json_object"}
        }
        """, topic, numberOfQuestions, difficulty.toString(), numberOfOptions);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode jsonNode = objectMapper.readTree(response.body());
        String quizContent = jsonNode.get("choices").get(0).path("message").path("content").asText();
        return objectMapper.readTree(quizContent);
    }
}
