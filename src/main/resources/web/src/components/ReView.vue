<template>
    <div>
      <van-nav-bar title="单词填充" left-arrow @click-left="back()">
      </van-nav-bar>
      <div style="margin: 8vh auto;padding: 2em">
        <div style=" margin: 3em auto; font-family: Consolas">
          <div style="display: flex;justify-content: center; font-size: 2em;">{{res}}</div>
          <div style="display: flex;justify-content: center; margin: 1em auto;color: #576b5a">{{currentWord.pa}}</div>
          <div style="display: flex;justify-content: center;color: #576b5a">{{currentWord.chineseWord}}</div>
        </div>
        <div style="display: flex;justify-content: space-around; font-family: Consolas">
          <van-button style="width: 50px; font-weight: bold" type="primary" @click="judge(choice[0])">{{choice[0]}}</van-button>
          <van-button style="width: 50px; font-weight: bold" type="info" @click="judge(choice[1])">{{choice[1]}}</van-button>
          <van-button style="width: 50px; font-weight: bold" type="warning" @click="judge(choice[2])">{{choice[2]}}</van-button>
        </div>
      </div>
    </div>
</template>

<script>
import Vue from 'vue'
import { Toast } from 'vant'
Toast.setDefaultOptions({ duration: 1000 })
// Vue.prototype.$toast = Toast
Vue.use(Toast)
export default {
  name: 'ReView',
  data () {
    return {
      list: [],
      currentWord: '',
      strarr: 'abcdefghijklmnopqrstuvwxyz'.split(''),
      choice: [],
      fill: '',
      index: 0,
      res: ''
    }
  },
  created () {
    // 获取复习的单词
    this.getReviewWords()
  },
  methods: {
    async getReviewWords () {
      const { data: res } = await this.$http.get('/word/review')
      if (res.length !== 0) {
        this.list = res
        this.currentWord = res[0]
        var cur = this.currentWord.englishWord
        this.show(cur)
        // var index = Math.floor(Math.random() * cur.length)
        // var fill = cur[index]
        // this.fill = fill
        // this.res = cur.replace(fill, '_')
        // var choice = []
        // choice.push(fill, this.strarr[Math.round(Math.random() * (this.strarr.length - 1))], this.strarr[Math.round(Math.random() * (this.strarr.length - 1))])
        // choice.sort()
        // this.choice = choice
      } else {
        this.$toast({
          message: '您没有单词没有复习'
        })
      }
    },
    show (cur) {
      var index = Math.floor(Math.random() * cur.length)
      var fill = cur[index]
      this.fill = fill
      this.res = cur.replace(fill, '_')
      var choice = []
      choice.push(fill, this.strarr[Math.round(Math.random() * (this.strarr.length - 1))], this.strarr[Math.round(Math.random() * (this.strarr.length - 1))])
      choice.sort()
      this.choice = choice
    },
    judge (fill) {
      if (fill === this.fill) {
        if (this.index === 20) {
          this.$toast({
            message: '今日复习已完毕'
          })
        } else {
          this.$toast({
            message: '正确'
          })
          this.currentWord = this.list[++this.index]
          var cur = this.currentWord.englishWord
          this.show(cur)
        }
      } else {
        this.$toast({
          message: '错误'
        })
      }
    },
    back () {
      this.$router.go(-1)
    }
  }
}
</script>

<style lang="less" scoped>
  .van-nav-bar{
    /*background-color: #66afb7;*/
    background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
  }
</style>
