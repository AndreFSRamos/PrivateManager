<template>
  <v-card class="card" outlined>

    <template>
      <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="400px">
          <v-card>
            <v-card-title>
              <span class="text-h5">Nova Forma de Pagamento</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field v-model="newPaymentMethod.description" label="Descrição *" required></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-color-picker v-model="newPaymentMethod.identifyColor" dot-size="25" swatches-max-height="200" required></v-color-picker>
                  </v-col>
                </v-row>
              </v-container>
              <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
              <v-spacera/>
              <v-btn color="blue darken-1" text @click="dialog = false">
                Close
              </v-btn>
              <v-btn color="blue darken-1" text @click="save">
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </template>
  
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="text-h5 mb-1">
          Quantidade de gastos por forma de pagamento
        </v-list-item-title>
        <v-list-item-subtitle>
          <Pie v-if="loaded" :data="chartDataPaymentMethods" :options="chartOptions"/>
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

    <v-card-actions>
      <v-row>
        <v-col cols="3" sm="1">
          <v-btn outlined icon text @click="add">
            <IconAdd/>
          </v-btn>
        </v-col>
        <v-col cols="3" sm="1">
          <v-btn outlined icon text>
            <IconEdit/>
          </v-btn>
        </v-col>
        <v-col cols="3" sm="1">
          <v-btn outlined icon text>
            <IconDelete/>
          </v-btn>
        </v-col>
        <v-col cols="3" sm="1">
          <v-btn outlined icon text>
            <IconReload/>
          </v-btn>
        </v-col>
      </v-row>
    </v-card-actions>
  </v-card>
  
</template>
  
<script>
import IconAdd from "vue-material-design-icons/Plus.vue";
import IconEdit from "vue-material-design-icons/Pencil.vue";
import IconDelete from "vue-material-design-icons/Delete.vue";
import IconReload from "vue-material-design-icons/Reload.vue";
  import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
  import { Pie } from "vue-chartjs";
  ChartJS.register(ArcElement, Tooltip, Legend);
  
  export default {
    name: "ChartPieQtdPerPaymentMethods",
    components: { Pie,IconAdd, IconEdit, IconDelete, IconReload },
    data() {
      return {
        paymentMethods: [],
        newPaymentMethod:{
        description:"",
        identifyColor:""
      },
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

    methods:{
      add(){
        this.dialog  = true;
      },

      save(){
        this.$http.post('/Formas_de_pagamento',this.newPaymentMethod).then(()=>{}).catch(()=>{})
        this.dialog = false;
      }
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