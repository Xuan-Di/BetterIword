<template>
    <div class="translate">
      <div class="head">
        <van-icon name="arrow-left" @click="back()"></van-icon>
        <div>自动检测语言</div>
      </div>
      <div class="inp">
        <van-field v-model="value" placeholder="在此输入要翻译的文本" @input="getTranslate()" @clear="this.result='' "/>
      </div>
      <div class="res">
        <van-field v-model="result"/>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Translate',
  data () {
    return {
      value: '',
      result: ''
    }
  },
  methods: {
    back () {
      this.$router.go(-1)
    },
    async getTranslate () {
      const { data: res } = await this.$http.get('/translate', {
        params: {
          word: this.value
        }
      })
      this.result = res
    }
  }

}
</script>

<style lang="less" scoped>
  .translate{
    .head{
      /*background-color: #66afb7;*/
      background: linear-gradient(to top, #98eebc, 25%, #66afb7 );
      display: flex;
      align-items: center;
      .van-icon{
        width: 10%;
        line-height: 54px;
        font-size: 25px;
        text-align: center;
      }
    }
    .inp, .res{
      height: 75px;
      padding: 10px;
      .van-field, .result{
        height: 75px;
        width: 100%;
        box-sizing: border-box;
        line-height: 30px;
        padding: 10px;
        border: 1px solid #eeeeee;
        background-color: #eeeeee;
      }
    }
  }
</style>
