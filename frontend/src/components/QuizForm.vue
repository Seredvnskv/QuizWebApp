<script>
import QuizConfigurationSummary from "@/components/QuizConfigurationSummary.vue";
import QuizService from "@/components/QuizService.vue";

export default {
  name: "QuizForm",
  components: {QuizConfigurationSummary, QuizService},
  data() {
    return {
      topic: '',
      numberOfQuestions: 10,
      difficulty: '',
      numberOfOptions: 4,
      showQuiz: false,
      showQuizService: false,
      showBackButton: true,
      questionsRange: {min: 1, max: 25},
      optionsRange: {min: 2, max: 6}
    }
  },
  computed: {
    isFormValid() {
      return this.topic.trim() !== '' &&
             this.difficulty !== '';
    }
  },
  methods: {
    generateQuiz() {
      if (this.isFormValid) {
        this.showQuiz = true
        window.scrollTo({ top: 0, behavior: 'smooth' })
      }
    },

    backToForm() {
      this.showQuiz = false
      this.showQuizService = false
      window.scrollTo({ top: 0, behavior: 'smooth' })
    },

    startQuiz() {
      this.showQuizService = true
      this.showBackButton = false
      window.scrollTo({ top: 0, behavior: 'smooth' })
    }
  }
}
</script>

<template>
  <div id="app">
    <header class="app-header">
      <h1>QuizGEN</h1>
      <p class="subtitle">Create custom quizzes with AI-powered questions</p>
    </header>

    <main class="app-content">
      <div v-if="!showQuiz" class="config-container">
        <div class="config-card">
          <h2>Configure Your Quiz</h2>
          <p class="description">Fill in the details below to generate a custom quiz</p>

          <form @submit.prevent="generateQuiz" class="quiz-form">
            <div class="form-group">
              <label for="topic">
                Quiz Topic
              </label>
              <input
                  id="topic"
                  v-model="topic"
                  placeholder="Math, History, Science..."
                  class="form-input"
                  required
              />
            </div>

            <div class="form-group">
              <label for="questions">
                Number of Questions
              </label>
              <input
                  id="questions"
                  v-model.number="numberOfQuestions"
                  type="number"
                  :min="questionsRange.min"
                  :max="questionsRange.max"
                  placeholder="e.g., 10"
                  class="form-input"
                  required
              />
              <small class="hint">Between {{questionsRange.min}} and {{questionsRange.max}} questions</small>
            </div>

            <div class="form-group">
              <label for="difficulty">
                Difficulty Level
              </label>
              <select
                  id="difficulty"
                  v-model="difficulty"
                  class="form-select"
                  required
              >
                <option value="" disabled>Select difficulty...</option>
                <option value="easy">Easy 😀</option>
                <option value="medium">Medium 🤔</option>
                <option value="hard">Hard 😮</option>
              </select>
            </div>

            <div class="form-group">
              <label for="options">
                Options per Question
              </label>
              <input
                  id="options"
                  v-model.number="numberOfOptions"
                  type="number"
                  placeholder="e.g., 4"
                  :min="optionsRange.min"
                  :max="optionsRange.max"
                  class="form-input"
                  required
              />
              <small class="hint">Between {{optionsRange.min}} and {{optionsRange.max}} options</small>
            </div>

            <button
                type="submit"
                class="btn-generate"
                :disabled="!isFormValid"
            >
              Generate Quiz
            </button>
          </form>
        </div>
      </div>

      <div v-else class="quiz-container">
        <button v-if="showBackButton" @click="backToForm" class="btn-back">
          Back to Configuration
        </button>

        <QuizConfigurationSummary
            v-if="!showQuizService"
            :topic="topic"
            :number-of-questions="numberOfQuestions"
            :difficulty="difficulty"
            :number-of-options="numberOfOptions"
            @start-quiz="startQuiz"
        />

        <QuizService
            v-if="showQuizService"
            :topic="topic"
            :number-of-questions="numberOfQuestions"
            :difficulty="difficulty"
            :number-of-options="numberOfOptions"
            @reset-quiz="backToForm"
        />

      </div>
    </main>

    <footer class="app-footer">
      <p>Sambor Seredyński</p>
    </footer>
  </div>
</template>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: linear-gradient(135deg, #9d7315 0%, #d12727 100%);
}

.app-header {
  text-align: center;
  padding: 40px 20px;
  color: white;
}

.app-header h1 {
  font-size: 3em;
  margin-bottom: 10px;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
}

.subtitle {
  font-size: 1.2em;
  opacity: 0.95;
}

.app-content {
  flex: 1;
  padding: 20px;
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
}

.config-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.config-card {
  background: white;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 10px 40px rgba(0,0,0,0.2);
  max-width: 600px;
  width: 100%;
  animation: slideIn 0.5s ease-out;
}

.config-card h2 {
  color: #2c3e50;
  font-size: 2em;
  margin-bottom: 10px;
  text-align: center;
}

.description {
  text-align: center;
  color: #666;
  margin-bottom: 30px;
}

.quiz-form {
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-group label {
  font-weight: 600;
  color: #2c3e50;
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 1.05em;
}

.form-input,
.form-select {
  padding: 14px 18px;
  border: 2px solid #e0e0e0;
  border-radius: 10px;
  font-size: 16px;
  transition: all 0.3s ease;
  font-family: inherit;
}

.form-input:focus,
.form-select:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.form-select {
  cursor: pointer;
  background-color: white;
}

.hint {
  color: #999;
  font-size: 0.9em;
  margin-top: -4px;
}

.btn-generate {
  padding: 16px 32px;
  background: linear-gradient(135deg, #b8631c 0%, #b68719 100%);
  color: white;
  border: none;
  border-radius: 12px;
  font-size: 1.2em;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  margin-top: 10px;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
}

.btn-generate:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(102, 126, 234, 0.6);
}

.btn-generate:active:not(:disabled) {
  transform: translateY(0);
}

.btn-generate:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.btn-back {
  padding: 10px 20px;
  background: rgba(255, 255, 255, 0.9);
  color: #2c3e50;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  font-size: 0.95em;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
  margin-bottom: 20px;
  display: inline-flex;
  align-items: center;
  gap: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.btn-back:hover {
  background: white;
  transform: translateX(-4px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  border-color: rgba(0, 0, 0, 0.2);
}

.btn-back:active {
  transform: translateX(-2px);
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.quiz-container {
  background: white;
  padding: 30px;
  border-radius: 20px;
  box-shadow: 0 10px 40px rgba(0,0,0,0.2);
  animation: slideIn 0.5s ease-out;
}

.app-footer {
  text-align: center;
  padding: 20px;
  color: white;
  opacity: 0.9;
}

@media (max-width: 768px) {
  .app-header h1 {
    font-size: 2em;
  }

  .subtitle {
    font-size: 1em;
  }

  .config-card {
    padding: 25px;
  }

  .config-card h2 {
    font-size: 1.5em;
  }

  .btn-generate {
    font-size: 1em;
  }
}
</style>