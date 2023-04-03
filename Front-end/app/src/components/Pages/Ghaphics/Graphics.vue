<template>
  <div>
    <v-row>
      <v-col cols="12" sm="12">
        <v-card>  
          <h1 style="padding: 15px;">Dash-Board de Gastos</h1>
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12" sm="4">
        <v-card >
          <v-progress-circular :rotate="360" :size="200" :width="15" :model-value="value" color="grey">{{ value }}</v-progress-circular>  
        </v-card>
      </v-col>

      <v-col cols="12" sm="4">
        <v-card >
          <v-progress-circular :rotate="360" :size="200" :width="15" :model-value="value" color="grey">{{ value }}</v-progress-circular>  
        </v-card>
      </v-col>

      <v-col cols="12" sm="4">
        <v-card >
          <v-progress-circular :rotate="360" :size="200" :width="15" :model-value="value" color="grey">{{ value }}</v-progress-circular>  
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12" sm="6">
        <v-card> <Bar id="my-chart-id" :options="chartOptions" :data="chartData" style="height: 400px;"/></v-card>
      </v-col>
      <v-col cols="12" sm="6">
        <v-card> <Bar id="my-chart-id" :options="chartOptions" :data="chartData" style="height: 400px;"/></v-card>
      </v-col>
    </v-row>
  </div>
</template> 

<script>
import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

     
  export default{
      name:"Graphics",
      components:{Bar},
      data () {
        return {
          interval: {},
          value: 0,
          chartData: {
            labels: [ 'Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
            datasets: [ {data: [10, 20, 30, 40,50,60,70,80,90,100,110,120], backgroundColor: 'green'} ]
          },
          chartOptions: {
            responsive: true,
          }
        }
      },
      mounted () {
        this.interval = setInterval(() => {
          if (this.value === 100) {
            return (this.value = 0)
          }
          this.value += 10
        }, 1000)
      },
      
  }
</script>


<style scoped>
.v-progress-circular {
  margin: 1rem;
}

.tab-graphic{
  margin-top: 20px;
}

</style>