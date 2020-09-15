<template>
    <div>
      <van-calendar
        ref="cal"
        title="每日打卡"
        :poppable="false"
        :show-confirm="true"
        :style="{ height: '500px' }"
        color="#66afb7"
        :formatter="formatter"
        @confirm="onConfirm"
        :confirm-text= "success()"
      />
    </div>
</template>

<script>
export default {
  name: 'Calendar',
  data () {
    return {
      text: '',
      show: false,
      flag: false,
      selected: [],
      curDate: new Date()
    }
  },
  created () {
    this.getDates()
  },
  methods: {
    async getDates () {
      const { data: res } = await this.$http.get('/studyInfo/querySignTime')
      for (var i = 0; i < res.length; i++) {
        var date = new Date(res[i])
        this.selected.push(date)
      }
      if (this.selected[this.selected.length - 1].getFullYear() === this.curDate.getFullYear() &&
        this.selected[this.selected.length - 1].getMonth() === this.curDate.getMonth() &&
        this.selected[this.selected.length - 1].getDate() === this.curDate.getDate()) {
        this.flag = true
      }
    },
    success () {
      return this.flag ? '今日已签到' : '确认签到'
    },
    async onConfirm (date) {
      this.selected.push(date)
      const { data: res } = await this.$http.get('/word/finishToday')
      if (res) {
        this.flag = true
      }
    },
    formatter (day) {
      const year = day.date.getFullYear()
      const month = day.date.getMonth() + 1
      const date = day.date.getDate()

      for (var i = 0; i < this.selected.length; i++) {
        if (this.selected[i].getFullYear() === year && this.selected[i].getMonth() + 1 === month && this.selected[i].getDate() === date) {
          day.bottomInfo = '已打卡'
          day.type = 'selected'
        }
      }
      if (this.curDate.getFullYear() === year && this.curDate.getMonth() + 1 === month && this.curDate.getDate() === date) {
        day.text = '今天'
      } else if ((year > this.curDate.getFullYear()) || (year === this.curDate.getFullYear() && month > this.curDate.getMonth() + 1) || ((year === this.curDate.getFullYear() && month === this.curDate.getMonth() + 1 && date > this.curDate.getDate()))) {
        day.type = 'disabled'
      }
      return day
    }
  }
}
</script>

<style scoped>
</style>
