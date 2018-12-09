package com.example.dell.myapplication12.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.myapplication12.MyScanActivity;
import com.example.dell.myapplication12.R;
import com.example.dell.myapplication12.SuccessActivity;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class FragmentMy extends Fragment{
    private ImageView imageView;
    private Button search_button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my,container,false);
        imageView=view.findViewById(R.id.imageView);
        search_button=view.findViewById(R.id.search_button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inif();
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MyScanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inif() {
        String name = ((SuccessActivity) getActivity()).setName();
        QRText qrText=new QRText(getActivity(),imageView,name);
        qrText.execute(name);
    }

    class QRText extends AsyncTask<String,Void,Bitmap>{
         private WeakReference<ImageView> mimageView ;
        private WeakReference<Context> mcontext;
       public QRText(Context context,ImageView imageView,String name){
           mcontext=new WeakReference<>(context);
           mimageView=new WeakReference<>(imageView);
       }
         @Override
         protected Bitmap doInBackground(String... strings) {
             String s = strings[0];
             if (TextUtils.isEmpty(s)){
                 return null;
             }
             int size = mcontext.get().getResources().getDimensionPixelOffset(R.dimen.ss);
               return QRCodeEncoder.syncEncodeQRCode(s,size);
         }

         @Override
         protected void onPostExecute(Bitmap bitmap) {
             super.onPostExecute(bitmap);
             if (bitmap!=null){
                 mimageView.get().setImageBitmap(bitmap);
             }else{
                 Toast.makeText(mcontext.get(),"失败",Toast.LENGTH_SHORT).show();
             }
         }
     }
}
