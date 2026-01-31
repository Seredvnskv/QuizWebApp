package org.example.quizbackend.quiz;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizRequest {
    private String topic;
    private QuizDifficulty difficulty;
    private Integer numberOfQuestions;
    private Integer numberOfOptions;
    private Boolean multipleChoice;
}
