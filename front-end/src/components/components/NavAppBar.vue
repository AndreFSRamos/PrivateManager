<template>
<div>
    <v-navigation-drawer v-model="drawer" app clipped  mini-variant> 
        <div class="mb-9"></div>
        <router-link :to="{name:'Dashboard'}"><v-btn icon="" class="d-block text-center mx-auto mb-9"><Dashborad></Dashborad></v-btn></router-link> 
        <router-link :to="{name:'ListItems'}"><v-btn icon="" class="d-block text-center mx-auto mb-9"><List></List></v-btn></router-link> 
        <router-link :to="{name:'Swagger'}"> <v-btn icon="" class="d-block text-center mx-auto mb-9"><Api/></v-btn></router-link> 
    </v-navigation-drawer>
    <v-app-bar app clipped-left >
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        <v-app-bar-title class="headline texxt-uppercase">
            <span>Gestor </span>
            <span class="font-weight-light">Pessoal</span>
        </v-app-bar-title>
        <v-spacer></v-spacer>
        <v-btn icon="" size="large" @click="$vuetify.theme.dark = !$vuetify.theme.dark"><IconTheme></IconTheme></v-btn>
    </v-app-bar>
</div>
</template>

<script>
import IconTheme from 'vue-material-design-icons/Brightness4.vue';
import Dashborad from 'vue-material-design-icons/ViewDashboard.vue';
import List from 'vue-material-design-icons/ViewList.vue';
import Api from 'vue-material-design-icons/Api.vue';
export default {
  name: "NavAppBar",
  data: () => ({drawer: null}),
  components:{IconTheme, Dashborad, List, Api}
};
</script>
