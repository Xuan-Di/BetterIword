<template>
    <div id="review">
      <!--单词-->
      <div class="word">
        <van-icon class="collect" :name="this.currentWord.addCollect?'star':'star-o'" @click="toogle()"></van-icon>
        <div class="left" @click="resite()">
          <div class="english">{{currentWord.englishWord}}</div>
          <div class="pronounce">英[{{currentWord.pa}}]<van-icon name="volume"></van-icon></div>
          <audio ref="audio" :src="currentWord.pron" autoplay></audio>
        </div>
        <div class="right" @click="toSearch()"><van-icon name="search"></van-icon></div>
      </div>
      <!--单击显示-->
      <div class="blank" v-if="!show" @click="showAnswer()"><!--新问题，没有先出现空白再显示详情-->
        <div class="box">
          <div class="">请把英文发音和中文解释说出口</div>
          <div class="tips">点击屏幕显示答案</div>
        </div>
      </div>
      <div v-if="show">
        <!--释义-->
        <div class="explanation">
          <p>{{currentWord.chineseWord}}</p>
        </div>
        <!--例子-->
        <div class="examples">
          <!--例句-->
          <div class="sentences">
            <div class="title">
              <span>例句</span>
            </div>
            <div class="content">
              <p class="instance1">{{currentWord.engInstance1}}</p>
              <p>{{currentWord.chiInstance1}}</p>
              <br>
              <p class="instance2">{{currentWord.engInstance2}}</p>
              <p>{{currentWord.chiInstance2}}</p>
            </div>
          </div>
        </div>
        <div class="buttons">
          <van-button class="btn" type="primary" @click="addToKnow()">认识</van-button>
          <van-button class="btn" type="warning" @click="addUnClear()">模糊</van-button>
          <van-button class="btn" type="danger" @click="addToUnKnow()">忘记</van-button>
        </div>
      </div>
      <!--生词本-->
      <div class="newwords" @click="toNewWords()">
        生词本
      </div>
    </div>
</template>

<script>
export default {
  name: 'Review',
  data () {
    return {
      currentWord: this.$store.state.dailyList[0],
      show: false,
      collectList: []
    }
  },
  async created () {
    await this.judgeRest()
    await this.getNewWords()
  },
  mounted () {

  },
  methods: {
    // 点击屏幕切换显示
    showAnswer () {
      this.show = true
    },
    // 给audio添加src
    resite () {
      this.$refs.audio.src = this.currentWord.pron
    },
    // 添加到认识队列
    async addToKnow () {
      this.$store.commit('addToKnow', this.currentWord)
      // 并且，要把这个单词传到后端，后端同步，删除该单词
      await this.$http.post('/word/wordInfo', {
      // await this.$http.post('http://a296148l31.zicp.vip/word/wordInfo', {
        word: this.currentWord,
        type: 'know'
      })
      if (this.$store.state.dailyList.length === 0) {
        await this.$router.push({ path: '/calendar' })
      }
      // 并且更新当前单词
      this.currentWord = this.$store.state.dailyList[0]
      this.show = false
    },
    async addUnClear () {
      this.$store.commit('addUnClear', this.currentWord)
      // 并且更新当前单词
      this.currentWord = this.$store.state.dailyList[0]
      await this.$http.post('/word/wordInfo', {
        word: this.currentWord,
        type: 'fuzzy'
      })
      this.show = false
    },
    async addToUnKnow () {
      this.$store.commit('addToUnKnow', this.currentWord)
      // 并且更新当前单词
      this.currentWord = this.$store.state.dailyList[0]
      await this.$http.post('/word/wordInfo', {
        word: this.currentWord,
        type: 'unknow'
      })
      this.show = false
    },
    toSearch () {
      this.$router.push({ name: 'search' })
    },
    async toogle () {
      // 如果已经收藏过，再次点击会取消收藏
      if (this.currentWord.addCollect) {
        await this.$http.get('/userWord/deleteWord', {
          params: {
            englishWord: this.currentWord.englishWord
          }
        })
        // 删除过后，重新请求生词列表
        this.currentWord.addCollect = false
        this.getNewWords()
      } else {
        // 如果没有收藏过，点击收藏，会添加收藏
        await this.$http.post('/userWord/insertWord', this.currentWord)
        // 添加过后，重新请求生词列表
        this.getNewWords()
      }
      // 切换图标
      this.currentWord.addCollect = !this.currentWord.addCollect
    },
    // 查询生词表
    async getNewWords () {
      const { data: res } = await this.$http.get('/userWord/queryAndSort')
      this.collectList = res
      // 如果生词表里已经存在该单词，那么重新刷新的时候，他的addCollect属性应该是true
      if (res.find(item => item.englishWord === this.currentWord.englishWord)) {
        this.currentWord.addCollect = true
      } else {
        this.currentWord.addCollect = false
      }
    },
    // 查看生词本
    toNewWords () {
      this.$router.push({ path: '/newWords' })
    },
    async judgeRest () {
      if (this.$store.state.dailyList.length === 0) {
        await this.$router.push({ path: '/calendar' })
      }
    }
  }
}
</script>

<style scoped lang="less">
  #review{
    p{
      margin-block-start: 0;
      margin-block-end: 0;
    }
    .word{
      width: 100%;
      height: 70px;
      /*background-color: #66afb7;*/
      background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
      display: flex;
      justify-content: center;
      align-items: center;
      border-bottom:1px solid #eee ;
      .collect{
        position: absolute;
        left: 15px;
        font-size: 20px;
      }
      .left{
        .english{
          font-size: 20px;
          display: flex;
          justify-content: center;
        }
        .pronounce{
          font-size: 12px;
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }
      .right{
        position: fixed;
        right: 15px;
        width: 30px;
        height: 30px;
        background-color: #66afb7;
        border-radius: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        box-shadow: 2px 2px 2px  rgba(255, 255, 255, 0.9);
        .van-icon{
          font-size: 20px;
        }
      }
    }
    .blank{
      .box{
        position: absolute;
        width: 100%;
        top: 50%;
        transform: translateY(-50%);
        display: flex;
        justify-content: center;
        flex-direction: column;
        align-items: center;
        .tips{
          color: #66afb7;
        }
      }
    }
    .explanation{
      padding: 10px;
      line-height: 22px;
      /*background-color: #98eebc;*/
      background: linear-gradient(to top, white, 20%, #98eebc );
      border-bottom:1px solid #eee ;
    }
    .examples{
      .title{
        background-color: #eeeeee;
        padding: 5px 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 12px;
        color: #41423c;
      }
      .content{
        padding: 10px;
        color: #576b5a;
        font-family: Consolas;
      }

    }
    .buttons{
      position: absolute;
      width: 100%;
      bottom: 60px;
      display: flex;
      justify-content: space-around;
      .btn{
        padding: 0 30px;
        height: 30px;
        line-height: 30px;
      }
    }
    .newwords{
      width: 50px;
      height: 50px;
      border-radius: 50%;
      /*background-color: #66afb7;*/

      text-align: center;
      line-height: 50px;
      color: white;
      position: fixed;
      right: 0;
      bottom: 100px;
    }
  }

</style>
