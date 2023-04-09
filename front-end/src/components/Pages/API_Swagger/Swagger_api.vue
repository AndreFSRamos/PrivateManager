<template>
  <div>
    <v-row>
      <v-col cols="12" sm="12">
        <v-card>
          <v-toolbar flat>
            <v-toolbar-title>
              Confira a documentação de nossos end points</v-toolbar-title>
          </v-toolbar>
        </v-card>
      </v-col>
    </v-row>
    <iframe class="frame" frameborder="0" scrolling="true" 
      src="http://localhost:5001/swagger-ui/index.html#/"
 
    ></iframe>
  </div>
</template>

<script>
  export default{}
</script>

<style scoped>
  .frame{
    height: 1500px;
    width: 100%;
  }
</style>

