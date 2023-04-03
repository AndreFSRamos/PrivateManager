<template>
    <div>

        <v-row>
            <v-col cols="12">
                <v-card style="display: flex; justify-content: space-between;align-items: center; padding: 10px;">
                    <h1>Lista de Gastos</h1>
                    <v-spacer></v-spacer>
                    <AddItems />
                </v-card>
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="12">
                <v-card>
                    <v-data-table :itemprop="itemsPerPage" :headers="headers" :items="desserts" item-value="name"
                        class="elevation-1"></v-data-table>

                    <v-table theme="dark" hover="true">
                        <thead>
                            <tr>
                                <th class="text-left">CÓDIGO</th>
                                <th class="text-left">DESCRIÇÃO</th>
                                <th class="text-left">DATA DA COMPRA</th>
                                <th class="text-left">TIPO</th>
                                <th class="text-left">FORMA DE PAGAMENTO</th>
                                <th class="text-left">VALOR</th>
                                <th class="text-left">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in produtos" :key="item.id">
                                <td>
                                    <p>{{ item['id'] }}</p>
                                </td>
                                <td> <input v-if="item.stats" type="text" class="" :value="item.descricao">
                                    <p v-else>{{ item['descricao'] }}</p>
                                </td>
                                <td> <input v-if="item.stats" type="text" class="" :value="item.dataComprar">
                                    <p v-else>{{ item['dataComprar'] }}</p>
                                </td>
                                <td>
                                    <select v-if="item.stats" name="tipo" id="tipo" class="">
                                        <option v-for="item in tipo" :key="item.id" :value="item.descricao">{{
                                            item['descricao'] }}</option>
                                    </select>
                                    <p v-else>{{ item['tipoItem'] }}</p>
                                </td>
                                <td>
                                    <select v-if="item.stats" name="formaPagameno" id="tiformaPagamenopo" class="">
                                        <option v-for="item in formaPagementos" :key="item.id" :value="item.descricao">{{
                                            item['descricao'] }}</option>
                                    </select>
                                    <p v-else>{{ item['formaPagamennto'] }}</p>
                                </td>
                                <td> <input v-if="item.stats" type="text" class="" :value="item.valor">
                                    <p v-else> R$ {{ item['valor'] }}</p>
                                </td>
                                <td>
                                    <v-btn @click="editar(item)"><img src="../../../assets/images/escrever.png"
                                            alt="editar"></v-btn>
                                    <v-btn class="my-btn my-btn-danger" @click="excluir(item)"><img
                                            src="../../../assets/images/excluir.png" alt="excluir"></v-btn>
                                </td>
                            </tr>
                        </tbody>
                    </v-table>
                </v-card>
            </v-col>
        </v-row>

    </div>
</template>

<script>

import axios from 'axios';
import AddItems from './Components/AddItem.vue'

export default {
    data() {
        return {
            newRegister: {
                descricao: "",
                nomeUsuario: "",
                dataComprar: "",
                tipoItem: "",
                formaPagamennto: "",
                valor: 0.0,
            },
            produtos: [],
            formaPagementos: [],
            tipo: [],
            popupadd: true,
        }
    },
    components: {
        AddItems,
    },
    methods: {
        excluir(protudo) {
            let apiURL = 'http://localhost:5001/items/' + protudo.id

            axios.delete(apiURL, protudo)
                .then(() => {
                    this.produtos.splice(protudo, 1)
                }).catch((error) => {
                    alert(error);
                }).finally(() => {
                    console.log('Erro final!');
                });
        },
        editar(protudo) {
            protudo.stats = !protudo.stats
        },
        salvar(newRegister) {
            let apiURL = 'http://localhost:5001/' + newRegister.id

            axios.delete(apiURL, protudo)
                .then(() => {
                    this.produtos.splice(protudo, 1)
                }).catch((error) => {
                    alert(error);
                }).finally(() => {
                    console.log('Erro final!');
                });
        },
    },
    created() {
        this.isEdit = false

        let apiURL = 'http://localhost:5001/items'

        axios.get(apiURL)
            .then((response) => {
                this.produtos = response.data
            }).catch((error) => {
                alert(error);
            }).finally(() => {
                console.log('Erro final!');
            });

        let apiURLFormaPagamento = 'http://localhost:5001/Formas_de_pagamento'
        axios.get(apiURLFormaPagamento)
            .then((response) => {
                this.formaPagementos = response.data
            }).catch((error) => {
                alert(error);
            }).finally(() => {
                console.log('Erro final!');
            });

        let apiURLTipo = 'http://localhost:5001/tipo_items'
        axios.get(apiURLTipo)
            .then((response) => {
                this.tipo = response.data
            }).catch((error) => {
                alert(error);
            }).finally(() => {
                console.log('Erro final!');
            });
    }
}
</script>

<style lang="scss" scoped>#header-list {
    display: flex;
    justify-content: space-between;

    padding-bottom: 15px;

    .nav-bottuns {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
}</style>