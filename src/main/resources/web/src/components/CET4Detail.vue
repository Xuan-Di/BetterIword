<template>
    <div class="container">
      <div class="head">
        <van-icon name="arrow-left" @click="back()"></van-icon>
        <div>历年英语四级真题</div>
      </div>
      <div class="content">
        <p class="title">{{detail.title}}</p>
        <p v-for="(item,i) in detail.content" :key="i">{{item}}</p>
      </div>
      <div class="bottombox">
        <audio :src="this.detail.voice" controls autoplay></audio>
      </div>
    </div>
</template>
<script>
export default {
  name: 'CET4Detail',
  data () {
    return {
      detail: {}
    }
  },
  created () {
    this.getDetail()
  },
  methods: {
    async getDetail () {
      const { data: res } = await this.$http.get('/cet/tingLiFourOne?title=' + this.$route.params.prop)
      this.detail = res
    },
    back () {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped lang="less">
.container{
  position: relative;
  .head{
    position: fixed;
    width: 100%;
    height: 50px;
    display: flex;
    align-items: center;
    background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
    .van-icon{
      width: 10%;
      line-height: 54px;
      font-size: 25px;
      text-align: center;
    }
  }
  .content{
    padding:50px 20px;
    font-family: Consolas;
    color: #576b5a;
    overflow: scroll;
    margin-bottom: 50px;
  }
  .title{
    font-weight: bold;
  }
  .bottombox{
    width: 100%;
    height: 60px;
    background-color: #eeeeee;
    position: fixed;
    bottom: 50px;
    audio {
      width: 100%;
    }
  }
}
</style>
