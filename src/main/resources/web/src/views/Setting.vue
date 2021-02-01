<template>
    <div id="setting">
      <div class="header">
        <div class="info">
          <img :src="userInfo.headImgUrl">
          <div>{{userInfo.nickName}}</div>
        </div>
      </div>
      <div class="data">
        <div>连续签到：<span class="rank">{{num.linkSignInCount}}</span></div>
        <div>已背单词：<span class="rank">{{num.wordCount}}</span></div>
      </div>
      <div class="list">
        <ul>
          <li>
            <img src="../assets/plan.svg">
            <van-field
            readonly
            clickable
            :value="value"
            @click="showPicker = true"
            placeholder="选择计划"
          />
            <van-popup v-model="showPicker" round >
              <van-picker ref="pickerRef" show-toolbar title="我的计划" :columns="columns" @cancel="showPicker = false" @confirm="onConfirm"/>
            </van-popup></li>
<!--          <li><img src="../assets/neighbor.svg"><span>附近的人</span></li>-->
          <router-link tag="li" to="/records"><img src="../assets/calendar.svg"><span>打卡记录</span></router-link>
          <router-link tag="li" to="/test"><img src="../assets/test.svg"><span>词汇量测试</span></router-link>
<!--          <li><img src="../assets/night.svg"><span>夜间模式</span><van-switch v-model="checked" /></li>-->
          <li><img src="../assets/alarm.svg"><span>复习提醒</span><van-switch v-model="remind" @change="setRemind()"/></li>
        </ul>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Setting',
  data () {
    return {
      userInfo: [],
      num: [],
      checked: true,
      remind: false,
      value: '',
      showPicker: false,
      columns: [
        {
          text: '20',
          children: [
            {
              text: '64'
            }
          ]
        },
        {
          text: '30',
          children: [
            {
              text: '43'
            }
          ]
        },
        {
          text: '40',
          children: [
            {
              text: '32'
            }
          ]
        }
      ]
    }
  },
  created () {
    this.getUserInfo()
  },
  methods: {
    onConfirm (value) {
      this.value = '每日单词 :' + value[0] + '    剩余天数 :' + value[1]
      this.showPicker = false
      this.$http.get('/user/setDailyCount?dailyCount=' + value[0])
    },
    async getUserInfo () {
      const { data: res } = await this.$http.get('/user/queryUserByOpenId')
      if (res) {
        this.userInfo = res
      }
      const { data: num } = await this.$http.get('/queryMyRank')
      if (num) {
        this.num = num
      }
      this.columns.forEach(e => {
        e.children[0].text = Math.ceil((1280 - this.num.wordCount) / e.text) + ''
      })
      this.value = '每日单词 :' + this.num.dailyCount + '    剩余天数 :' + Math.ceil((1280 - this.num.wordCount) / this.num.dailyCount)
      this.remind = this.num.isTixing === 1
    },
    async setRemind () {
      var isTiXing = 1
      if (this.remind) {
        isTiXing = 1
      } else {
        isTiXing = 0
      }
      await this.$http.get('/user/setIsTiXing?isTiXing=' + isTiXing)
    }
  }

}
</script>

<style lang="less" scoped>
  #setting{
    .header{
      height: 100px;
      display: flex;
      padding-left: 20px;
      align-items: center;
      /*background-color: #66afb7;*/
      background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
      .info{
        display: flex;
        justify-content: center;
        align-items: center;
        img{
          width: 60px;
          border-radius: 5px;
          margin-right: 10px;
        }
      }
    }
    .data{
      height: 2em;
      line-height: 2em;
      border: 5px solid #eeeeee;
      display: flex;
      justify-content: space-around;
      .rank{
        color: #66afb7;
        font-weight: bolder;
      }
    }
    .list{
      ul{
        padding: 5px;
        li{
          display: flex;
          align-items: center;
          height: 3em;
          line-height: 3em;
          border-bottom: 2px solid #eeeeee;
          padding-left: 10px;
          position: relative;
          img{
            margin-right: 10px;
            height: 2em;
            line-height: 2em;
          }
          .van-switch{
            right: 10px;
            position: absolute;
            right: 10px;
          }
          .van-popup{
            width: 200px;
          }
        }
      }
    }
  }

</style>
