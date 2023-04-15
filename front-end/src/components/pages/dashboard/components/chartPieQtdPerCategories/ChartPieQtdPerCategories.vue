<template>
  <v-card class="card" outlined>
    <v-row justify="center"><h1><p style="font-size: 2rem;">Quantidade de gastos por categorias</p></h1></v-row>
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-subtitle>
          <Pie v-if="loaded" :data="chartDataCategories" :options="chartOptions"/>
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-card-actions>
      <v-row justify="center">
        <v-btn outlined icon text><AddNewCategory/></v-btn>
       <div style="width: 50px;"></div>
        <v-btn outlined icon text><DeleteCategory/></v-btn>
      </v-row>
      <div style="height: 50px;"></div>
    </v-card-actions>
  </v-card>
</template>

<script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { Pie } from "vue-chartjs";
import AddNewCategory from './components/AddNewCategory.vue';
import DeleteCategory from './components/EditEndDeletecategory.vue';

ChartJS.register(ArcElement, Tooltip, Legend);

export default {
  name: "ChartPieQtdPerCategories",
  components: { Pie,AddNewCategory, DeleteCategory},
  data() {
    return {
      categories: [],
      newCategory:{
        description:"",
        identifyColor:""
      },
      identifyColorsCategories:[],
      chartDataCategories: [],  
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
        layout: {
            padding:{
              top:15,
              bottom:15
            }
        }
      },
      loaded: false,
    };
  },

  async mounted() {
    this.loaded = false;
    await this.$http
      .get("/tipo_items")
      .then((response) => {
        let allCategories = response.data;
        allCategories.forEach((element) => {
          this.categories.push(element["description"]);
          this.identifyColorsCategories.push(element["identifyColor"])
        });
      })
      .catch(() => {
        this.loaded = false;
      });

    this.chartDataCategories = {
      labels: this.categories,
      datasets: [
        {
          data: [10, 20, 40, 50],
          backgroundColor: this.identifyColorsCategories,
        },
      ],
    };
    this.loaded = true;
  },
};
</script>