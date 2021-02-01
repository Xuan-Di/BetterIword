<template>
    <div class="search">
      <div class="head">
        <van-icon name="arrow-left" @click="back()"></van-icon>
        <van-search shape="round" v-model="value" background="linear-gradient(to top, #98eebc, 25%, #66afb7 )" placeholder="请输入搜索关键词" @clear="clear()" @input="getSearchResult()" />
      </div>
      <div class="translate" @click="toTranslate()">
        翻译
      </div>
      <ul class="result" v-if="!showdetail">
        <li v-for="(item, i) in list" :key="i" @click="detail(item)">
          {{item.englishWord}}
          {{item.chineseWord}}
        </li>
      </ul>
      <!--单词-->
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
  name: 'Search',
  data () {
    return {
      value: '',
      list: [],
      datails: {},
      showdetail: false
    }
  },
  methods: {
    back () {
      this.$router.go(-1)
    },
    async getSearchResult () {
      const { data: res } = await this.$http.get('/word/queryInEs', {
        params: {
          keyword: this.value
        }
      })
      this.list = res
    },
    detail (item) {
      this.datails = item
      this.showdetail = true
    },
    // 给audio添加src
    resite () {
      this.$refs.audio.src = this.datails.pron
    },
    toTranslate () {
      this.$router.push({ name: 'translate' })
    },
    clear () {
      this.list = []
      this.datails = {}
      this.showdetail = false
    }
  }
}
</script>

<style lang="less" scoped>
  .search{
    .head{
      width: 100%;
      display: flex;
      .van-icon{
        width: 10%;
        /*background-color: #66afb7;*/
        background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
        line-height: 54px;
        font-size: 25px;
        text-align: center;
      }
      .van-search{
        width: 90%;
      }
    }
    .result{
      background-color: #eeeeee;
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
      .sentences{
        .title{
          background-color: #e0e2e4;
          padding: 5px 10px;
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
    }
    .translate{
      width: 50px;
      height: 50px;
      border-radius: 50%;
      /*background-color: #66afb7;*/
      background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
      text-align: center;
      line-height: 50px;
      color: white;
      position: fixed;
      right: 0;
      bottom: 100px;
    }
  }
</style>
