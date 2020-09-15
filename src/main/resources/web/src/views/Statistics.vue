<template>
    <div class="statistics">
      <div class="tabs">
        <router-link tag="div" to="/rank">排行榜</router-link>
        <router-link tag="div" to="/learnSituation">学习情况</router-link>
      </div>
      <router-view></router-view>
<!--      <van-tabs>-->
<!--        <van-tab title="排行榜">-->
<!--          <ul>-->
<!--            <li v-for="(item, index) in rankList" :key="index">-->
<!--              <div class="jiangpai">-->
<!--                <img v-if="index===0" src="../assets/jiangpai.svg">-->
<!--                <img v-else-if="index===1" src="../assets/jiangpai_1.svg">-->
<!--                <img v-else-if="index===2" src="../assets/jiangpai_2.svg">-->
<!--                <div v-else>{{index + 1}}</div>-->
<!--              </div>-->
<!--              <img :src='item.headImageUrl'>-->
<!--              <div>{{item.nickName}}</div>-->
<!--              <span>{{item.score}}</span>-->
<!--            </li>-->
<!--          </ul>-->
<!--        </van-tab>-->
<!--        <van-tab title="学习情况">-->
<!--          <div class="main" style="width: 100%;height:calc(100vh - 60px); overflow: scroll"></div>-->
<!--        </van-tab>-->
<!--      </van-tabs>-->
    </div>
</template>

<script>
export default {
  name: 'Statistics',
  data () {
    return {
      rankList: [],
      dataList: [],
      x: [],
      know: [],
      fuzzy: [],
      unKnow: [],
      dates: []
    }
  },
  created () {
    // this.getRankList()
  },
  mounted () {
    // this.drawChart()
  },
  methods: {
    async getRankList () {
      const { data: res } = await this.$http.get('/queryRank')
      this.rankList = res
    },
    drawChart () {
      // 基于准备好的dom，初始化echarts实例
      alert(document.getElementById('main'))
      const myChart = this.$echarts.init(document.getElementById('main'))
      const { data: res } = this.$http.get('/studyInfo/queryStudyInfo')
      if (res.status !== 200) {
        console.log('获取柱状图数据失败')
      }
      this.dates = res.map(item => {
        var datesArr = item.createDate.split('-')
        return datesArr[1] + '-' + datesArr[2]
      })
      this.unKnow = this.dataList.map(item => item.unknowWord)
      this.know = this.dataList.map(item => item.knowWord)
      this.fuzzy = this.dataList.map(item => item.fuzzyWord)
      console.log(typeof this.dates[0])
      // 指定图表的配置项和数据
      var option = {
        tooltip: {
          // 坐标轴指示器，坐标轴触发有效
          trigger: 'axis',
          axisPointer: {
            // 默认为直线，可选为：'line' | 'shadow'
            type: 'shadow'
          }
        },
        legend: {
          data: ['认识', '模糊', '不认识']
        },
        xAxis: { // res.map(item => item.createDate) // x轴
          data: this.dates
        },
        yAxis: {},
        series: [
          {
            name: '认识',
            type: 'bar',
            stack: 'oneday',
            data: res.map(item => item.knowWord)
          },
          {
            name: '模糊',
            type: 'bar',
            stack: 'oneday',
            data: res.map(item => item.fuzzyWord)
          },
          {
            name: '不认识',
            type: 'bar',
            stack: 'oneday',
            data: res.map(item => item.unknowWord)
          }
        ]
      }
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option)
    }
  }
}
</script>

<style scoped lang="less">
  .statistics{
    .router-link-active{
      color: #f47051;
      border-bottom: 2px solid #f47051;
    }
    .tabs{
      display: flex;
      /*background-color: #66afb7;*/
      background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
      div{
        flex: 1;
        display: flex;
        justify-content: center;
        /*border: 1px solid #576b5a;*/
        padding: 10px;
      }
    }
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
