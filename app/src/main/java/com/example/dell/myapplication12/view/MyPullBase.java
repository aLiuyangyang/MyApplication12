package com.example.dell.myapplication12.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.myapplication12.R;
import com.example.dell.myapplication12.bean.JavaBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

class MyPullBase extends BaseAdapter {
    private List<JavaBean.DataBean> mlist;
    private Context context;

    public MyPullBase(Context context) {
        this.context = context;
        mlist=new ArrayList<>();
    }

    public void setList(List<JavaBean.DataBean> list) {
        mlist.clear();
        mlist.addAll(list);
        notifyDataSetChanged();
    }
    public void addList(List<JavaBean.DataBean> list) {
        mlist.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public JavaBean.DataBean getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            if (getItemViewType(position)==0){
                convertView=View.inflate(context, R.layout.type1,null);
            }else{
                convertView=View.inflate(context,R.layout.type2,null);
            }
            holder=new ViewHolder();
            holder.imageView1=convertView.findViewById(R.id.image1);
            holder.imageView2=convertView.findViewById(R.id.image2);
            holder.imageView3=convertView.findViewById(R.id.image3);
            holder.text=convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.text.setText(getItem(position).getTitle());
        ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s(),holder.imageView1);
        if (holder.imageView2!=null&&holder.imageView3!=null){
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s02(),holder.imageView2);
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s03(),holder.imageView3);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView imageView1,imageView2,imageView3;
        TextView text;
    }
}
