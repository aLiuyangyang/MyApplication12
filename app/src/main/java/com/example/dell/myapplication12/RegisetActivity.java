package com.example.dell.myapplication12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.myapplication12.bean.LoginBean;
import com.example.dell.myapplication12.bean.RegBean;
import com.example.dell.myapplication12.presenter.Presenterimpl;
import com.example.dell.myapplication12.view.IView;

public class RegisetActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText edit_name,edit_pass;
    private Button log_but;
    private Presenterimpl presenterimpl;
    private String RegUrl="http://www.zhaoapi.cn/user/reg?mobile=%s&password=%s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiset);
        inif();
    }

    private void inif() {
        edit_name=findViewById(R.id.edit_mname);
        edit_pass=findViewById(R.id.edit_mpass);
        log_but=findViewById(R.id.log_mbut);
       log_but.setOnClickListener(this);
        presenterimpl=new Presenterimpl(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.log_mbut:
                String name = edit_name.getText().toString();
                String pass = edit_pass.getText().toString();
                presenterimpl.setRequestData(String.format(RegUrl,name,pass),RegBean.class);
                break;
        }
    }

    @Override
    public void setData(Object data) {
         RegBean regBean= (RegBean) data;
        if (regBean.getCode().equals("0")){
            Intent intent=new Intent(RegisetActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(RegisetActivity.this,"请重新输入",Toast.LENGTH_SHORT).show();
        }
    }
}
