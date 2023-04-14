<template>
    <v-card class="card" outlined style="height: 500px;">
        <LineChartGenerator :data="chartDataCategories" :options="chartOptions"/>
    </v-card>
</template>

<script>
    import {Chart as ChartJS, CategoryScale,LinearScale, PointElement, LineElement,Title,Tooltip, Legend,} from "chart.js";
    import { Line as LineChartGenerator } from "vue-chartjs";
    ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);
    export default {
        name: "ChartLine",
        components: {LineChartGenerator},
        data() {
            return {
            categories: [],
            chartDataCategories: {
                labels: ['JAN', 'FEV', 'MAR', 'ABR', 'MAI', 'JUH', 'JUL', 'AGO', 'SET', 'OUT', 'NOV', 'DEZ'],
                datasets: [
                    {
                    label: 'TOTAL DE GASTOS POR MÊS',
                    backgroundColor: '#05B7F7',
                    data: [40, 39, 10, 40, 39, 80, 40, 10, 40, 39, 80, 40],
                    }
                ],
            },
            chartOptions: {
                responsive: true,
                maintainAspectRatio: false,
            },
            loaded: false,
            };
        },
    };
</script>