<script>
export default {
  name: "QuizCards",
  props: {
   questions: {
      type: Array,
      required: true
   }
  },
  data() {
    return {
      shuffledQuestions: [],
      currentQuestionIndex: 0,
      selectedAnswers: {}
    }
  },
  mounted() {
    this.shuffleQuestions();
    console.log(this.shuffledQuestions);
  },
  methods: {
    shuffleQuestions() {
      this.shuffledQuestions = [...this.questions].sort(() => Math.random() - 0.5)
          .map(question => {
            const shuffledOptions = [...question.options].sort(() => Math.random() - 0.5);
            return {
              ...question,
              options: shuffledOptions
            };
          });
    },

    nextQuestion() {
      if (this.currentQuestionIndex < this.shuffledQuestions.length - 1) {
        this.currentQuestionIndex++;
      }
    },

    previousQuestion() {
      if (this.currentQuestionIndex > 0) {
        this.currentQuestionIndex--;
      }
    },

    selectAnswer(optionIndex) {
      this.selectedAnswers = {
        ...this.selectedAnswers,
        [this.currentQuestionIndex]: optionIndex
      };
    },

    submitQuiz() {
      let score = 0;

      this.shuffledQuestions.forEach((question, index) => {
        if (question.options[this.selectedAnswers[index]]?.isCorrect) {
          score++;
        }
      });

      console.log(score);
    }
  }
}
</script>

<template>
  <div class="quiz-cards">
    <div v-if="shuffledQuestions.length > 0" class="quiz-card">
      <div class="progress-bar">
        <span>Question {{ currentQuestionIndex + 1 }} of {{ shuffledQuestions.length }}</span>
        <div class="progress">
          <div
              class="progress-fill"
              :style="{ width: ((currentQuestionIndex + 1) / shuffledQuestions.length * 100) + '%' }"
          ></div>
        </div>
      </div>

      <h3>Question {{ currentQuestionIndex + 1 }}:</h3>
      <p class="question-text">{{ shuffledQuestions[currentQuestionIndex].question }}</p>

      <ul class="options-list">
        <li
            v-for="(option, index) in shuffledQuestions[currentQuestionIndex].options"
            :key="index"
            class="option-item"
        >
          <button class="option-btn"
                  :class="{ selected: selectedAnswers[currentQuestionIndex] === index }"
                  @click="selectAnswer(index)">
            <span class="option-letter">{{ String.fromCharCode(65 + index) }}</span>
            {{ option.text }}
          </button>
        </li>
      </ul>

      <div class="navigation-buttons">
        <button
            @click="previousQuestion"
            :disabled="currentQuestionIndex === 0"
            class="nav-btn prev-btn"
        >
          ← Previous
        </button>

        <button
            v-if="currentQuestionIndex < shuffledQuestions.length - 1"
            @click="nextQuestion"
            class="nav-btn next-btn"
        >
          Next →
        </button>

        <button
            v-else
            @click="submitQuiz"
            class="nav-btn submit-btn"
        >
          Submit Quiz
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.quiz-cards {
  max-width: 800px;
  margin: 0 auto;
}

.quiz-card {
  background: white;
  padding: 30px;
  border-radius: 15px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

.progress-bar {
  margin-bottom: 30px;
}

.progress-bar span {
  display: block;
  text-align: center;
  margin-bottom: 10px;
  font-weight: 600;
  color: #667eea;
}

.progress {
  height: 8px;
  background: #e0e0e0;
  border-radius: 10px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background: linear-gradient(90deg, #309644 0%, #0bd450 100%);
  transition: width 0.3s ease;
}

.question-text {
  font-size: 1.3em;
  color: #2c3e50;
  margin: 20px 0;
  font-weight: 500;
}

.options-list {
  list-style: none;
  padding: 0;
  margin: 30px 0;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.option-btn {
  width: 100%;
  padding: 15px 20px;
  background: white;
  border: 2px solid #e0e0e0;
  border-radius: 10px;
  font-size: 1.05em;
  cursor: pointer;
  transition: all 0.3s ease;
  text-align: left;
  display: flex;
  align-items: center;
  gap: 15px;
}

.option-btn:hover:not(.selected) {
  border-color: #1267bb;
  background: #f8f9ff;
  transform: translateX(5px);
}

.selected {
  border-color: #1267bb;
  background: #d7e8f6;
}

.option-letter {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 32px;
  height: 32px;
  background: #1e55c1;
  color: white;
  border-radius: 50%;
  font-weight: 600;
  flex-shrink: 0;
}

.navigation-buttons {
  display: flex;
  gap: 15px;
  margin-top: 30px;
}

.nav-btn {
  flex: 1;
  padding: 14px 24px;
  border: none;
  border-radius: 10px;
  font-size: 1.05em;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.prev-btn {
  background: #f0f0f0;
  color: #666;
}

.prev-btn:hover:not(:disabled) {
  background: #e0e0e0;
}

.prev-btn:disabled {
  opacity: 0.3;
  cursor: not-allowed;
}

.next-btn {
  background: linear-gradient(135deg, #94a4f3 0%, #1e55c1 100%);
  color: white;
}

.next-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
}

.submit-btn {
  background: linear-gradient(135deg, #28a745 0%, #20c997 100%);
  color: white;
}

.submit-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(40, 167, 69, 0.4);
}
</style>