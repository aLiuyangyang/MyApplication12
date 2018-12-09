package com.example.dell.myapplication12.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.myapplication12.R;
import com.example.dell.myapplication12.bean.JavaBean;
import com.example.dell.myapplication12.presenter.Presenterimpl;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class FragmentShow extends Fragment implements IView{
    private FlyBanner carousel_fly;
    private PullToRefreshListView pull;
    private MyPullBase adapter;
    private Presenterimpl presenterimpl;
    private int page;
    private String  PullUrl="http://www.xieast.com/api/news/news.php?page=%d";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.show,container,false);
        carousel_fly=view.findViewById(R.id.carousel_fly);
        pull=view.findViewById(R.id.pull);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carousel();
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        adapter=new MyPullBase(getActivity());
        pull.setAdapter(adapter);
        presenterimpl=new Presenterimpl(this);
        page=1;
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
              page=1;
              load();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
       load();
            }
        });
        load();
    }

    private void load() {
        presenterimpl.setRequestData(String.format(PullUrl,page),JavaBean.class);
    }

    private void carousel() {
        List<Integer> list=new ArrayList<>();
        list.add(R.drawable.e);
        list.add(R.drawable.a);
        list.add(R.drawable.q);
        list.add(R.drawable.w);
        carousel_fly.setImages(list);
    }

    @Override
    public void setData(Object data) {
         JavaBean javaBean= (JavaBean) data;
           if (page==1){
               adapter.setList(((JavaBean) data).getData());
           }else{
               adapter.addList(((JavaBean) data).getData());
           }
           page++;
           pull.onRefreshComplete();
    }
}
