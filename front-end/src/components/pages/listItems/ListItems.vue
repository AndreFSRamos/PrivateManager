<template>
  <v-data-table :headers="header" :items="items" :items-per-page="20" class="elevation-1">
   
    <template v-slot:top>
      <v-alert show dismissible prominent :type="menssager.type" v-for="menssager in menssagers" :key="menssager.texto">{{ menssager.texto }}</v-alert>
      <v-toolbar flat>
        
        <v-toolbar-title> Lista de Gastos </v-toolbar-title>
        <v-spacer></v-spacer>

        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn class="mb-2" v-bind="attrs" v-on="on"> Novo Gasto </v-btn>
          </template>
          
          <v-card>
            <v-card-title>
              <span class="text-h5">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field v-model="newRegister.description" label="DESCRIÇÃO *" required></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field v-model="newRegister.dateBuy" value="04/06/2023" type="date" label="DATA DA COMPRA *" required></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-select v-model="newRegister.category" :items="categories" label="TIPO *" required></v-select>
                  </v-col>
                  <v-col cols="12">
                    <v-select v-model="newRegister.paymentMethod" :items="paymentMethods" label="FORMA DE PAGAMENTO *" required></v-select>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field v-model="newRegister.price" value="10.00" prefix="$" label="VALOR *" type="number" required></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="close"> Cancelar </v-btn>
              <v-btn color="blue darken-1" text @click="save">Confirmar</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="text-h5">Deseja excluir o item?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="red" text @click="closeDelete">Cancelar</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">Confirmar</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
      <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn @click="initialize"> Atualizar </v-btn>
    </template>
    
  </v-data-table>
</template>

<script>
export default {
  name: "ListView",
  components: {},
  data: () => ({
    header: [
      { text: "CÓDIGO", value: "id" },
      { text: "DESCRIÇÃO", value: "description" },
      { text: "DATA DA COMPRA", value: "dateBuy" },
      { text: "TIPO", value: "category" },
      { text: "FORMA DE PAGEMENTO", value: "paymentMethod" },
      { text: "VALOR", value: "price" },
      { text: "", value: "actions", sortable: false },
    ],
    newRegister: {
      description: "",
      dateBuy:"",
      category: "",
      paymentMethod: "",
      price: 0.0,
    },
    menssagers:[],
    items: [],
    paymentMethods: [],
    categories: [],
    dialog: false,
    dialogDelete: false,
    editedIndex: -1,
  }),

  created() {
    this.initialize();
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Novo Gasto" : "Editar Gasto";
    },
  },

 
  methods: {
    
    editItem(item) {
      this.editedIndex = item.id
      this.newRegister = item
      this.dialog = true;
    },

    deleteItem(item) {
      this.dialogDelete =true
      this.editedIndex = item.id
    },

    close() {
      this.dialog = false;
      this.editedIndex = -1;
    },

    closeDelete() {
      this.dialogDelete = false
      this.editedIndex = -1;
    },

  initialize() {
    this.$http.get('/items')
        .then((response) => {this.items = response.data;});

    this.$http.get('/tipo_items')
        .then((response) => {
        let teste= response.data
        teste.forEach(element => {
        this.categories.push(element['description'])
    });
        }).catch(() => {});

    this.$http.get('/Formas_de_pagamento')
        .then((response) => {
        let teste= response.data
        teste.forEach(element => {
        this.paymentMethods.push(element['description'])
            });
        }).catch(() => {});
    },

    deleteItemConfirm() {
      this.$http.delete("/items/"+this.editedIndex).then(()=>{
        this.menssagers.push({texto:"O item foi excluido com sucesso!.", type:"success"});
      }).catch(()=>{
        this.menssagers.push({texto:"Ocorreu um erro e não foi possivel excluir o item!.", type:"error"});
      }),
      this.dialogDelete = false
    },

    save() {
        this.newRegister.id = this.editedIndex
        if(this.editedIndex > -1){ 
          this.$http.put('items/' + this.newRegister.id, this.newRegister).then(()=>{ 
            this.menssagers.push({texto:"Gasto ( " + this.newRegister.id + " ) editado com sucesso!.", type:"success"});
          }).catch(() => {
            this.menssagers.push({texto:"Não foi possivel editar o gasto ( "  + this.newRegister.id + " )!.", type:"error"});
          });
        }else{ 
          this.$http.post('/items',this.newRegister).then(()=>{ 
            this.menssagers.push({texto:"Novo gasto adiconado com sucesso", type:"success"});
          }).catch(() => {
            this.menssagers.push({texto:"Erro ao tentar adiconar o novo gasto!", type:"error"});
          });
        }
        this.dialog = false;
    }
  },
};
</script>
