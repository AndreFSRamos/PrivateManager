<template>
    <v-card class="card" outlined>
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="text-h5 mb-1">
            Quantidade de gastos por forma de pagamento
        </v-list-item-title>
        <v-list-item-subtitle>
          <Pie v-if="loaded" :data="chartDataPaymentMethods" :options="chartOptions" />
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>
  
<script>
  import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
  import { Pie } from "vue-chartjs";
  ChartJS.register(ArcElement, Tooltip, Legend);
  
  export default {
    name: "ChartPieQtdPerPaymentMethods",
    components: { Pie },
    data() {
      return {
        paymentMethods: [],
        chartDataPaymentMethods: [],
        chartOptions: {
          responsive: true,
          maintainAspectRatio: false,
        },
        loaded: false,
      };
    },
    async mounted() {
      this.loaded = false;
  
      await this.$http
        .get("/Formas_de_pagamento")
        .then((response) => {
          let allpaymentMethods = response.data;
          allpaymentMethods.forEach((element) => {
            this.paymentMethods.push(element["descricao"]);
          });
        })
        .catch(() => {this.loaded = false;});
  
      this.chartDataPaymentMethods = {
        labels: this.paymentMethods,
        datasets: [
          {
            data: [10, 20, 40, 50],
            backgroundColor: ["#05B7F7", "#04D6D6", "#11EDAF", "#04D65F"],
          },
        ],
      };
  
      this.loaded = true;
    },
  };
</script>