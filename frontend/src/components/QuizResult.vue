<script>
import ApexCharts from 'apexcharts'

export default {
  name: "QuizResult",
  props: {
    results: {
      type: Object,
      required: true
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.renderChart();
    });
  },
  methods: {
    renderChart() {
      const options = {
        chart: {
          type: 'donut',
          height: 400
        },
        series: [this.results.correct, this.results.wrong, this.results.unanswered],
        labels: ['Correct', 'Wrong', 'Unanswered'],
        colors: ['#28a745', '#dc3545', '#ffc107'],
        legend: {
          position: 'bottom',
          fontSize: '16px'
        },
        dataLabels: {
          enabled: true,
          style: {
            fontSize: '16px',
            fontWeight: 'bold'
          }
        },
        plotOptions: {
          pie: {
            donut: {
              size: '65%',
              labels: {
                show: true,
                total: {
                  show: true,
                  label: 'Total Questions',
                  fontSize: '18px',
                  fontWeight: 600,
                  formatter: () => this.results.totalQuestions
                }
              }
            }
          }
        },
        tooltip: {
          y: {
            formatter: (val) => `${val} question${val !== 1 ? 's' : ''}`
          }
        }
      };

      const chart = new ApexCharts(document.querySelector("#chart"), options);
      chart.render();
    }
  }
}
</script>

<template>
  <h2>Quiz Results</h2>

  <div class="chart-container">
    <div id="chart"></div>
  </div>

  <div class="result-summary">
    <h3>Summary</h3>
    <p>You answered <strong>{{ results.correct }}</strong> out of <strong>{{ results.totalQuestions }}</strong> questions correctly.</p>
    <p v-if="results.unanswered > 0">You left <strong>{{ results.unanswered }}</strong> question(s) unanswered.</p>
  </div>
</template>

<style scoped>
.chart-container {
  max-width: 500px;
  margin: 0 auto 40px;
  padding: 20px;
  border-radius: 12px;
}

.result-summary {
  padding: 25px;
  border-radius: 12px;
  margin-top: 30px;
}

.result-summary h3 {
  color: #2c3e50;
  margin-bottom: 15px;
  font-size: 1.5em;
}

.result-summary p {
  color: #666;
  font-size: 1.1em;
  line-height: 1.6;
  margin-bottom: 10px;
}

.result-summary strong {
  color: #667eea;
  font-weight: 600;
}
</style>

