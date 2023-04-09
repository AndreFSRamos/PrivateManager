<template>
  <v-app>
    <NavAppBar />
    <v-main>
      <ContentPage />
    </v-main>
  </v-app>
</template>
  
  <script>
import ContentPage from "./components/ContentPage.vue";
import NavAppBar from "./components/NavAppBar.vue";
export default {
  name: "HomePage",
  components: {
    ContentPage,
    NavAppBar,
  },
};
</script>