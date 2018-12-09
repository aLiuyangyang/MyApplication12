package com.example.dell.myapplication12.model;

import com.example.dell.myapplication12.callback.MyCallback;

public interface Model {
    void setRequestData(String path, Class clazz, MyCallback myCallback);
}
