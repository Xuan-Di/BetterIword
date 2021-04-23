//package com.wzxlq.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.wzxlq.dao.UserDao;
//import com.wzxlq.entity.TingLi;
//import com.wzxlq.entity.Word;
//import com.wzxlq.utils.GetContinuousSignInDay;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.*;
//
//
///**
// * @author 王照轩
// * @date 2020/4/15 - 17:10
// */
//@SpringBootTest
//class UserServiceImplTest {
//    @Resource
//    private UserDao userDao;
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//    public HashMap<String, List<Integer>> studyInfoMap = new HashMap<>();
//    public List<Integer> list = new ArrayList<>();
//    public HashMap<String, Queue<Word>> reviewMap = new HashMap<>();
//
//    @Test
//    void queryById() {
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.remove(Integer.valueOf(1));
//        list.remove(Integer.valueOf(2));
//        list.remove(Integer.valueOf(3));
//        System.out.println(list == null);
//        System.out.println(list.size());
//    }
//
//    @Test
//    void mtets() {
//        LocalDate of = LocalDate.of(2020, 6, 1);
//        LocalDate ont = LocalDate.of(2020, 5, 31);
//        int distanceDay = GetContinuousSignInDay.distanceDay(of, ont);
//        System.out.println(distanceDay);
//    }
//
//    @Test
//    public void findsiji() throws IOException {
//        //String targetUrl = "http://m.kekenet.com/cet6/ljtl/cet6zhenti";
//        String targetUrl = "http://m.kekenet.com/cet4/tl/cet4zhenti";
//        //获取connetion
//        Connection connection = Jsoup.connect(targetUrl);
//        //伪造请求头
//        connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        connection.header("Accept-Encoding", "gzip, deflate");
//        connection.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
//        connection.header("Cache-Control", "no-cache");
//        connection.header("Connection", "keep-alive");
//        connection.header("Host", "m.kekenet.com");
//        connection.header("User-Agent", "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Mobile Safari/537.36");
//        connection.ignoreHttpErrors(true);
//        //执行
//        Connection.Response response = connection.method(Connection.Method.GET).execute();
//        //获取爬取结果
//        Document document = response.parse();
//        Elements select = document.select(".listMain");
//        Elements elements = select.select(".listItem");
//        int count = elements.size();
//        ArrayList<TingLi> list = new ArrayList<>();
//        for (Element element : elements) {
//            count--;
//            if (count < 3) {
//                break;
//            }
//            String href = element.select("a").first().attr("href");
//            String s = "http://m.kekenet.com" + href;
//            Connection con = Jsoup.connect(s);
//            con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            con.header("Accept-Encoding", "gzip, deflate");
//            con.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
//            con.header("Cache-Control", "no-cache");
//            con.header("Connection", "keep-alive");
//            con.header("Host", "m.kekenet.com");
//            con.header("User-Agent", "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Mobile Safari/537.36");
//            con.ignoreHttpErrors(true);
//            Connection.Response res = con.method(Connection.Method.GET).execute();
//            Document parse = res.parse();
////            parse.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
//            parse.select("br").append("$");
////            parse.select("p").prepend("\\n\\n");
////            String sm = parse.html().replaceAll("\\\\n", "\n");
////            String clean = Jsoup.clean(sm, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
////            System.out.println(clean);
//            Elements title = parse.select(".f-title");
//            TingLi tl = new TingLi();
//
//            String playerMain = parse.select(".playerMain").select("source").attr("src");
////            System.out.println(playerMain);
//            String s1 = title.text().replace("听力真题及答案", "");
//            tl.setTitle(s1);
//            tl.setVoice(playerMain);
//            tl.setContent(new ArrayList<>());
//            Elements ps = parse.select(".f-y").select("p");
//            for (Element p : ps) {
//                String text = p.text();
//                if (!"".equals(text) && text != null) {
//                    String[] split = text.split("\\$");
//                    List<String> stringList = Arrays.asList(split);
//                    for (String s2 : stringList) {
//                        System.out.println(s2);
//                        tl.getContent().add(s2);
//                    }
//                }
//            }
////            if (!tl.getTitle().equals("") && !tl.getVoice().equals("")) {
////                list.add(tl);
////            }
//            if (!tl.getTitle().equals("") && !tl.getVoice().equals("")) {
//                redisTemplate.opsForHash().put("cet4", tl.getTitle(), tl);
//            }
//        }
//    }
//
//    @Test
//    public void queryCet4() {
//        //String openId = "wzx";
//        //redisTemplate.opsForHash().put("User_" + openId, "isTixing", 1);
//        //redisTemplate.opsForList().leftPush("MATCHQUEUE", "wzx");
//
//        Object o = redisTemplate.opsForList().rightPop("MATCHQUEUE");
//        String m = (String) o;
//        System.out.println(m);
//        //Object tixing = redisTemplate.opsForHash().get("cet4", "2019年6月英语四级:第1套");
//        //TingLi tl = ((JSONObject) tixing).toJavaObject(TingLi.class);
//    }
//}