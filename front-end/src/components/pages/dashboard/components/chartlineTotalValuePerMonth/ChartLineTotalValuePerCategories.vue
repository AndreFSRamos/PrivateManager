<template>
  <v-card class="card" outlined style="height: 500px;">
    <LineChartGenerator v-model="chartDataCategories" v-if="loaded" :data="chartDataCategories" :options="chartOptions" />
  </v-card>
</template>

<script>
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend, } from "chart.js";
import { Line as LineChartGenerator } from "vue-chartjs";
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);
export default {
  name: "ChartLine",
  props:{
    yearAndMonth : String
  },
  components: { LineChartGenerator },
  data() {
    return {
      valuesDay: [],
      days: [],
      chartDataCategories: [],
      chartOptions: {
        responsive: true,
    
        maintainAspectRatio: false,
        legend: {
          labels: {
            boxWidth: 10,
          },
          position: "top",
        },
        animation: {
          duration: 2000,
          easing: "easeInOutQuart",
        },
      },
      loaded: false,
    };
  },

  methods: {
    async initilize() {
      this.valuesDay = [],
      this.days = [],
      await this.$http
        .get("/items/total/totalPerDayTheMonth?yearAndMonth="+this.yearAndMonth)
        .then((response) => {
         response.data.forEach(element => {
            this.valuesDay.push(element)
         });
         
        })
        .catch(() => {
          this.loaded = false;
        });

        for (let i = 1; i <= 31; i++) {
          this.days.push(i)
        }

        this.chartDataCategories = {
        labels: this.days,
        datasets: [
          {
            label: 'TOTAL DE GASTOS POR DIA',
            data: this.valuesDay,
            backgroundColor: "rgba(224, 248, 255, 0.4)",
            borderColor: "#5cddff",
            lineTension: 0.3,
            pointBackgroundColor: "#5cddff",
          },
        ],
      }
    }
  },

  async mounted() {
    this.loaded = false;
    await this.initilize()
    this.loaded = true;
  },

};
</script>