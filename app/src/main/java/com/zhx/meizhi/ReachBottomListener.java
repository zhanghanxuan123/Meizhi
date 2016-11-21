package com.zhx.meizhi;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


public abstract class ReachBottomListener extends RecyclerView.OnScrollListener{

    boolean isSlidingToLast = false;
    private int currentPage = 1;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
        // 当不滚动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取最后一个完全显示的ItemPosition
            int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
            int lastVisiblePos = getMaxElem(lastVisiblePositions);
            int totalItemCount = manager.getItemCount();

            // 判断是否滚动到底部
            if (lastVisiblePos == (totalItemCount -1) && isSlidingToLast) {
                //加载更多功能的代码
                onLoadMore(currentPage);
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
        if(dy > 0){
            //大于0表示，正在向下滚动
            isSlidingToLast = true;
        }else{
            //小于等于0 表示停止或向上滚动
            isSlidingToLast = false;
        }

    }

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i]>maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }

    public abstract void onLoadMore(int currentPage);
}
