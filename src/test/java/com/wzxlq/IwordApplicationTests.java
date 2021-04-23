//package com.wzxlq;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.wzxlq.dao.WordDao;
//import com.wzxlq.entity.Nearbyperson;
//import com.wzxlq.entity.User;
//import com.wzxlq.entity.Word;
//import com.wzxlq.entity.wxUser;
//import com.wzxlq.service.UserService;
//import com.wzxlq.utils.FileOperation;
//import com.wzxlq.utils.Trie;
//import com.wzxlq.utils.sentUtil;
//import com.wzxlq.vo.RankPersonVO;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.springframework.data.geo.*;
//import org.springframework.data.redis.connection.RedisGeoCommands;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//@SpringBootTest
//class IwordApplicationTests {
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
//    @Resource
//    private WordDao wordDao;
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Autowired
//    private UserService userService;
//
//    @Test
//    void contextLoads() {
//    }
//
//    //测试获取索引,判断其是否存在
//    @Test
//    void testExistIndex() throws IOException {
//        GetIndexRequest request = new GetIndexRequest("iword");
//        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
//        System.out.println(exists);
//    }
//
//    //加入所有单词到es中
//    @Test
//    public void parseContent() throws Exception {
//        List<Word> wordList = wordDao.queryAll();
//        BulkRequest bulkRequest = new BulkRequest();
//        bulkRequest.timeout("2m");
//        Gson gson = new Gson();
//        for (int i = 0; i < wordList.size(); i++) {
//            bulkRequest.add(new IndexRequest("iword")
//                    .source(gson.toJson(wordList.get(i), new TypeToken<Word>() {
//                    }.getType()), XContentType.JSON));
//        }
//        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//        System.out.println(!bulk.hasFailures());
//    }
//
//    //查询,重点！
//    @Test
//    void testSearch() throws IOException {
//        SearchRequest searchRequest = new SearchRequest("iword");
//        //构建搜索条件
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        //查询条件，我们可以使用queryBuilders工具来实现
//        //QueryBuilders.termQuery 精确匹配
////        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("englishWord", "a");
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery("englishWord", "a" + "*"));
////        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
//        sourceBuilder.query(boolQueryBuilder);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        searchRequest.source(sourceBuilder);
//        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        ArrayList<Map<String, Object>> list = new ArrayList<>();
//        for (SearchHit documentFields : search.getHits().getHits()) {
//            list.add(documentFields.getSourceAsMap());
//        }
//        System.out.println(list);
//    }
//
//    //更新词频
//    @Test
//    void ciping() {
//        List<Word> wordList = wordDao.queryAll();
//        ArrayList<String> words = new ArrayList<>();
//        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
//            long startTime = System.nanoTime();
//            Trie trie = new Trie();
//            System.out.println(words);
//            for (String word : words)
//                trie.add(word);
//            for (Word word : wordList) {
//                String englishWord = word.getEnglishWord();
//                word.setCollect(trie.count(englishWord));
//                wordDao.update(word);
//            }
//            long endTime = System.nanoTime();
//            double time = (endTime - startTime) / 1000000000.0;
//            System.out.println("Total different words: " + trie.getSize());
//            System.out.println("Trie: " + time + " s");
//        }
//    }
//
//    @Test
//    public void queryRank() {
//        List<User> userList = userService.queryAll();
//        ArrayList<RankPersonVO> list = new ArrayList<>();
//        for (User user : userList) {
//            Long size = redisTemplate.opsForList().size(user.getOpenId());
////            list.add(new RankPersonVO(user.getNickName(),user.getHeadImgUrl(),1280-size));
//        }
//        Collections.sort(list, (a, b) -> (int) (b.getScore() - a.getScore()));
//        System.out.println(list);
//    }
//
//    @Test
//    public void testSet() {
////        redisTemplate.opsForHash().put("User_o47m6s2AnLoH76RXa6hDezXMre_k","isTixing",1);
////        redisTemplate.opsForHash().put("User_wzx","headImage","http:/kGlNdY12POO95bXoIU9SibcrffqFjg/0");
////        Integer wordCount = (Integer)redisTemplate.opsForHash().get("User_wzx", "wordCount");
////        String headImage = (String)redisTemplate.opsForHash().get("User_wzx", "headImage");
//        //geoadd : (纬度、经度、名称)三元组
//        String json = sentUtil.get("http://api.map.baidu.com/location/ip?ak=ryFnGbYBMuoymNnla3TSxxpE7rCrPNfT&coor=bd09ll");
//        Gson gson = new Gson();
//        Nearbyperson nearbyperson = gson.fromJson(json, Nearbyperson.class);
//        System.out.println(nearbyperson.getContent().getPoint().getX());
//        System.out.println(nearbyperson.getContent().getPoint().getY());
//        Distance distance = new Distance(100, Metrics.KILOMETERS);
//        //RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
//        //GeoResults<RedisGeoCommands.GeoLocation<Object>>  results = redisTemplate.opsForGeo().radius("location", "n05", distance,args);
//        //List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> list = results.getContent();
//        //System.out.println(list);
//        //Distance distance2 = redisTemplate.opsForGeo().distance("home", "张三", "李四", RedisGeoCommands.DistanceUnit.KILOMETERS);
//        //http://api.map.baidu.com/location/ip?ak=ryFnGbYBMuoymNnla3TSxxpE7rCrPNfT&coor=bd09ll
//
//        //redisTemplate.opsForGeo().add("location",
//        //        new Point(Double.parseDouble(nearbyperson.getContent().getPoint().getX()), Double.parseDouble(nearbyperson.getContent().getPoint().getY())), "n04");
////redisTemplate.opsForGeo().add("home", new Point(116.48105, 39.996794), "张三");
//        //redisTemplate.opsForGeo().add("home", new Point(116.514203, 39.905409), "李四");
//        //redisTemplate.opsForGeo().add("home", new Point(116.489033, 40.007669), "王五");
//
////计算张三李四之间的距离 其中，距离单位可以是m、km、ml、ft，分别代表米、千米、英里和尺
////        Distance distance = redisTemplate.opsForGeo().distance("home", "张三", "李四");
////        Distance distance2 = redisTemplate.opsForGeo().distance("home", "张三", "李四", RedisGeoCommands.DistanceUnit.KILOMETERS);
////        System.out.println(distance);
////        System.out.println(distance.getValue());
////        System.out.println(distance2.getValue());
//
//    }
//
//    @Test
//    public void tesxxx() {
//         List<String> numList = new ArrayList<>();
//         numList.add("o47m6s2AnLoH76RXa6hDezXMre_k");
//         //numList.add("wzx");
//        String[] num = new String[2] ;
//        num[0]="o47m6s2AnLoH76RXa6hDezXMre_k";
//        num[1] = "o47m6s63RNsykUBlEjMQsu6G9YPY";
//        List<Point> points = redisTemplate.opsForGeo().position("location", num);
//        System.out.println(points);
//    }
//}
