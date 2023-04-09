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
    data(){
        return  {
            changeWidthSidebar :false,
            items:[]
        }
    },
    name: "ContentPage",
}
</script>

<style lang="scss" scoped>
.content-right{
    padding:20px;
}</style>