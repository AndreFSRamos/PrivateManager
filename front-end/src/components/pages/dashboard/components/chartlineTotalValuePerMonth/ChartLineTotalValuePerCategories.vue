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
                labels: [
                    '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15',
                    '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'
                ],
                datasets: [
                    {
                    label: 'TOTAL DE GASTOS POR MÊS',
                    backgroundColor: '#05B7F7',
                    data: [40, 39, 10, 40, 39, 80, 40, 10, 40, 39, 80, 40],
                    },
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