<template>
    <div class="content-right">
        <slot name="slot-pages">
            <router-view>
            </router-view>
        </slot>
    </div>
</template>

<script lang="ts">

export default{ 
    name: "ContentPage",
    data(){
        return  {
            changeWidthSidebar :false,
            items:[]
        }
    },
}
</script>

<style scoped>
.content-right{
    padding:20px;
}</style>