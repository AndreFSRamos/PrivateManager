<template>
   <v-card class="card" outlined>
    <v-row justify="center"><h1><p style="font-size: 2rem;">Quantidade de gastos por Forma de Pagamento</p></h1></v-row>
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-subtitle>
          <Doughnut v-if="loaded" :data="chartDataPaymentMethods" :options="chartOptions"/>
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-card-actions>
      <v-row justify="center">
        <v-btn outlined icon text><AddNewPaymentMethods/></v-btn>
       <div style="width: 50px;"></div>
        <v-btn outlined icon text><EditEndDeletePaymentMethods/></v-btn>
      </v-row>
      <div style="height: 50px;"></div>
    </v-card-actions>
  </v-card>
</template>
  
<script>
  import AddNewPaymentMethods from './components/AddNewPaymentMethods.vue';
  import EditEndDeletePaymentMethods from './components/EditEndDeletePaymentMethods.vue';
  import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
  import { Doughnut } from "vue-chartjs";
  ChartJS.register(ArcElement, Tooltip, Legend);
  
  export default {
    name: "ChartPieQtdPerPaymentMethods",
    components: { Doughnut, AddNewPaymentMethods, EditEndDeletePaymentMethods},
    data() {
      return {
        paymentMethods: [],
        identifyColorPaymentMethods:[],
        chartDataPaymentMethods: [],
        chartOptions: {
          responsive: true,
          maintainAspectRatio: false,
        },
        loaded: false,
        dialog:false,
      };
    },

    async mounted() {
      this.loaded = false;
  
      await this.$http
        .get("/Formas_de_pagamento")
        .then((response) => {
          let allpaymentMethods = response.data;
          allpaymentMethods.forEach((element) => {
            this.paymentMethods.push(element["description"]);
            this.identifyColorPaymentMethods.push(element["identifyColor"])
          });
        })
        .catch(() => {this.loaded = false;});
  
      this.chartDataPaymentMethods = {
        labels: this.paymentMethods,
        datasets: [
          {
            data: [10, 20, 40, 50],
            backgroundColor: this.identifyColorPaymentMethods,
          },
        ],
      };
  
      this.loaded = true;
    },
  };
</script>