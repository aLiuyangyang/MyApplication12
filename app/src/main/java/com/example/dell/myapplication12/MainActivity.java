package com.example.dell.myapplication12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.myapplication12.bean.LoginBean;
import com.example.dell.myapplication12.presenter.Presenterimpl;
import com.example.dell.myapplication12.view.IView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText edit_name,edit_pass;
    private Button log_but,reg_but,deng_but;
    private CheckBox check_remenber,check_auto;
    private Presenterimpl presenterimpl;
    private String LoginUrl="http://www.zhaoapi.cn/user/login?mobile=%s&password=%s";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        init();
        sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean j_check = sharedPreferences.getBoolean("j_check", false);
        if (j_check){
            String username = sharedPreferences.getString("name", null);
            String password = sharedPreferences.getString("pass", null);
            edit_name.setText(username);
            edit_pass.setText(password);
        }
        boolean z_check = sharedPreferences.getBoolean("z_check", false);
        if (z_check){
            String name = edit_name.getText().toString();
            String pwd = edit_pass.getText().toString();
            check_remenber.setChecked(true);
            presenterimpl.setRequestData(String.format(LoginUrl,name,pwd),LoginBean.class);
        }
        check_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check_auto.isChecked()){
                    check_remenber.setChecked(true);
                }else{
                    editor.clear();
                    editor.commit();

                }
            }
        });
    }

    private void init() {
        edit_name=findViewById(R.id.edit_name);
        edit_pass=findViewById(R.id.edit_pass);
        log_but=findViewById(R.id.log_but);
        reg_but=findViewById(R.id.reg_but);
        deng_but=findViewById(R.id.deng_but);
        deng_but.setOnClickListener(this);
        log_but.setOnClickListener(this);
        reg_but.setOnClickListener(this);
        check_remenber=findViewById(R.id.check_remenber);
        check_auto=findViewById(R.id.check_auto);
        presenterimpl=new Presenterimpl(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.deng_but:
                UMShareAPI umShareAPI=UMShareAPI.get(MainActivity.this);
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("TAG", "onStart");
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Log.i("TAG", map+"");
                        String name = map.get("screen_name");
                        Intent intent=new Intent(MainActivity.this,SuccessActivity.class);
                        intent.putExtra("name",name);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("TAG", "onError");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Log.i("TAG", "onCancel");
                    }
                });
                break;
            case R.id.log_but:
                String name = edit_name.getText().toString();
                String pass = edit_pass.getText().toString();
                if (check_remenber.isChecked()){
                    editor.putString("name",name);
                    editor.putString("pass",pass);
                    editor.putBoolean("j_check",true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
                if (check_remenber.isChecked()){
                    editor.putBoolean("z_check",true);
                    editor.commit();
                }
                presenterimpl.setRequestData(String.format(LoginUrl,name,pass),LoginBean.class);
                break;
            case R.id.reg_but:
                Intent intent=new Intent(MainActivity.this,RegisetActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    @Override
    public void setData(Object data) {
           LoginBean loginBean= (LoginBean) data;
           if (loginBean.getCode().equals("0")){
               Intent intent=new Intent(MainActivity.this,SuccessActivity.class);
               intent.putExtra("name",edit_name.getText().toString());
               startActivity(intent);
           }else{
               Toast.makeText(MainActivity.this,"请重新输入",Toast.LENGTH_SHORT).show();
           }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenterimpl!=null){
            presenterimpl.des();
        }
    }

}
