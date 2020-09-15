<template>
    <div class="rank">
      <ul>
        <li v-for="(item, index) in rankList" :key="index">
          <div class="jiangpai">
            <img v-if="index===0" src="../assets/jiangpai.svg">
            <img v-else-if="index===1" src="../assets/jiangpai_1.svg">
            <img v-else-if="index===2" src="../assets/jiangpai_2.svg">
            <div v-else>{{index + 1}}</div>
          </div>
          <img :src='item.headImageUrl'>
          <div>{{item.nickName}}</div>
          <span>{{item.score}}</span>
        </li>
      </ul>
    </div>
</template>

<script>
export default {
  name: 'Rank',
  data () {
    return {
      rankList: []
    }
  },
  created () {
    this.getRankList()
  },
  methods: {
    async getRankList () {
      const { data: res } = await this.$http.get('/queryRank')
      this.rankList = res
    }
  }
}
</script>

<style lang="less" scoped>
  .rank{
    ul{
      overflow: scroll;
      margin-bottom: 50px;
      li{
        position: relative;
        height: 3em;
        padding: 10px;
        border-bottom: 1px solid #eeeeee;
        display: flex;
        align-items: center;
        justify-content: flex-start;
        div{
          /*width: 60px;*/
          height: 100%;
          line-height: 3em;
          /*border: 1px solid #66afb7;*/
          display: flex;
          justify-content: center;
          padding: 10px;

          display: -webkit-box;
          -webkit-box-orient: vertical;
          -webkit-line-clamp:1;
          overflow: hidden;
        }
        .jiangpai{
          display: flex;
          align-items: center;
          img{
            width: 30px;
          }
        }
        span{
          position: absolute;
          right: 20px;
          color: #66afb7;
          font-weight: bold;

        }
        img{
          height: 100%;
          border-radius: 50%;
        }
      }
    }
  }
</style>
