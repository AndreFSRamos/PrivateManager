<template>
  <v-card class="card" outlined>
    <v-row justify="center"><h3><p>Quantidade de gastos por categorias</p></h3></v-row>
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-subtitle>
          <Doughnut v-model="chartDataCategories" v-if="loaded" :data="chartDataCategories" :options="chartOptions"/>
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-card-actions>
      <v-row justify="center">
        <v-btn outlined icon text><AddNewCategory :saveItem="save"/></v-btn>
       <div style="width: 50px;"></div>
        <v-btn outlined icon text><DeleteCategory :atualize="initilize" :teste="fullCategories" :delete="deleteItemConfirm" :updateItem="update"/></v-btn>
        <v-btn @click="initilize()" text>teste</v-btn>
      </v-row>
      <div style="height: 50px;"></div>
    </v-card-actions>
  </v-card>
</template>

<script>
  import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
  import { Doughnut } from "vue-chartjs";
  import AddNewCategory from './components/AddNewCategory.vue';
  import DeleteCategory from './components/EditEndDeletecategory.vue';

  ChartJS.register(ArcElement, Tooltip, Legend);

  export default {
    name: "ChartPieQtdPerCategories",
    components: { Doughnut,AddNewCategory, DeleteCategory},
    props:{
      searchDate:String
    },
    data() {
      return {
        dateSearch: this.searchDate,
        fullCategories:[],
        categories: [],
        newCategory:{
          description:"",
          identifyColor:""
        },
        identifyColorsCategories:[],
        chartDataCategories: [],  
        dataValues:[],
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

    methods:{

    async save(item) {
      await  this.$http.post("/tipo_items", item);
      await  this.initilize()
    },

    async deleteItemConfirm(id) {
        await this.$http.delete("/tipo_items/"+id);
        await this.initilize()
    },

    async update(item) {
        await this.$http.put("/tipo_items/" + item.id, item);
        await this.initilize()
    },

     async initilize(){
      this.identifyColorsCategories= []
      this.dataValues=[]
      this.categories=[]
       await this.$http
        .get("/tipo_items")
        .then((response) => {
          this.fullCategories = response.data
          response.data.forEach(async (element) => {
            this.categories.push(element["description"]);
            this.identifyColorsCategories.push(element["identifyColor"])
          });
        })
        .catch(() => {
          this.loaded = false;
        });

        for(let i =0 ; i < this.categories.length; i++){
            await this.$http.get('/items/total/categotry?findReference='+this.categories[i]+'&findDate='+this.searchDate).then((total)=>{
            this.dataValues.push(total.data)
          });
        }

      this.chartDataCategories = {  
        labels: this.categories,
        datasets: [
          {
            data: this.dataValues,
            backgroundColor: this.identifyColorsCategories,
          },
        ],
      };
      }
    },

    async mounted() {
      this.loaded = false;
      await  this.initilize()
      this.loaded = true;
    },
  };
</script>