package com.wzxlq.utils;
 
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 连接百度的声音识别接口，处理pcm音频文件，将其转换为一段话
 * @author Administrator
 *
 */
public class Sample {

   private static final String serverURL = "http://vop.baidu.com/server_api";
   private static String token = "";
   //private static final String testFileName = "F:\\语音文件\\a1.pcm";
   //put your own params here
   private static final String apiKey = "nCqGZAAz3KBkvKzGCOefN2oD";//这里的apiKey就是前面申请在应用卡片中的apiKey
   private static final String secretKey = "iUdpqDxIqqNBy6TX5dVDhhe6FKWgxlG9";//这里的secretKey就是前面申请在应用卡片中的secretKey
   private static final String cuid = "2C-4D-54-29-D9-5B";//cuid是设备的唯一标示，因为我用的是PC，所以这里用的是网卡Mac地址

   public static void main(String[] args) throws Exception {
       getToken();
      // method1("F:\\语音文件\\a1.pcm");
       String mp3path = "D:\\jdzzp-hwrxx.mp3";
       String pcmpath = "D:\\jdzzp-hwrxx.pcm";
       MP3ConvertPCM.mp3Convertpcm(mp3path, pcmpath);
       method2(pcmpath);
   }

   public static String beginSample(String testFileName) throws Exception{
       //testFileName="F:\\语音文件\\16k.pcm";
        getToken();
        // method1("F:\\语音文件\\a1.pcm");
        String word=method2(testFileName);
        word=word.substring(word.indexOf("[")+2, word.indexOf("]")-1);
        word=word.replaceAll(",", "/");
        System.out.println(word);
         return word;
   }

   private static void getToken() throws Exception {
       String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" +
           "&client_id=" + apiKey + "&client_secret=" + secretKey;
       HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
       token = new JSONObject(printResponse(conn)).getString("access_token");
   }

   private static void method1(String testFileName) throws Exception {
       File pcmFile = new File(testFileName);
       HttpURLConnection conn = (HttpURLConnection) new URL(serverURL).openConnection();

       // construct params
       JSONObject params = new JSONObject();
       params.put("format", "pcm");
       params.put("rate", 8000);
       params.put("channel", "1");
       params.put("token", token);
       params.put("cuid", cuid);
       params.put("len", pcmFile.length());
       params.put("speech", DatatypeConverter.printBase64Binary(loadFile(pcmFile)));

       // add request header
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

       conn.setDoInput(true);
       conn.setDoOutput(true);

       // send request
       DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
       wr.writeBytes(params.toString());
       wr.flush();
       wr.close();

       printResponse(conn);
   }

   private static String method2(String testFileName) throws Exception {
       File pcmFile = new File(testFileName);
       HttpURLConnection conn = (HttpURLConnection) new URL(serverURL
               + "?cuid=" + cuid + "&token=" + token).openConnection();

       // add request header
       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-Type", "audio/pcm; rate=8000");

       conn.setDoInput(true);
       conn.setDoOutput(true);

       // send request
       DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
       wr.write(loadFile(pcmFile));
       wr.flush();
       wr.close();

      String word=printResponse(conn);
      return word;
   }

   private static String printResponse(HttpURLConnection conn) throws Exception {
       if (conn.getResponseCode() != 200) {
           // request error
           return "";
       }
       InputStream is = conn.getInputStream();
       BufferedReader rd = new BufferedReader(new InputStreamReader(is,"utf-8"));
       String line;
       StringBuffer response = new StringBuffer();
       while ((line = rd.readLine()) != null) {
           response.append(line);
           response.append('\r');
       }
       rd.close();
       System.out.println(new JSONObject(response.toString()).toString(4));
       return response.toString();
   }

   private static byte[] loadFile(File file) throws IOException {
       InputStream is = new FileInputStream(file);

       long length = file.length();
       byte[] bytes = new byte[(int) length];

       int offset = 0;
       int numRead = 0;
       while (offset < bytes.length
               && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
           offset += numRead;
       }

       if (offset < bytes.length) {
           is.close();
           throw new IOException("Could not completely read file " + file.getName());
       }

       is.close();
       return bytes;
   }
}