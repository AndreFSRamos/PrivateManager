<template>
    <div class="content-right">
        <slot name="slot-pages">
            <router-view>
            </router-view>
        </slot>
    </div>
</template>

<script>

import SideBar from "./components/Sidebar.vue";

export default{ 
    data(){
        return  {
            changeWidthSidebar :false,
            items:[]
        }
    },
    name: "dashboard",
    components:{
        SideBar,
    },
  
}

</script>

<style lang="scss" scoped>
.content-right{
    padding: 25px;
}</style>