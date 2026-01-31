<script>
export default {
  name: "QuizConfigurationSummary",

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

  methods: {
    startQuiz() {
      this.$emit('start-quiz');
    }
  }
}
</script>

<template>
  <div id="summary">
    <h2>Quiz Configuration Summary</h2>
    <ul id="summary-list">
      <li><strong>Topic:</strong> {{ topic.toUpperCase() }}</li>
      <li><strong>Number of Questions:</strong> {{ numberOfQuestions }}</li>
      <li><strong>Difficulty Level:</strong> {{ difficulty.toUpperCase() }}</li>
      <li><strong>Number of Options per Question:</strong> {{ numberOfOptions }}</li>
    </ul>
  </div>
  <button class="start-quiz-button" @click="startQuiz">
    Start Quiz
  </button>
</template>

<style scoped>
#summary {
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  padding: 30px;
  border-radius: 15px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
  margin: 20px 0;
}

#summary h2 {
  color: #2c3e50;
  font-size: 1.8em;
  margin-bottom: 20px;
  text-align: center;
  border-bottom: 2px solid #667eea;
  padding-bottom: 15px;
}

#summary-list {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

#summary-list li {
  background: white;
  padding: 15px 20px;
  border-radius: 10px;
  font-size: 1.1em;
  color: #34495e;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

#summary-list li:hover {
  transform: translateX(5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

#summary-list li strong {
  color: #667eea;
  margin-right: 8px;
  font-weight: 600;
}

#summary-list li:nth-child(odd) {
  background: #cfe7ff;
}

.start-quiz-button {
  width: 100%;
  padding: 16px 32px;
  margin-top: 30px;
  background: linear-gradient(135deg, #6e995f 0%, #18c418 100%);
  color: white;
  border: none;
  border-radius: 12px;
  font-size: 1.2em;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
  text-transform: uppercase;
  letter-spacing: 1px;
}

.start-quiz-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(102, 126, 234, 0.6);
}

.start-quiz-button:active {
  transform: translateY(0);
  box-shadow: 0 2px 10px rgba(102, 126, 234, 0.4);
}

@media (max-width: 768px) {
  #summary {
    padding: 20px;
  }

  #summary h2 {
    font-size: 1.4em;
  }

  #summary-list li {
    font-size: 1em;
    padding: 12px 15px;
  }

  .start-quiz-button {
    font-size: 1em;
    padding: 14px 24px;
  }
}
</style>