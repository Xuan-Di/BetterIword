import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    dailyList: []
  },
  mutations: {
    // 每天推送的单词
    updateDaily (state, list) {
      // 给每个单词权重,默认按从小到大排列
      list.forEach((item, index) => {
        item.weight = 10 * (index + 1)
      })
      state.dailyList = list
    },
    // 认识的单词
    async addToKnow (state, word) {
      // 将认识的单词从每日单词队列中删除
      state.dailyList.shift()
    },
    addUnClear (state, word) {
      // 将单词的权重增加1/2，重新根据权重排
      // word.weight *= 2.5
      word.weight += 35
      // 将单词加到队尾
      state.dailyList.push(word)
      // 把第一个单词删掉
      state.dailyList.shift()
      // 重新根据weight值排序
      state.dailyList.sort((a, b) => a.weight - b.weight)
    },
    addToUnKnow (state, word) {
      // 将单词的权重增加1/2，重新根据权重排
      // word.weight *= 1.25
      word.weight += 25
      // 将单词加到队尾
      state.dailyList.push(word)
      // 把第一个单词删掉
      state.dailyList.shift()
      // 重新根据weight值排序
      state.dailyList.sort((a, b) => a.weight - b.weight)
    }
  },
  actions: {
  },
  modules: {
  }
})
