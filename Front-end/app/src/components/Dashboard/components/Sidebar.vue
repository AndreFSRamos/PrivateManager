<template>
   <v-navigation-drawer model-value class="pt-10" rail >
        <router-link :to="{name:'home'}"> <v-avatar size="" class="d-block text-center mx-auto mb-9"><img src="../../../assets/images/chart.png" alt=""></v-avatar></router-link> 
        <router-link :to="{name:'view'}"> <v-avatar  size="" class="d-block text-center mx-auto mb-9"><img src="../../../assets/images/list.png" alt=""></v-avatar></router-link> 
        <router-link :to="{name:'lista_endpoints'}"> <v-avatar  size="" class="d-block text-center mx-auto mb-9"><img src="../../../assets/images/api.png" alt=""></v-avatar></router-link> 
    </v-navigation-drawer>
</template>

<script>
    export default{
        name:"SideBar"
    }
</script>