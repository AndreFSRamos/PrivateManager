<template>
    <div>
        <v-row>
            <v-col cols="12" sm="12">
                <v-card>  
                <h1 style="padding: 15px;">Confira a documentação de nossos end points</h1>
                </v-card>
            </v-col>
        </v-row>
        <iframe src="http://localhost:5001/swagger-ui/index.html#/" width="100%" height="1000px"></iframe>
    </div>
   
</template>

<script>
  export  default{
    data(){
    
    }
  }
</script>