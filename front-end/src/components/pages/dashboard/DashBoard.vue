<template>
  <div>
    <v-row>
      <v-col cols="12" sm="12">
        <v-card>
          <v-toolbar flat>
            <v-toolbar-title>Dashboard</v-toolbar-title>
          </v-toolbar>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="6">
        <ChartPieQtdPerPaymentMethods />
      </v-col>
      <v-col cols="12" sm="6">
        <ChartPieQtdPerCategories />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <ChartLineTotalValueToMonthPerCategories/>
      </v-col>
    </v-row>
  </div>
</template> 

<script>
import ChartPieQtdPerCategories from "./components/ChartPieQtdPerCategories.vue";
import ChartPieQtdPerPaymentMethods from "./components/ChartPieQtdPerPaymentMethods.vue";
import ChartLineTotalValueToMonthPerCategories from './components/ChartLineTotalValuePerCategories.vue'
export default {
  components: {
    ChartPieQtdPerCategories,
    ChartPieQtdPerPaymentMethods,
    ChartLineTotalValueToMonthPerCategories,
  },
};
</script>

<style>
  .card{
    padding: 14px;
  }
</style>
