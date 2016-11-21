package com.zhx.meizhi;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public final MeizhiService mService = MeizhiHttp.getServiceInstance();
    private MeizhiAdapter adapter;
    List<String>urlList;
    StaggeredGridLayoutManager layoutmanager;
    RecyclerView recyclerview;
    int page = 1;
    List<String>newurlList;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        init();
        getMeizhi(10,1);

    }

    private void init() {
        layoutmanager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        urlList = new ArrayList<>();
        newurlList = new ArrayList<>();
        adapter = new MeizhiAdapter(this, urlList);
        recyclerview.setAdapter(adapter);
        recyclerview.addOnScrollListener(new ReachBottomListener() {
            @Override
            public void onLoadMore(int currentPage) {
                page++;
                getMeizhi(10,page);
            }
        });
        adapter.setOnItemClickLitener(new MeizhiAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                newurlList.clear();
                for (int i=0;i<6;i++){
                    newurlList.add(urlList.get(position+i));
                    Log.d("zhangnew",newurlList.get(i));
                }
                Log.d("zhangsize", String.valueOf(newurlList.size()));
                CheckPicView checkPicView = new CheckPicView(MainActivity.this,context);
                checkPicView.init(newurlList);
                checkPicView.Show();
                //checkPicView.Show();
            }
        });
    }

    void getMeizhi(int count ,int page){

        Observable<MeizhiEntity>observable = mService.getMeizhiData(count,page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeizhiEntity>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("zhang2", e.toString());
                    }

                    @Override
                    public void onNext(MeizhiEntity meizhiEntity) {
                        for(int i = 0;i<meizhiEntity.getResults().size();i++)
                        {
                            urlList.add(meizhiEntity.getResults().get(i).getUrl());
                            adapter.notifyDataSetChanged();

                        }
                    }
                });
    }

}
