<script>
import QuizCards from "@/components/QuizCards.vue";
import QuizResult from "@/components/QuizResult.vue";

export default {
  name: "QuizService",
  components: {QuizResult, QuizCards},
  props: {
    topic: {
      type: String,
      required: true
    },
    numberOfQuestions: {
      type: Number,
      required: true
    },
    difficulty: {
      type: String,
      required: true
    },
    numberOfOptions: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      questions: [],
      loading: false,
      error: null,
      generated: false,
      finished: false,
      quizResults: null
    }
  },
  mounted() {
    this.getQuestions();
  },

  methods: {
    getQuestions() {
      this.loading = true;
      this.error = null;

      const url = "http://localhost:8080/api/v1/quiz/generate-quiz";
      const payload = {
        topic: this.topic,
        numberOfQuestions: this.numberOfQuestions,
        difficulty: this.difficulty.toUpperCase(),
        numberOfOptions: this.numberOfOptions
      };

      fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      })
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        this.questions = data.questions;
        this.loading = false;
        this.generated = true;
        console.log("Quiz Questions:", this.questions);
      })
      .catch(error => {
        console.error("Error fetching quiz questions:", error);
        this.error = error.message;
        this.loading = false;
      });
    },
    handleQuizFinished(results) {
      this.quizResults = results;
      this.finished = true;
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
  }
}
</script>

<template>
  <div v-if="!generated" class="quiz-service">
    <div v-if="loading" class="loading">
      <div class="spinner"></div>
      <p>Generating your quiz...</p>
    </div>

    <div v-else-if="error" class="error">
      <p>Error: {{ error }}</p>
      <button @click="getQuestions" class="retry-btn">Try Again</button>
    </div>
  </div>

  <div v-else>
    <quiz-cards
        v-if="!finished"
        :questions="questions"
        @quiz-finished="handleQuizFinished"
    />

    <quiz-result
        v-if="finished"
        :results="quizResults"
    />
  </div>
</template>

<style scoped>
.quiz-service {
  width: 100%;
}

.loading {
  text-align: center;
  padding: 60px 20px;
}

.spinner {
  border: 4px solid #f3f3f3;
  border-top: 4px solid #667eea;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  animation: spin 1s linear infinite;
  margin: 0 auto 20px;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.loading p {
  font-size: 1.2em;
  color: #666;
}

.error {
  text-align: center;
  padding: 40px 20px;
  color: #e74c3c;
}

.error p {
  font-size: 1.2em;
  margin-bottom: 20px;
}

.retry-btn {
  padding: 12px 24px;
  background: #667eea;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 1em;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.retry-btn:hover {
  background: #764ba2;
  transform: translateY(-2px);
}
</style>