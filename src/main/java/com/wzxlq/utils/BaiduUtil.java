package com.wzxlq.utils;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Package: com.wzxlq.util
 * @ClassName: BaiduUtil
 * @Author: 王照轩
 * @CreateTime: 2021/4/21 10:21
 * @Description:
 */
public class BaiduUtil {

    // 设置APPID/AK/SK，通过百度自己申请
    public static final String APP_ID = "24012953";
    public static final String API_KEY = "nCqGZAAz3KBkvKzGCOefN2oD";
    public static final String SECRET_KEY = "iUdpqDxIqqNBy6TX5dVDhhe6FKWgxlG9";



    /**
     * 转化
     *
     * @param targetPath
     * @return
     */
    public static Map<String, String> getAipSpeech(String targetPath) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        JSONObject res = client.asr(targetPath, "wav", 16000, null);
        Map<String, String> result = new HashMap<String, String>();
        System.out.println(res);
        if ("success.".equals(res.get("err_msg"))) {
            Iterator<String> iterator = res.keys();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = iterator.next();
                if ("result".equals(key)) {
                    Object re = res.get(key);
                    if ("JSONArray".equals(re.getClass().getSimpleName())) {
                        JSONArray s = (JSONArray) re;
                        value = s.getString(0);
                    }
                } else {
                    value = res.get(key) + "";
                }
                result.put(key, value);
            }
        } else {
            result.put("result", "语音无法识别,转化失败");
        }
        System.out.println(result);
        return result;
    }

}
