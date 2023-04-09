import Vue from 'vue'
import Router from "vue-router";
import home from "@/components/Pages/Ghaphics/ChartsGraphics.vue";
import ListItem from "@/components/Pages/ListItem/ListView.vue"
import API from "@/components/Pages/API_Swagger/Swagger_api.vue"

Vue.use(Router)

export default new Router({
    routes:[
        {
            path: "/",
            name: "home",
            component: home
        },
       
        {
            path: "/Lista_de_Gastos",
            name: "view",
            component: ListItem
        },
    
        {
            path: "/Apis",
            name: "lista_endpoints",
            component: API
        },
    ]
});

