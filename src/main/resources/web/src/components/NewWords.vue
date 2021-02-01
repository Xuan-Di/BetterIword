<template>
    <div class="newwords">
      <!--头部-->
      <van-nav-bar title="生词本" left-arrow :border="true" @click-left="back()">
        <template #right>
          <van-dropdown-menu>
            <van-dropdown-item v-model="value1" :options="option1" @change="changeSort()"/>
          </van-dropdown-menu>
        </template>
      </van-nav-bar>

      <div class="list" v-if="!showdetail">
        <div class="tishi"><img id="animate" src="../assets/shouzhi.svg" style="display: none"></div>
        <van-swipe-cell v-for="(item,i) in newWordsList" :key="i">
          <van-cell :border="false" :title="item.englishWord" :value="item.chineseWord" />
          <template #right>
            <van-button square type="primary" text="详情" @click="details(item)"/>
            <van-button square type="danger" text="移除" @click="removeit(item)"/>
          </template>
        </van-swipe-cell>
      </div>
      <div v-else>
        <div class="word">
          <div class="left" @click="resite()">
            <div class="english">{{datails.englishWord}}</div>
            <div class="pronounce">英[{{datails.pa}}]<van-icon name="volume"></van-icon></div>
            <audio ref="audio" autoplay></audio>
          </div>
        </div>
        <!--释义-->
        <div class="explanation">
          <span>{{datails.chineseWord}}</span>
        </div>
        <!--例子-->
        <div class="examples">
          <!--例句-->
          <div class="sentences">
            <div class="title">
              <span>例句</span>
              <van-icon name="wap-nav"></van-icon>
            </div>
            <div class="content">
              <p>{{datails.engInstance1}}</p>
              <p>{{datails.chiInstance1}}</p>
              <p>{{datails.engInstance2}}</p>
              <p>{{datails.chiInstance2}}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'NewWords',
  data () {
    return {
      newWordsList: [],
      datails: {},
      showdetail: false,
      value1: 0,
      option1: [
        { text: '字母', value: 0 },
        { text: '词频', value: 1 }
      ]
    }
  },
  created () {
    this.getNewWords()
  },
  mounted () {
    var monkey = document.querySelector('#animate')
    monkey.style.display = 'block'
    monkey.classList.add('move')
    monkey.addEventListener('animationend', function () {
      monkey.style.display = 'none'
    })
  },
  methods: {
    async getNewWords () {
      const { data: res } = await this.$http.get('/userWord/queryAndSort')
      this.newWordsList = res
    },
    details (item) {
      this.datails = item
      this.showdetail = true
    },
    // 给audio添加src
    resite () {
      this.$refs.audio.src = this.datails.pron
    },
    back () {
      this.showdetail = false
      this.$router.go(-1)
    },
    async removeit (item) {
      const res = await this.$http.get('/userWord/deleteWord', {
        params: {
          englishWord: item.englishWord
        }
      })
      console.log(res)
      this.getNewWords()
    },
    changeSort () {
      if (this.value1 === 0) {
        this.newWordsList.sort((a, b) => {
          if (a.englishWord > b.englishWord) {
            return 1
          } else if (a.englishWord < b.englishWord) {
            return -1
          } else {
            return 0
          }
        })
      } else if (this.value1 === 1) {
        this.newWordsList.sort((a, b) => b.collect - a.collect)
      }
    }
  }
}
</script>
<style lang="less" scoped>
  .newwords{

    .van-nav-bar{
      /*background-color: #66afb7;*/
      background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
      .van-icon{
        color: #41423c;
      }
    }
    .van-dropdown-menu{
      height: 40px;
      /*background-color: #66afb7;*/
      background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
    }
    .word {
      padding: 10px;
      font-family: Consolas;
      color: #576b5a;
      border-bottom: 1px solid #eee;
      .left {
        .english {
          font-size: 1.5em;
        }

        .pronounce {
          font-size: 12px;
          display: flex;
          justify-content: flex-start;
          align-items: center;
        }
      }
    }
    .explanation{
      padding:5px 10px;
      color: #576b5a;
    }
    .examples{
      .title{
        background-color: #eeeeee;
        padding: 2px 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 12px;
        color: #41423c;
      }
      .content{
        font-family: Consolas;
        padding: 10px;
        color: #556755;
      }
    }
    .list{
      position: relative;
      .tishi{
        img{
          width: 30px;
          position: absolute;
          z-index: 99;
          right: 5px;
        }
      }
      .van-swipe-cell{
        margin: 0 10px;
        border-bottom: 1px solid #66afb7;
        line-height: 24px;
      }
      .van-cell__title{
        flex: none;
      }
      .van-cell__value>span{
        text-align: left;
        margin-left: 10px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp:1;
        overflow: hidden;
      }
    }
    .move{
      animation:mymove 0.5s 2 ;
      -webkit-animation:mymove 0.5s 2; /*Safari and Chrome*/
      @keyframes mymove
      {
        from {right:0px;}
        to {right:30px;}
      }
    }

  }

</style>
