package com.example.dell.myapplication12.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.dell.myapplication12.callback.MyCallback;
import com.example.dell.myapplication12.util.NetUtil;
import com.google.gson.Gson;

public class Modelimpl implements Model{
    private MyCallback callback;
    @SuppressLint("StaticFieldLeak")
    @Override
    public void setRequestData(String path, final Class clazz, MyCallback myCallback) {
        callback=myCallback;
        new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return setResult(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.setData(o);
            }
        }.execute(path);
    }
    public <T> T setResult(String path,Class clazz){
        String json = NetUtil.setResult(path);
        Gson gson=new Gson();
        T t  = (T) gson.fromJson(json, clazz);
        return t;
    }
}
