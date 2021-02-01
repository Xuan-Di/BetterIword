<template>
  <div>
    <div class="header">发现</div>
    <div class="container">
      <div class="shake">四六级倒计时</div>
      <div  class="countDown">
        <div>{{d}}<div class="badge">天</div></div>
        <div>{{h}}<div class="badge">时</div></div>
        <div>{{m}}<div class="badge">分</div></div>
        <div>{{s}}<div class="badge">秒</div></div>
      </div>
    </div>
    <div class="boxs">
      <router-link to="/fuxi" class="fuxi box">
        <img src="http://wzxlq.xyz/iword/restudy.svg">
        <div>复习</div>
      </router-link>
      <router-link to="/translate" class="fanyi box">
        <img src="http://wzxlq.xyz/iword/translate.svg">
        <div>翻译</div>
      </router-link>
      <router-link to="/search" class="query box">
        <img src="http://wzxlq.xyz/iword/search.svg">
        <div>查询</div>
      </router-link>
      <router-link to="/newWords" class="newwords box">
        <img src="http://wzxlq.xyz/iword/book.svg">
        <div>生词本</div>
      </router-link>
      <router-link to="/cet4" class="box">
        <img src="http://wzxlq.xyz/iword/listen_4.svg">
        <div>四级听力</div>
      </router-link>
      <router-link to="/cet6" class="box">
        <img src="http://wzxlq.xyz/iword/listen_6.svg">
        <span>六级听力</span>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      d: 0,
      h: 0,
      m: 0,
      s: 0
    }
  },
  created () {
    setInterval(this.getRTime, 0)
  },
  methods: {
    getRTime () {
      var NowTime = new Date()
      var curYear = NowTime.getFullYear()
      var EndTime
      if (NowTime > new Date(curYear + '/12/18 00:00:00')) {
        EndTime = new Date((curYear + 1) + '/6/18 00:00:00')
      } else if (NowTime > new Date(curYear + '/6/18 00:00:00') && NowTime < new Date(curYear + '/12/18 00:00:00')) {
        EndTime = new Date(curYear + '/12/18 00:00:00')
      } else if (NowTime < new Date(curYear + '/6/18 00:00:00')) {
        EndTime = new Date(curYear + '/6/18 00:00:00')
      }
      var t = EndTime.getTime() - NowTime.getTime()
      if (t >= 0) {
        this.d = Math.floor(t / 1000 / 60 / 60 / 24)
        this.h = toDouble(Math.floor(t / 1000 / 60 / 60 % 24))
        this.m = toDouble(Math.floor(t / 1000 / 60 % 60))
        this.s = toDouble(Math.floor(t / 1000 % 60))
      }
      function toDouble (n) {
        return n < 10 ? '0' + n : n
      }
    }
  }

}
</script>

<style lang="less" scoped>
  .header{
    background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
    font-size: 1.2em;
    color: #41423c;
    line-height: 2.5em;
    text-align: center;
  }
  .container{
    margin: 1em;
    padding:1em 1em;
    border-radius: 10px;
    background-color: #e0e2e4;
    .shake{
      text-align: center;
      font-family: Consolas;
      font-size: 2em;
      color: #41423c;
      animation: shake-it .5s reverse infinite cubic-bezier(0.68, -0.55, 0.27, 1.55);
    }
    @keyframes shake-it{
      0%{
        text-shadow: 0 0 rgba(0, 255, 255, .5), 0 0 rgba(255, 0, 0, .5);
      }
      25%{
        text-shadow: -2px 0 rgba(0, 255, 255, .5), 2px 0 rgba(255, 0, 0, .5);
      }
      50%{
        text-shadow: -5px 0 rgba(0, 255, 255, .5), 3px 0 rgba(255, 0, 0, .5);
      }
      100%{
        text-shadow: 3px 0 rgba(0, 255, 255, .5), 5px 0 rgba(255, 0, 0, .5);
      }
    }
    .countDown{
      display: flex;
      justify-content: center;
      >div{
        margin: 2px;
        height: 2em;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 3em;
        font-size: 2em;
        background-color: #41423c;
        border-radius: 10px;
        color: white;
        position: relative;
        .badge{
          font-weight: lighter;
          font-size: 0.4em;
          position: absolute;
          right: 0;
          bottom: 0;
        }
      }

    }
  }
  .boxs{
    width: 100%;
    box-sizing: border-box;
    display: flex;
    flex-wrap: wrap;
    margin-bottom:50px;
    justify-content: center;
    .box{
      margin-top: 10px;
      width: 30vw;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      img{
        width: 50%;
      }
    }
  }
</style>
