<template>
  <div>
    <v-row>
      <v-col justify="center">
        <v-card outlined>
          <v-row justify="center" class="d-flex align-center">
            <v-col cols="0" sm="10">
              <h1 style=" padding: 0px 0px 0px 25px;">Dashboard</h1>
            </v-col>
            <v-col cols="12" sm="2" class="d-flex align-center">
              <v-menu ref="menu" v-model="menu" :close-on-content-click="false" :return-value.sync="date"
                transition="scale-transition" offset-y max-width="290px" min-width="auto">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field style="padding: 25px 25px 0px 0px;" v-model="date" label="Consulta por mês" prepend-icon="mdi-calendar" readonly v-bind="attrs" v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="date" type="month" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu = false">
                    Cancel
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.menu.save(date)">
                    OK
                  </v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="6">
        <ChartPieQtdPerPaymentMethods :searchDate="date"/>
      </v-col>
      <v-col cols="12" sm="6">
        <ChartPieQtdPerCategories :searchDate="date"/>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <ChartLineTotalValueToMonthPerCategories :yearAndMonth="date"/>
      </v-col>
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
  data() {
    return {
      date: new Date().toISOString().substr(0, 7),
      menu: false,
      modal: false,
    }
  }
};
</script>

<style scoped>
.card {
  padding-top: 16px;
  padding-bottom: 16px;
}
</style>
