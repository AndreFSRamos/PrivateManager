<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="400px">
      <template v-slot:activator="{ on, attrs }"><v-btn fab v-bind="attrs" v-on="on"><IconAdd/></v-btn></template>
      <v-card>
        <v-card-title><span class="text-h5">Adiconar nova categoria</span></v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="newCategory.description" label="Descrição *" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-color-picker v-model="newCategory.identifyColor" dot-size="25" swatches-max-height="200" required/>
              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer/>
          <v-btn  color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="save">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import IconAdd from "vue-material-design-icons/Plus.vue";
export default {
  components:{IconAdd},
  name: "AddNewCategory",
  data() {
    return {
      newCategory: {
        description: "",
        identifyColor: "",
      },
      dialog: false,
    };
  },
  methods: {
    save() {
      this.$http.post("/tipo_items", this.newCategory);
      this.dialog = false;
    },
    showAdd(){
      this.dialog  = true;
    },
  },
};
</script>