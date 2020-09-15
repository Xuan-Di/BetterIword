<template>
    <div class="blank">
      <van-loading type="spinner" color="#1989fa" />
    </div>
</template>

<script>
export default {
  name: 'Blank',
  data () {
    return {
      code: ''
    }
  },
  created () {
    this.getPersonInfo()
  },
  methods: {
    // 这个是为了获取地址里面的参数
    getUrlParam (name) {
      const reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)') // 构造一个含有目标参数的正则表达式对象
      const arr = window.location.search.substr(1).match(reg) // 匹配目标参数
      if (arr) {
        this.code = decodeURIComponent(arr[2])
        return decodeURIComponent(arr[2])
      }
      return null
    },
    async getPersonInfo () {
      this.code = this.getUrlParam('code')
      // 调用后台方法：code换openid，openid换人员信息
      // const { data: res } = await this.$http.get('http://a296148l31.zicp.vip/queryAll?code=' + this.code)
      // const { data: res } = await this.$http.get('/queryAll?code=' + this.code)
      const { data: res } = await this.$http.get('/queryInTest?openId=n2244')
      window.sessionStorage.setItem('token', res.token)
      if (res.words) {
        res.words.forEach(e => {
          e.addCollect = false
        })
        this.$store.commit('updateDaily', res.words)
        await this.$router.push({ path: '/review' })
      }
    }
  }
}
</script>

<style lang="less" scoped>
.blank{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

</style>
