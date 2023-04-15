<template>
  <div>
    <v-row>
      <v-col>
        <v-card class="card" outlined>
          <v-toolbar flat >
            <v-toolbar-title>Dashboard</v-toolbar-title><v-spacer/><v-text-field
            label="Filled"
            placeholder="Dense & Rounded"
            filled
            rounded
         
          ></v-text-field>
          </v-toolbar>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="6"><ChartPieQtdPerPaymentMethods/></v-col>
      <v-col cols="12" sm="6"><ChartPieQtdPerCategories/></v-col>
    </v-row>
    <v-row>
      <v-col cols="12"><ChartLineTotalValueToMonthPerCategories/></v-col>
    </v-row>
  </div>
</template> 

<script>
  import ChartPieQtdPerCategories from "./components/chartPieQtdPerCategories/ChartPieQtdPerCategories.vue";
  import ChartPieQtdPerPaymentMethods from "./components/chartPieQtdPerPaymentMethods/ChartPieQtdPerPaymentMethods.vue";
  import ChartLineTotalValueToMonthPerCategories from './components/chartlineTotalValuePerMonth/ChartLineTotalValuePerCategories.vue'
  export default {
    components: {
      ChartPieQtdPerCategories,
      ChartPieQtdPerPaymentMethods,
      ChartLineTotalValueToMonthPerCategories,
    },
  };
</script>

<style scoped>
  .card{
    padding: 8px;
  }
</style>
