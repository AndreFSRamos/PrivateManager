<template v-if="true">
    <v-row justify="center">
      <v-dialog  persistent max-width="400px">
        <v-card>
          <v-card-title>
            <span class="text-h5">Nova Categoria</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field v-model="newCategory.description" label="Descrição *" required></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-color-picker v-model="newCategory.identifyColor" dot-size="25" swatches-max-height="200" required></v-color-picker>
                </v-col>
              </v-row>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text>
              Close
            </v-btn>
            <v-btn color="blue darken-1" text @click="save">
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </template>

<script>
    export default {
        name: "AddNewCategory",
    
        data() {
            return {
                newCategory:{
                description:"",
                identifyColor:""
                },
            };
        },
        methods:{
            save(){
                this.$http.post('/tipo_items',this.newCategory).then(()=>{}).catch(()=>{})
            }
        },
    };
  </script>