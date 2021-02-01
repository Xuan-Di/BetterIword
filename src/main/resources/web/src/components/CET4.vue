<template>
  <div>
    <div class="head">
      <van-icon name="arrow-left" @click="back()"></van-icon>
      <img src="../assets/cet42.svg">
      <div>
        <p>历年四级英语听力真题</p>
        <p>{{list.length}}个课程</p>
      </div>
    </div>
    <ul>
      <router-link tag="li" :to="{name:'cet4detail', params:{ prop : item }}" v-for="(item,i) in list" :key="i">{{item}}</router-link>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'CET4',
  data () {
    return {
      list: []
    }
  },
  created () {
    this.getList()
  },
  methods: {
    async getList () {
      const { data: res } = await this.$http.get('/cet/tingLiFourList')
      this.list = res
    },
    back () {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped lang="less">
  .head{
    background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
    display: flex;
    height: 5em;
    .van-icon{
      width: 10%;
      display: flex;
      align-items: center;
      font-size: 25px;
      text-align: center;
    }
    img{
      width: 5em;
    }
    p{
      color: #576b5a;
      font-weight: bold;
    }
  }

  ul{
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    background-color: #ffffff;
    padding: 7px;
    z-index: 9999;
    li{
      line-height: 30px;
      padding: 6px;
      border-bottom: 1px solid #e0e2e4;
      background-color: white;
      // 超出部分隐藏
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp:1;
      overflow: hidden;
    }
  }

</style>
