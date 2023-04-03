<template>

    <v-dialog v-model="dialog" persistent width="1024">

      <template v-slot:activator="{ props }">
        <v-btn v-bind="props">Novo Forma de Pagamento</v-btn>
      </template>

      <v-card>
        <v-card-title> <span class="text-h5">Adicionar Novo Tipo D Gasto</span> </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>

              <v-col cols="12">
                <v-text-field label="Descrição*" required v-model="newRegister.descricao"></v-text-field>
              </v-col>

            </v-row>
          </v-container>
          <small>Os campo com (*) são obrigatórios</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue-darken-1" variant="text" @click="dialog = false">Fechar</v-btn>
          <v-btn color="blue-darken-1" variant="text" @click="createSubmitForm()">Salvar</v-btn>
        </v-card-actions>
      </v-card>

    </v-dialog>
</template>

<script>

import axios from 'axios';

export default {
name:"AddFormaPagamento",
data(){
      return {
         newRegister:{
              descricao: "",
          },
          dialog: false,
      }
  },
  methods:{
      createSubmitForm() {
          let apiURL= 'http://localhost:5001/Formas_de_pagamento';
          axios.post(apiURL,this.newRegister).then(()=>{
          }).catch(e =>{alert('ERROR!!!')})
          this.dialog = false
      }
  },
}
</script>