<template>
    <div>
      <van-nav-bar title="词汇量测试" left-arrow :border="true" @click-left="back()">
      </van-nav-bar>
      <div v-if="!showReport">
        <van-progress :percentage="percent" :show-pivot="false" stroke-width="8" color="linear-gradient(to right, #72F3C3, #5bee68, #b9ee58, #ee9a48, #ee655c)" />
        <div class="word">
          {{currentWord.englishWord}}
        </div>
        <div class="countdown">
          <van-circle
            v-model="currentRate"
            :color="gradientColor"
            ref="circleRef"
            rate="0"
            speed="2"
            size="80px"
            :stroke-width="100"
          />
        </div>
        <div class="buttoms">
          <van-button type="primary" @click="toogle ()">认识</van-button>
          <van-button type="primary" @click="toggleNext ()">不认识</van-button>
        </div>
      </div>
      <div v-else>
        <div class="tips">你的词汇量大约是</div>
        <div class="num">{{count}}</div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Test',
  data () {
    return {
      list: [],
      currentWord: '',
      index: 0,
      currentRate: 100,
      gradientColor: {
        '0%': '#3fecff',
        '100%': '#6149f6'
      },
      count: 0,
      percent: 100 / 15,
      showReport: false
    }
  },
  created () {
    this.getList()
  },
  methods: {
    async getList () {
      const { data: res } = await this.$http.get('/word/wordCountTest')
      this.list = res
      this.currentWord = res[0]
      // document.querySelector('.countdown').innerHTML = '<van-circle v-model="50" :color="gradientColor" ref="circleRef" rate="0" speed="20" size="50px" :stroke-width="100" />'
    },
    back () {
      this.$router.go(-1)
    },
    toogle () {
      if (this.index >= 0 && this.index < 5) {
        this.count += 300
      } else if (this.index >= 5 && this.index < 12) {
        this.count += 286
      } else if (this.index >= 12 && this.index < 15) {
        this.count += 1000
      }
      this.percent += 100 / 15
      this.currentWord = this.list[++this.index]
      if (this.index === 14) {
        this.showReport = true
      }
    },
    toggleNext () {
      this.percent += 100 / 15
      this.currentWord = this.list[++this.index]
      if (this.index === 14) {
        this.showReport = true
      }
    }
  }
}
</script>

<style scoped lang="less">
  .van-progress{
    margin-top: 1em;
    color: #ee655c;
  }
  .word{
    height: 25vh;
    margin: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2em;
  }
  .countdown{
    height: 100px;
    width: 100%;
    display: flex;
    justify-content: center;
    margin-bottom: 1em;
  }
  .buttoms{
    display: flex;
    justify-content: space-around;
    .van-button{
      width: 100px;
    }
  }
  .van-circle{
    display: block;
  }
  .tips{
    display: flex;
    justify-content: center;
    font-size: 1em;
    padding: 1em;
  }
  .num{
    display: flex;
    justify-content: center;
    font-size: 2em;
    padding: 1em;
  }
</style>
