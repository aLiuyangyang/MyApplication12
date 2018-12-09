package com.example.dell.myapplication12.presenter;

import com.example.dell.myapplication12.callback.MyCallback;
import com.example.dell.myapplication12.model.Model;
import com.example.dell.myapplication12.model.Modelimpl;
import com.example.dell.myapplication12.view.IView;

public class Presenterimpl implements Presenter{
    private IView iView;
    private Model model;

    public Presenterimpl(IView iView) {
        this.iView = iView;
        model=new Modelimpl();
    }

    @Override
    public void setRequestData(String path, Class clazz) {
       model.setRequestData(path, clazz, new MyCallback() {
           @Override
           public void setData(Object data) {
               iView.setData(data);
           }
       });
    }
    public void des(){
        if (iView!=null){
            iView=null;
        }
        if (model!=null){
            model=null;
        }
    }
}
