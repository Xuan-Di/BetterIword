<template>
  <div id="container" class="test-dark">
    <router-view></router-view>
    <van-tabbar :fixed="true" v-model="active" route>
      <van-tabbar-item name="review" icon="underway-o" :badge="this.$store.state.dailyList.length" to="/review">单词</van-tabbar-item>
      <van-tabbar-item name="searchandtranslate" icon="eye-o" to="/searchandtranslate">发现</van-tabbar-item>
      <van-tabbar-item name="statistics" icon="chart-trending-o" to="/statistics">统计</van-tabbar-item>
      <van-tabbar-item name="setting" icon="setting-o" to="/setting">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>

export default {
  data () {
    return {
      // 通过名字匹配
      active: 'review'
    }
  }
}
</script>
<style scoped lang="less">
  .van-tabbar{
    border-top: 1px solid #66afb7;
  }

</style>
