<template>
  <v-card class="card" outlined>
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="text-h5 mb-1">
          Quantidade de gastos por categorias
        </v-list-item-title>
        <v-list-item-subtitle>
          <Pie
            v-if="loaded"
            :data="chartDataCategories"
            :options="chartOptions"
          />
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-card-actions>
      <v-row>
        <v-col cols="3" sm="1">
          <v-btn outlined icon text>
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
  name: "ChartPieQtdPerCategories",
  components: { Pie, IconAdd, IconEdit, IconDelete, IconReload},
  data() {
    return {
      categories: [],
      chartDataCategories: [],  
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
      .get("/tipo_items")
      .then((response) => {
        let allCategories = response.data;
        allCategories.forEach((element) => {
          this.categories.push(element["descricao"]);
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
          backgroundColor: ["#05B7F7", "#04D6D6", "#11EDAF", "#04D65F"],
        },
      ],
    };

    this.loaded = true;
  },
};
</script>