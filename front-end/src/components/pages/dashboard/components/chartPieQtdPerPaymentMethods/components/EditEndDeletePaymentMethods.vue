<template>
    <v-dialog v-model="dialog" persistent max-width="500px">
        <template v-slot:activator="{ on, attrs }">
            <v-btn fab v-bind="attrs" v-on="on"><IconDelete/></v-btn>
        </template>

        <v-card>
            <v-card-title><span class="text-h5">Editar & Deletar</span></v-card-title>

            <v-data-table :headers="header" :items="categories" :items-per-page="5">
                <template v-slot:[`item.identifyColor`]="{ item }">
                    <v-chip :color="item.identifyColor"> {{ item.identifyColor }} </v-chip>
                </template>
                <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="setEdit(item)">mdi-pencil</v-icon>
                    <v-icon small @click="deleteItemConfirm(item.id)"> mdi-delete </v-icon>
                </template>
                <template v-slot:no-data><v-btn @click="initialize"> Atualizar </v-btn></template>
            </v-data-table>
            <v-expand-transition>
                <v-card-text  v-if="showEdit">
                    <v-container>
                        <v-row>
                            <v-col cols="12">
                                <v-text-field v-model="newCategory.description" label="Descrição *" required></v-text-field>
                            </v-col>
                            <v-col cols="12">
                                <v-color-picker v-model="newCategory.identifyColor" dot-size="25" swatches-max-height="200" width="500px" required/>
                            </v-col>
                        </v-row>
                    </v-container>
                    <small>*indicates required field</small>
                </v-card-text>
            </v-expand-transition>
            <v-card-actions>    
                <v-spacer/>
                <v-btn  color="blue darken-1" text @click="close">Close</v-btn>
                <v-btn color="blue darken-1" v-if="showEdit" text @click="update">Save</v-btn>
            </v-card-actions>
        </v-card>

    </v-dialog>
</template>
  
<script>
import IconDelete from "vue-material-design-icons/Delete.vue";
export default {
  components: { IconDelete },
  name: "DeleteCategory",
  data() {
    return {
    header: [
        { text: "DESCRIÇÃO", value: "description" },
        { text: "Cor", value: "identifyColor" },
        { text: "", value: "actions" },
    ],
    categories: [],
    newCategory: {
        id: "",
        description: "",
        identifyColor: "",
    },
    dialog: false,
    dialogDelete: false,
    showEdit:false,
    editedIndex: -1,
    };
  },

  methods: {

    setEdit(item){
        this.showEdit = true
        this.newCategory = item
    },

    deleteItemConfirm(id) {
      this.$http.delete("/tipo_items/"+id).then(()=>{});
      this.dialogDelete = false
    },

    closeDelete() {
      this.dialogDelete = false
      this.showEdit  =false
      this.editedIndex = -1;
    },

    close(){
        this.showEdit = false
        this.dialog = false;
    },

    update() {
      this.$http.put("/tipo_items/" + this.newCategory.id, this.newCategory).then(()=>{});
      this.dialog = false;
    },

    initialize(){
        this.$http.get('/tipo_items').then((response) => {this.categories = response.data});
    }

  },

  created() {this.initialize()},

};
</script>