<template>
  <div>
    <v-app id="inspire" theme="dark">
      <Sidebar></Sidebar>
    <v-main>
    <Dashboard></Dashboard>
    </v-main>
  </v-app>
  </div>
</template>
<script>
import Dashboard from "./components/Dashboard/dashboard.vue";
import Sidebar from "./components/Dashboard/components/Sidebar.vue";

export default {
  name: 'App',
  components:{
    Dashboard,
    Sidebar
  },

  data: () => ({

  }),
}
</script>

