import Vue from 'vue'
import Router from "vue-router";
import Dashboard from "@/components/pages/dashboard/DashBoard.vue";
import ListItem from "@/components/pages/listItems/ListItems.vue"
import API from "@/components/pages/docAPISwagger/SwaggerApi.vue"

Vue.use(Router)

export default new Router({
    routes:[
        {
            path: "/",
            name: "Dashboard",
            component: Dashboard
        },
        {
            path: "/Lista_de_Gastos",
            name: "ListItems",
            component: ListItem
        },
        {
            path: "/Apis",
            name: "Swagger",
            component: API
        },
    ]
});

