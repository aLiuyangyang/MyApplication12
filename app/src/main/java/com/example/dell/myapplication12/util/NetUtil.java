package com.example.dell.myapplication12.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {

    public static String setResult(String path){
        String result="";
        try {
            URL url=new URL(path);
            HttpURLConnection http= (HttpURLConnection) url.openConnection();
            int responseCode = http.getResponseCode();
            if (responseCode==200){
                result=stringTo(http.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return  result;
    }

    private static String stringTo(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader br=new BufferedReader(inputStreamReader);
        StringBuilder builder=new StringBuilder();
        for (String t=br.readLine();t!=null;t=br.readLine()){
            builder.append(t);
        }
        return builder.toString();
    }
}
