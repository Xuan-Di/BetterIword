<template>
  <div class="studyInfo">
    <div id="main" style="width: 100%;height:calc(100vh - 60px); overflow: scroll"></div>
  </div>

</template>
<script>
export default {
  name: 'LearnSituation',
  data () {
    return {
      dataList: [],
      x: [],
      know: [],
      fuzzy: [],
      unKnow: [],
      dates: []
    }
  },
  created () {
  },
  methods: {
    async drawChart () {
      // 基于准备好的dom，初始化echarts实例
      const myChart = this.$echarts.init(document.getElementById('main'))
      const { data: res } = await this.$http.get('/studyInfo/queryStudyInfo')
      if (res.status !== 200) {
        console.log('获取柱状图数据失败')
      }
      // var month = item.createDate.getMonth() + 1
      // var day = item.createDate.getDate()
      // return month + '' + day
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
  },
  mounted () {
    this.drawChart()
  }
}
</script>

<style lang="less" scoped>

</style>
