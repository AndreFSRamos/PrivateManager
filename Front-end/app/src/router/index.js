import { createRouter, createWebHistory } from "vue-router";
import home from "@/components/Pages/Ghaphics/Graphics.vue";
import ListItem from "@/components/Pages/ListItem/ListItem.vue";
import API from "@/components/Pages/API/api.vue";


const routes =[
    {
        path: "/Graficos",
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

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;