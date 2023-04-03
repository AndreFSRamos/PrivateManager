<template>

      <v-dialog v-model="dialog" persistent width="1024">

        <template v-slot:activator="{ props }"> 
          <v-btn color="blue" v-bind="props">Novo Gasto</v-btn>
        </template>

        <v-card>
          <v-card-title> <span class="text-h5">Adicionar novo gasto</span> </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>

                <v-col cols="12">
                  <v-text-field label="Descrição*" required v-model="newRegister.descricao"></v-text-field>
                </v-col>

                <v-col cols="12" sm="6">
                  <v-text-field label="Data da Compra*" type="date" required v-model="newRegister.dataComprar"></v-text-field>
                </v-col>

                <v-col cols="12"  sm="6">
                  <v-text-field  label="Valor Gasto*" type="text" required v-model="newRegister.valor"></v-text-field>
                </v-col>

                <v-col cols="12" sm="6">
                  <v-select :items="formaPagementos" label="Forma de Pagameto*" required v-model="newRegister.formaPagamennto"></v-select>
                </v-col>

                <v-col cols="12" sm="6">
                  <v-select :items="tipo" label="tipo*" required v-model="newRegister.tipoItem"></v-select>
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

name:"AddItem",
  data(){
        return {
           newRegister:{
                descricao: "",
                nomeUsuario:"",
                dataComprar: "",
                tipoItem: "",
                formaPagamennto: "",
                valor: 0.0,
            },
            formaPagementos:[],
            tipo:[],
            dialog: false,
        }
    },
    methods:{
        createSubmitForm() {
            let apiURL= 'http://localhost:5001/items';
            axios.post(apiURL,this.newRegister).then(()=>{
            }).catch(e =>{alert('ERROR!!!')})
            this.dialog = false
            this.created()
        },
    },
    created(){

        let apiURLFormaPagamento= 'http://localhost:5001/Formas_de_pagamento'
        axios.get(apiURLFormaPagamento)
        .then((response)=> {
            let teste= response.data
            teste.forEach(element => {
                this.formaPagementos.push(element['descricao'])
            });
        }).catch((error)=> {  
            alert(error);
        }).finally(()=> {
            console.log('Erro final!');
        });

        let apiURLTipo= 'http://localhost:5001/tipo_items'
        axios.get(apiURLTipo)
        .then((response)=> {
            let teste= response.data
            teste.forEach(element => {
                this.tipo.push(element['descricao'])
            });
        }).catch((error)=> {
            alert(error);
        }).finally(()=> {
            console.log('Erro final!');
        });
      },
      watch:{
        init(){}
      }
}
</script>