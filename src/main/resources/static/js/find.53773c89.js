(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["find"],{"08b3":function(t,e,n){},1276:function(t,e,n){"use strict";var r=n("d784"),s=n("44e7"),i=n("825a"),a=n("1d80"),o=n("4840"),c=n("8aa5"),u=n("50c4"),l=n("14c3"),d=n("9263"),h=n("d039"),v=[].push,f=Math.min,p=4294967295,g=!h((function(){return!RegExp(p,"y")}));r("split",2,(function(t,e,n){var r;return r="c"=="abbc".split(/(b)*/)[1]||4!="test".split(/(?:)/,-1).length||2!="ab".split(/(?:ab)*/).length||4!=".".split(/(.?)(.?)/).length||".".split(/()()/).length>1||"".split(/.?/).length?function(t,n){var r=String(a(this)),i=void 0===n?p:n>>>0;if(0===i)return[];if(void 0===t)return[r];if(!s(t))return e.call(r,t,i);var o,c,u,l=[],h=(t.ignoreCase?"i":"")+(t.multiline?"m":"")+(t.unicode?"u":"")+(t.sticky?"y":""),f=0,g=new RegExp(t.source,h+"g");while(o=d.call(g,r)){if(c=g.lastIndex,c>f&&(l.push(r.slice(f,o.index)),o.length>1&&o.index<r.length&&v.apply(l,o.slice(1)),u=o[0].length,f=c,l.length>=i))break;g.lastIndex===o.index&&g.lastIndex++}return f===r.length?!u&&g.test("")||l.push(""):l.push(r.slice(f)),l.length>i?l.slice(0,i):l}:"0".split(void 0,0).length?function(t,n){return void 0===t&&0===n?[]:e.call(this,t,n)}:e,[function(e,n){var s=a(this),i=void 0==e?void 0:e[t];return void 0!==i?i.call(e,s,n):r.call(String(s),e,n)},function(t,s){var a=n(r,t,this,s,r!==e);if(a.done)return a.value;var d=i(t),h=String(this),v=o(d,RegExp),m=d.unicode,w=(d.ignoreCase?"i":"")+(d.multiline?"m":"")+(d.unicode?"u":"")+(g?"y":"g"),b=new v(g?d:"^(?:"+d.source+")",w),x=void 0===s?p:s>>>0;if(0===x)return[];if(0===h.length)return null===l(b,h)?[h]:[];var _=0,k=0,y=[];while(k<h.length){b.lastIndex=g?k:0;var C,S=l(b,g?h:h.slice(k));if(null===S||(C=f(u(b.lastIndex+(g?0:k)),h.length))===_)k=c(h,k,m);else{if(y.push(h.slice(_,k)),y.length===x)return y;for(var E=1;E<=S.length-1;E++)if(y.push(S[E]),y.length===x)return y;k=_=C}}return y.push(h.slice(_)),y}]}),!g)},"14c3":function(t,e,n){var r=n("c6b6"),s=n("9263");t.exports=function(t,e){var n=t.exec;if("function"===typeof n){var i=n.call(t,e);if("object"!==typeof i)throw TypeError("RegExp exec method returned something other than an Object or null");return i}if("RegExp"!==r(t))throw TypeError("RegExp#exec called on incompatible receiver");return s.call(t,e)}},"15a4":function(t,e,n){"use strict";var r=n("300b"),s=n.n(r);s.a},2444:function(t,e,n){},2957:function(t,e,n){},"300b":function(t,e,n){},"31bd":function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"container"}},[n("van-nav-bar",{attrs:{title:"单词PK","left-arrow":"",border:!0},on:{"click-left":function(e){return t.back()}}}),this.flag?t._e():n("div",{attrs:{id:"main"}},[n("van-button",{attrs:{id:"btn",type:"primary",block:""},on:{click:function(e){return t.matchPlayer()}}},[t._v(" 匹配对手 ")]),this.showWaitTime&&this.waitTime?n("h2",[t._v("等待时间："+t._s(this.waitTime)+"s")]):t._e(),n("van-button",{attrs:{type:"primary",block:""},on:{click:function(e){return t.cancelQueue()}}},[t._v("取消排队")])],1),this.flag?n("div",[n("div",{attrs:{id:"content"}},[n("header",[n("div",{staticClass:"headImg"},[n("div",{staticClass:"box1"},[n("img",{attrs:{src:t.userInfo.headImgUrl}}),n("div",[t._v(t._s(t.userInfo.nickName))])]),n("h2",[t._v(t._s(t.curCount))]),n("div",{staticClass:"box2"},[n("img",{attrs:{src:t.opponentUser.headImgUrl}}),n("div",[t._v(t._s(t.opponentName))])])])]),n("main",[n("div",{staticClass:"bar1"},[n("div",{staticClass:"process"},[t._v(t._s(this.score))])]),n("div",{staticClass:"qa"},[n("div",{staticClass:"question"},[t._v(t._s(this.words[this.currentTimu].englishWord))]),n("div",{staticClass:"option"},[n("div",{class:t.optionsStyle[0],on:{click:function(e){return t.answerEvent(e,0)}}},[t._v(t._s(t.words[t.currentTimu].queone))]),n("div",{class:t.optionsStyle[1],on:{click:function(e){return t.answerEvent(e,1)}}},[t._v(t._s(t.words[t.currentTimu].quetow))]),n("div",{class:t.optionsStyle[2],on:{click:function(e){return t.answerEvent(e,2)}}},[t._v(t._s(t.words[t.currentTimu].quethree))]),n("div",{class:t.optionsStyle[3],on:{click:function(e){return t.answerEvent(e,3)}}},[t._v(t._s(t.words[t.currentTimu].quefour))])])]),n("div",{staticClass:"bar2"},[n("div",{staticClass:"process"},[t._v(t._s(this.opScore))])])])])]):t._e(),n("van-dialog")],1)},s=[],i=(n("96cf"),n("1da1")),a=n("d399"),o=n("2241"),c={name:"websocket",created:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var n,r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$http.get("/user/queryUserByOpenId");case 2:n=e.sent,r=n.data,r&&(t.userInfo=r);case 5:case"end":return e.stop()}}),e)})))()},data:function(){return{websocket:null,username:window.sessionStorage.getItem("token"),opponentName:"",waitTime:0,showWaitTime:!1,timer:null,flag:!1,words:[],curCount:10,currentTimu:0,optionsStyle:["optionItem","optionItem","optionItem","optionItem"],isChoose:!1,score:0,opScore:0,userInfo:{},opponentUser:{},countDownTimer:null,btn:null,show:!0}},mounted:function(){this.btn=document.querySelector("#btn"),this.btn.disabled=!1},methods:{onConfirm:function(){var t={code:1,item:"传输数据"};this.websocketsend(JSON.stringify(t))},initWebSocket:function(t){this.websock=new WebSocket("ws://106.14.44.65:8889/websocket/"+t),this.websock.onmessage=this.websocketonmessage,this.websock.onerror=this.websocketonerror,this.websock.onopen=this.websocketonopen,this.websock.onclose=this.websocketclose},websocketonopen:function(){var t={code:0,msg:"这是client：初次连接"};this.websocketsend(JSON.stringify(t))},websocketonerror:function(){},websocketonmessage:function(t){var e=JSON.parse(t.data),n=document.querySelector(".bar2 .process");if("match"===e.message)this.showWaitTime=!1,this.waitTime=0,clearInterval(this.timer),this.opponentUser=e.content.opponentUser,this.opponentName=e.content.opponentUser.openId,this.flag=!0,this.words=e.content.words,this.countDownTimer=setInterval(this.countDown,1e3);else if("score"==e.message){this.opScore+=10;var r=100*(1-this.opScore/10/this.words.length);n.style.transform="translateY("+r+"%)"}},websocketsend:function(t){this.websock.send(t)},websocketclose:function(t){},matchPlayer:function(t){var e=this;return Object(i["a"])(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return e.waitTime=0,e.showWaitTime=!0,e.initWebSocket(e.username),e.timer=setInterval((function(){e.waitTime++}),1e3),t.next=6,e.$http.get("http://106.14.44.65:8889/enterQueue/"+e.username).then((function(t){200===t.status?e.btn.disabled=!0:Object(a["a"])({message:"请重新排队"})}));case 6:case"end":return t.stop()}}),t)})))()},submitAnswer:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var n;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return n={username:t.username,answer:!0,opponentName:t.opponentName},e.next=3,t.$http.post("http://106.14.44.65:8889/submitAnswer",n).then((function(t){}));case 3:case"end":return e.stop()}}),e)})))()},cancelQueue:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$http.get("http://106.14.44.65:8889/cancelQueue/"+t.username).then((function(e){"200"===e.data.code?(t.showWaitTime=!1,clearInterval(t.timer),t.btn.disabled=!1):Object(a["a"])({message:"系统故障，请重试"})}));case 2:case"end":return e.stop()}}),e)})))()},countDown:function(){var t=this;this.curCount=this.curCount-1,this.currentTimu===this.words.length&&(clearInterval(this.countDownTimer),this.show=!0,o["a"].alert({title:"标题",message:"".concat(this.score>this.opScore?this.userInfo.nickName:this.opponentName,"赢了"),theme:"round-button"}).then(Object(i["a"])(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$router.push({path:"./setting"}).catch((function(t){}));case 2:case"end":return e.stop()}}),e)}))))),-1===this.curCount&&(this.currentTimu++,this.optionsStyle=["optionItem","optionItem","optionItem","optionItem"],this.isChoose=!1,this.curCount=10)},answerEvent:function(t,e){if(!this.isChoose){var n=this.words[this.currentTimu],r=this.optionsStyle,s=n.realque;if(s===t.target.innerHTML){var i=document.querySelector(".bar1 .process");r[e]="correct",this.optionsStyle=r,this.isChoose=!0,this.score+=10;var a=100-10*this.score/this.words.length;i.style.transform="translateY("+a+"%)",this.submitAnswer()}else{r[e]="error";var o=4;for(var c in n)if(n[c]===n.realque&&"realque"!==c){switch(c){case"queone":o=0;break;case"quetow":o=1;break;case"quethree":o=2;break;case"quefour":o=3;break;default:break}r[o]="correct"}this.optionsStyle=r}}}},destroyed:function(){this.websock.close(),clearInterval(this.countDownTimer)}},u=c,l=(n("c8f8"),n("2877")),d=Object(l["a"])(u,r,s,!1,null,"9056db4e",null);e["default"]=d.exports},"327b":function(t,e,n){},"44e7":function(t,e,n){var r=n("861d"),s=n("c6b6"),i=n("b622"),a=i("match");t.exports=function(t){var e;return r(t)&&(void 0!==(e=t[a])?!!e:"RegExp"==s(t))}},"48e7":function(t,e,n){"use strict";var r=n("2957"),s=n.n(r);s.a},"4aa9":function(t,e,n){"use strict";var r=n("a1c9"),s=n.n(r);s.a},"4b60":function(t,e,n){},5319:function(t,e,n){"use strict";var r=n("d784"),s=n("825a"),i=n("7b0b"),a=n("50c4"),o=n("a691"),c=n("1d80"),u=n("8aa5"),l=n("14c3"),d=Math.max,h=Math.min,v=Math.floor,f=/\$([$&'`]|\d\d?|<[^>]*>)/g,p=/\$([$&'`]|\d\d?)/g,g=function(t){return void 0===t?t:String(t)};r("replace",2,(function(t,e,n,r){var m=r.REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE,w=r.REPLACE_KEEPS_$0,b=m?"$":"$0";return[function(n,r){var s=c(this),i=void 0==n?void 0:n[t];return void 0!==i?i.call(n,s,r):e.call(String(s),n,r)},function(t,r){if(!m&&w||"string"===typeof r&&-1===r.indexOf(b)){var i=n(e,t,this,r);if(i.done)return i.value}var c=s(t),v=String(this),f="function"===typeof r;f||(r=String(r));var p=c.global;if(p){var _=c.unicode;c.lastIndex=0}var k=[];while(1){var y=l(c,v);if(null===y)break;if(k.push(y),!p)break;var C=String(y[0]);""===C&&(c.lastIndex=u(v,a(c.lastIndex),_))}for(var S="",E=0,I=0;I<k.length;I++){y=k[I];for(var R=String(y[0]),T=d(h(o(y.index),v.length),0),$=[],W=1;W<y.length;W++)$.push(g(y[W]));var O=y.groups;if(f){var j=[R].concat($,T,v);void 0!==O&&j.push(O);var q=String(r.apply(void 0,j))}else q=x(R,v,T,$,O,r);T>=E&&(S+=v.slice(E,T)+q,E=T+R.length)}return S+v.slice(E)}];function x(t,n,r,s,a,o){var c=r+t.length,u=s.length,l=p;return void 0!==a&&(a=i(a),l=f),e.call(o,l,(function(e,i){var o;switch(i.charAt(0)){case"$":return"$";case"&":return t;case"`":return n.slice(0,r);case"'":return n.slice(c);case"<":o=a[i.slice(1,-1)];break;default:var l=+i;if(0===l)return e;if(l>u){var d=v(l/10);return 0===d?e:d<=u?void 0===s[d-1]?i.charAt(1):s[d-1]+i.charAt(1):e}o=s[l-1]}return void 0===o?"":o}))}}))},"558d":function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",[r("div",{staticClass:"head"},[r("van-icon",{attrs:{name:"arrow-left"},on:{click:function(e){return t.back()}}}),r("img",{attrs:{src:n("6a51")}}),r("div",[r("p",[t._v("历年四级英语听力真题")]),r("p",[t._v(t._s(t.list.length)+"个课程")])])],1),r("ul",t._l(t.list,(function(e,n){return r("router-link",{key:n,attrs:{tag:"li",to:{name:"cet4detail",params:{prop:e}}}},[t._v(t._s(e))])})),1)])},s=[],i=(n("96cf"),n("1da1")),a={name:"CET4",data:function(){return{list:[]}},created:function(){this.getList()},methods:{getList:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var n,r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$http.get("/cet/tingLiFourList");case 2:n=e.sent,r=n.data,t.list=r;case 5:case"end":return e.stop()}}),e)})))()},back:function(){this.$router.go(-1)}}},o=a,c=(n("6f58"),n("2877")),u=Object(c["a"])(o,r,s,!1,null,"6e02155b",null);e["default"]=u.exports},6547:function(t,e,n){var r=n("a691"),s=n("1d80"),i=function(t){return function(e,n){var i,a,o=String(s(e)),c=r(n),u=o.length;return c<0||c>=u?t?"":void 0:(i=o.charCodeAt(c),i<55296||i>56319||c+1===u||(a=o.charCodeAt(c+1))<56320||a>57343?t?o.charAt(c):i:t?o.slice(c,c+2):a-56320+(i-55296<<10)+65536)}};t.exports={codeAt:i(!1),charAt:i(!0)}},"6a51":function(t,e,n){t.exports=n.p+"img/cet42.5e0f65af.svg"},"6cd5":function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"newwords"},[n("van-nav-bar",{attrs:{title:"生词本","left-arrow":"",border:!0},on:{"click-left":function(e){return t.back()}},scopedSlots:t._u([{key:"right",fn:function(){return[n("van-dropdown-menu",[n("van-dropdown-item",{attrs:{options:t.option1},on:{change:function(e){return t.changeSort()}},model:{value:t.value1,callback:function(e){t.value1=e},expression:"value1"}})],1)]},proxy:!0}])}),t.showdetail?n("div",[n("div",{staticClass:"word"},[n("div",{staticClass:"left",on:{click:function(e){return t.resite()}}},[n("div",{staticClass:"english"},[t._v(t._s(t.datails.englishWord))]),n("div",{staticClass:"pronounce"},[t._v("英["+t._s(t.datails.pa)+"]"),n("van-icon",{attrs:{name:"volume"}})],1),n("audio",{ref:"audio",attrs:{autoplay:""}})])]),n("div",{staticClass:"explanation"},[n("span",[t._v(t._s(t.datails.chineseWord))])]),n("div",{staticClass:"examples"},[n("div",{staticClass:"sentences"},[n("div",{staticClass:"title"},[n("span",[t._v("例句")]),n("van-icon",{attrs:{name:"wap-nav"}})],1),n("div",{staticClass:"content"},[n("p",[t._v(t._s(t.datails.engInstance1))]),n("p",[t._v(t._s(t.datails.chiInstance1))]),n("p",[t._v(t._s(t.datails.engInstance2))]),n("p",[t._v(t._s(t.datails.chiInstance2))])])])])]):n("div",{staticClass:"list"},[t._m(0),t._l(t.newWordsList,(function(e,r){return n("van-swipe-cell",{key:r,scopedSlots:t._u([{key:"right",fn:function(){return[n("van-button",{attrs:{square:"",type:"primary",text:"详情"},on:{click:function(n){return t.details(e)}}}),n("van-button",{attrs:{square:"",type:"danger",text:"移除"},on:{click:function(n){return t.removeit(e)}}})]},proxy:!0}],null,!0)},[n("van-cell",{attrs:{border:!1,title:e.englishWord,value:e.chineseWord}})],1)}))],2)],1)},s=[function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"tishi"},[r("img",{staticStyle:{display:"none"},attrs:{id:"animate",src:n("a86f")}})])}],i=(n("96cf"),n("1da1")),a={name:"NewWords",data:function(){return{newWordsList:[],datails:{},showdetail:!1,value1:0,option1:[{text:"字母",value:0},{text:"词频",value:1}]}},created:function(){this.getNewWords()},mounted:function(){var t=document.querySelector("#animate");t.style.display="block",t.classList.add("move"),t.addEventListener("animationend",(function(){t.style.display="none"}))},methods:{getNewWords:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var n,r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$http.get("/userWord/queryAndSort");case 2:n=e.sent,r=n.data,t.newWordsList=r;case 5:case"end":return e.stop()}}),e)})))()},details:function(t){this.datails=t,this.showdetail=!0},resite:function(){this.$refs.audio.src=this.datails.pron},back:function(){this.showdetail=!1,this.$router.go(-1)},removeit:function(t){var e=this;return Object(i["a"])(regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return n.next=2,e.$http.get("/userWord/deleteWord",{params:{englishWord:t.englishWord}});case 2:n.sent,e.getNewWords();case 4:case"end":return n.stop()}}),n)})))()},changeSort:function(){0===this.value1?this.newWordsList.sort((function(t,e){return t.englishWord>e.englishWord?1:t.englishWord<e.englishWord?-1:0})):1===this.value1&&this.newWordsList.sort((function(t,e){return e.collect-t.collect}))}}},o=a,c=(n("4aa9"),n("2877")),u=Object(c["a"])(o,r,s,!1,null,"dfaf1a8e",null);e["default"]=u.exports},"6f58":function(t,e,n){"use strict";var r=n("4b60"),s=n.n(r);s.a},"6f77":function(t,e,n){},7939:function(t,e,n){"use strict";var r=n("327b"),s=n.n(r);s.a},8012:function(t,e,n){"use strict";var r=n("2444"),s=n.n(r);s.a},"81d5":function(t,e,n){"use strict";var r=n("7b0b"),s=n("23cb"),i=n("50c4");t.exports=function(t){var e=r(this),n=i(e.length),a=arguments.length,o=s(a>1?arguments[1]:void 0,n),c=a>2?arguments[2]:void 0,u=void 0===c?n:s(c,n);while(u>o)e[o++]=t;return e}},8498:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",[r("div",{staticClass:"head"},[r("van-icon",{attrs:{name:"arrow-left"},on:{click:function(e){return t.back()}}}),r("img",{attrs:{src:n("cf80")}}),r("div",[r("p",[t._v("历年六级英语听力真题")]),r("p",[t._v(t._s(t.list.length)+"个课程")])])],1),r("ul",t._l(t.list,(function(e,n){return r("router-link",{key:n,attrs:{tag:"li",to:{name:"cet6detail",params:{prop:e}}}},[t._v(t._s(e))])})),1)])},s=[],i=(n("96cf"),n("1da1")),a={name:"CET6",data:function(){return{list:[]}},created:function(){this.getList()},methods:{getList:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var n,r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$http.get("/cet/tingLiSixList");case 2:n=e.sent,r=n.data,t.list=r;case 5:case"end":return e.stop()}}),e)})))()},back:function(){this.$router.go(-1)}}},o=a,c=(n("15a4"),n("2877")),u=Object(c["a"])(o,r,s,!1,null,"b1a27716",null);e["default"]=u.exports},8582:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"translate"},[n("div",{staticClass:"head"},[n("van-icon",{attrs:{name:"arrow-left"},on:{click:function(e){return t.back()}}}),n("div",[t._v("自动检测语言")])],1),n("div",{staticClass:"inp"},[n("van-field",{attrs:{placeholder:"在此输入要翻译的文本"},on:{input:function(e){return t.getTranslate()},clear:function(t){this.result=""}},model:{value:t.value,callback:function(e){t.value=e},expression:"value"}})],1),n("div",{staticClass:"res"},[n("van-field",{model:{value:t.result,callback:function(e){t.result=e},expression:"result"}})],1)])},s=[],i=(n("96cf"),n("1da1")),a={name:"Translate",data:function(){return{value:"",result:""}},methods:{back:function(){this.$router.go(-1)},getTranslate:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var n,r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$http.get("/translate",{params:{word:t.value}});case 2:n=e.sent,r=n.data,t.result=r;case 5:case"end":return e.stop()}}),e)})))()}}},o=a,c=(n("7939"),n("2877")),u=Object(c["a"])(o,r,s,!1,null,"29156b7a",null);e["default"]=u.exports},"8aa5":function(t,e,n){"use strict";var r=n("6547").charAt;t.exports=function(t,e,n){return e+(n?r(t,e).length:1)}},9263:function(t,e,n){"use strict";var r=n("ad6d"),s=n("9f7f"),i=RegExp.prototype.exec,a=String.prototype.replace,o=i,c=function(){var t=/a/,e=/b*/g;return i.call(t,"a"),i.call(e,"a"),0!==t.lastIndex||0!==e.lastIndex}(),u=s.UNSUPPORTED_Y||s.BROKEN_CARET,l=void 0!==/()??/.exec("")[1],d=c||l||u;d&&(o=function(t){var e,n,s,o,d=this,h=u&&d.sticky,v=r.call(d),f=d.source,p=0,g=t;return h&&(v=v.replace("y",""),-1===v.indexOf("g")&&(v+="g"),g=String(t).slice(d.lastIndex),d.lastIndex>0&&(!d.multiline||d.multiline&&"\n"!==t[d.lastIndex-1])&&(f="(?: "+f+")",g=" "+g,p++),n=new RegExp("^(?:"+f+")",v)),l&&(n=new RegExp("^"+f+"$(?!\\s)",v)),c&&(e=d.lastIndex),s=i.call(h?n:d,g),h?s?(s.input=s.input.slice(p),s[0]=s[0].slice(p),s.index=d.lastIndex,d.lastIndex+=s[0].length):d.lastIndex=0:c&&s&&(d.lastIndex=d.global?s.index+s[0].length:e),l&&s&&s.length>1&&a.call(s[0],n,(function(){for(o=1;o<arguments.length-2;o++)void 0===arguments[o]&&(s[o]=void 0)})),s}),t.exports=o},"9f7f":function(t,e,n){"use strict";var r=n("d039");function s(t,e){return RegExp(t,e)}e.UNSUPPORTED_Y=r((function(){var t=s("a","y");return t.lastIndex=2,null!=t.exec("abcd")})),e.BROKEN_CARET=r((function(){var t=s("^r","gy");return t.lastIndex=2,null!=t.exec("str")}))},a1c9:function(t,e,n){},a86f:function(t,e,n){t.exports=n.p+"img/shouzhi.6b27968c.svg"},ac1f:function(t,e,n){"use strict";var r=n("23e7"),s=n("9263");r({target:"RegExp",proto:!0,forced:/./.exec!==s},{exec:s})},ad6d:function(t,e,n){"use strict";var r=n("825a");t.exports=function(){var t=r(this),e="";return t.global&&(e+="g"),t.ignoreCase&&(e+="i"),t.multiline&&(e+="m"),t.dotAll&&(e+="s"),t.unicode&&(e+="u"),t.sticky&&(e+="y"),e}},c106:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"search"},[n("div",{staticClass:"head"},[n("van-icon",{attrs:{name:"arrow-left"},on:{click:function(e){return t.back()}}}),n("van-search",{attrs:{shape:"round",background:"linear-gradient(to top, #98eebc, 25%, #66afb7 )",placeholder:"请输入搜索关键词"},on:{clear:function(e){return t.clear()},input:function(e){return t.getSearchResult()}},model:{value:t.value,callback:function(e){t.value=e},expression:"value"}})],1),n("div",{staticClass:"translate",on:{click:function(e){return t.toTranslate()}}},[t._v(" 翻译 ")]),t.showdetail?n("div",[n("div",{staticClass:"word"},[n("div",{staticClass:"left",on:{click:function(e){return t.resite()}}},[n("div",{staticClass:"english"},[t._v(t._s(t.datails.englishWord))]),n("div",{staticClass:"pronounce"},[t._v("英["+t._s(t.datails.pa)+"]"),n("van-icon",{attrs:{name:"volume"}})],1),n("audio",{ref:"audio",attrs:{autoplay:""}})])]),n("div",{staticClass:"explanation"},[n("span",[t._v(t._s(t.datails.chineseWord))])]),n("div",{staticClass:"examples"},[n("div",{staticClass:"sentences"},[n("div",{staticClass:"title"},[n("span",[t._v("例句")]),n("van-icon",{attrs:{name:"wap-nav"}})],1),n("div",{staticClass:"content"},[n("p",[t._v(t._s(t.datails.engInstance1))]),n("p",[t._v(t._s(t.datails.chiInstance1))]),n("p",[t._v(t._s(t.datails.engInstance2))]),n("p",[t._v(t._s(t.datails.chiInstance2))])])])])]):n("ul",{staticClass:"result"},t._l(t.list,(function(e,r){return n("li",{key:r,on:{click:function(n){return t.detail(e)}}},[t._v(" "+t._s(e.englishWord)+" "+t._s(e.chineseWord)+" ")])})),0)])},s=[],i=(n("96cf"),n("1da1")),a={name:"Search",data:function(){return{value:"",list:[],datails:{},showdetail:!1}},methods:{back:function(){this.$router.go(-1)},getSearchResult:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var n,r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$http.get("/word/queryInEs",{params:{keyword:t.value}});case 2:n=e.sent,r=n.data,t.list=r;case 5:case"end":return e.stop()}}),e)})))()},detail:function(t){this.datails=t,this.showdetail=!0},resite:function(){this.$refs.audio.src=this.datails.pron},toTranslate:function(){this.$router.push({name:"translate"})},clear:function(){this.list=[],this.datails={},this.showdetail=!1}}},o=a,c=(n("48e7"),n("2877")),u=Object(c["a"])(o,r,s,!1,null,"4cfe3aa0",null);e["default"]=u.exports},c8f8:function(t,e,n){"use strict";var r=n("6f77"),s=n.n(r);s.a},cb29:function(t,e,n){var r=n("23e7"),s=n("81d5"),i=n("44d2");r({target:"Array",proto:!0},{fill:s}),i("fill")},ce7f:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("van-nav-bar",{attrs:{title:"单词填充","left-arrow":""},on:{"click-left":function(e){return t.back()}}}),n("div",{staticStyle:{margin:"8vh auto",padding:"2em"}},[n("div",{staticStyle:{margin:"3em auto","font-family":"Consolas"}},[n("div",{staticStyle:{display:"flex","justify-content":"center","font-size":"2em"}},[t._v(t._s(t.res))]),n("div",{staticStyle:{display:"flex","justify-content":"center",margin:"1em auto",color:"#576b5a"}},[t._v(t._s(t.currentWord.pa))]),n("div",{staticStyle:{display:"flex","justify-content":"center",color:"#576b5a"}},[t._v(t._s(t.currentWord.chineseWord))])]),n("div",{staticStyle:{display:"flex","justify-content":"space-around","font-family":"Consolas"}},[n("van-button",{staticStyle:{width:"50px","font-weight":"bold"},attrs:{type:"primary"},on:{click:function(e){return t.judge(t.choice[0])}}},[t._v(t._s(t.choice[0]))]),n("van-button",{staticStyle:{width:"50px","font-weight":"bold"},attrs:{type:"info"},on:{click:function(e){return t.judge(t.choice[1])}}},[t._v(t._s(t.choice[1]))]),n("van-button",{staticStyle:{width:"50px","font-weight":"bold"},attrs:{type:"warning"},on:{click:function(e){return t.judge(t.choice[2])}}},[t._v(t._s(t.choice[2]))])],1)])],1)},s=[],i=(n("cb29"),n("ac1f"),n("5319"),n("1276"),n("96cf"),n("1da1")),a=n("2b0e"),o=n("d399");o["a"].setDefaultOptions({duration:1e3}),a["a"].use(o["a"]);var c={name:"ReView",data:function(){return{list:[],currentWord:"",strarr:"abcdefghijklmnopqrstuvwxyz".split(""),choice:[],fill:"",index:0,res:""}},created:function(){this.getReviewWords()},methods:{getReviewWords:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){var n,r,s;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,t.$http.get("/word/review");case 2:n=e.sent,r=n.data,0!==r.length?(t.list=r,t.currentWord=r[0],s=t.currentWord.englishWord,t.show(s)):t.$toast({message:"您没有单词没有复习"});case 5:case"end":return e.stop()}}),e)})))()},show:function(t){var e=Math.floor(Math.random()*t.length),n=t[e];this.fill=n,this.res=t.replace(n,"_");var r=[];r.push(n,this.strarr[Math.round(Math.random()*(this.strarr.length-1))],this.strarr[Math.round(Math.random()*(this.strarr.length-1))]),r.sort(),this.choice=r},judge:function(t){if(t===this.fill)if(20===this.index)this.$toast({message:"今日复习已完毕"});else{this.$toast({message:"正确"}),this.currentWord=this.list[++this.index];var e=this.currentWord.englishWord;this.show(e)}else this.$toast({message:"错误"})},back:function(){this.$router.go(-1)}}},u=c,l=(n("f0fd"),n("2877")),d=Object(l["a"])(u,r,s,!1,null,"714dce7e",null);e["default"]=d.exports},cf80:function(t,e,n){t.exports=n.p+"img/cet6.b52c7268.svg"},d784:function(t,e,n){"use strict";n("ac1f");var r=n("6eeb"),s=n("d039"),i=n("b622"),a=n("9263"),o=n("9112"),c=i("species"),u=!s((function(){var t=/./;return t.exec=function(){var t=[];return t.groups={a:"7"},t},"7"!=="".replace(t,"$<a>")})),l=function(){return"$0"==="a".replace(/./,"$0")}(),d=i("replace"),h=function(){return!!/./[d]&&""===/./[d]("a","$0")}(),v=!s((function(){var t=/(?:)/,e=t.exec;t.exec=function(){return e.apply(this,arguments)};var n="ab".split(t);return 2!==n.length||"a"!==n[0]||"b"!==n[1]}));t.exports=function(t,e,n,d){var f=i(t),p=!s((function(){var e={};return e[f]=function(){return 7},7!=""[t](e)})),g=p&&!s((function(){var e=!1,n=/a/;return"split"===t&&(n={},n.constructor={},n.constructor[c]=function(){return n},n.flags="",n[f]=/./[f]),n.exec=function(){return e=!0,null},n[f](""),!e}));if(!p||!g||"replace"===t&&(!u||!l||h)||"split"===t&&!v){var m=/./[f],w=n(f,""[t],(function(t,e,n,r,s){return e.exec===a?p&&!s?{done:!0,value:m.call(e,n,r)}:{done:!0,value:t.call(n,e,r)}:{done:!1}}),{REPLACE_KEEPS_$0:l,REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE:h}),b=w[0],x=w[1];r(String.prototype,t,b),r(RegExp.prototype,f,2==e?function(t,e){return x.call(t,this,e)}:function(t){return x.call(t,this)})}d&&o(RegExp.prototype[f],"sham",!0)}},f0fd:function(t,e,n){"use strict";var r=n("08b3"),s=n.n(r);s.a},f9fe:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("div",{staticClass:"header"},[t._v("发现")]),n("div",{staticClass:"container"},[n("div",{staticClass:"shake"},[t._v("四六级倒计时")]),n("div",{staticClass:"countDown"},[n("div",[t._v(t._s(t.d)+" "),n("div",{staticClass:"badge"},[t._v("天")])]),n("div",[t._v(t._s(t.h)+" "),n("div",{staticClass:"badge"},[t._v("时")])]),n("div",[t._v(t._s(t.m)+" "),n("div",{staticClass:"badge"},[t._v("分")])]),n("div",[t._v(t._s(t.s)+" "),n("div",{staticClass:"badge"},[t._v("秒")])])])]),n("div",{staticClass:"boxs"},[n("router-link",{staticClass:"fuxi box",attrs:{to:"/fuxi"}},[n("img",{attrs:{src:"http://wzxlq.xyz/iword/restudy.svg"}}),n("div",[t._v("复习")])]),n("router-link",{staticClass:"fanyi box",attrs:{to:"/translate"}},[n("img",{attrs:{src:"http://wzxlq.xyz/iword/translate.svg"}}),n("div",[t._v("翻译")])]),n("router-link",{staticClass:"query box",attrs:{to:"/search"}},[n("img",{attrs:{src:"http://wzxlq.xyz/iword/search.svg"}}),n("div",[t._v("查询")])]),n("router-link",{staticClass:"newwords box",attrs:{to:"/newWords"}},[n("img",{attrs:{src:"http://wzxlq.xyz/iword/book.svg"}}),n("div",[t._v("生词本")])]),n("router-link",{staticClass:"box",attrs:{to:"/cet4"}},[n("img",{attrs:{src:"http://wzxlq.xyz/iword/listen_4.svg"}}),n("div",[t._v("四级听力")])]),n("router-link",{staticClass:"box",attrs:{to:"/cet6"}},[n("img",{attrs:{src:"http://wzxlq.xyz/iword/listen_6.svg"}}),n("span",[t._v("六级听力")])]),n("router-link",{staticClass:"box",attrs:{to:"/pk"}},[n("img",{attrs:{alt:"",src:"http://wzxlq.xyz/iword/listen_6.svg"}}),n("span",[t._v("单词PK")])])],1)])},s=[],i={data:function(){return{d:0,h:0,m:0,s:0}},created:function(){setInterval(this.getRTime,0)},methods:{getRTime:function(){var t,e=new Date,n=e.getFullYear();e>new Date(n+"/12/18 00:00:00")?t=new Date(n+1+"/6/18 00:00:00"):e>new Date(n+"/6/18 00:00:00")&&e<new Date(n+"/12/18 00:00:00")?t=new Date(n+"/12/18 00:00:00"):e<new Date(n+"/6/18 00:00:00")&&(t=new Date(n+"/6/18 00:00:00"));var r=t.getTime()-e.getTime();function s(t){return t<10?"0"+t:t}r>=0&&(this.d=Math.floor(r/1e3/60/60/24),this.h=s(Math.floor(r/1e3/60/60%24)),this.m=s(Math.floor(r/1e3/60%60)),this.s=s(Math.floor(r/1e3%60)))}}},a=i,o=(n("8012"),n("2877")),c=Object(o["a"])(a,r,s,!1,null,"7701a882",null);e["default"]=c.exports}}]);
//# sourceMappingURL=find.53773c89.js.map