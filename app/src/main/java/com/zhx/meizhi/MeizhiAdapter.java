package com.zhx.meizhi;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MeizhiAdapter extends RecyclerView.Adapter<MeizhiAdapter.MeizhiViewHolder>{

    private List<String>dataList;
    private Context mContext;
    private List<Integer> mHeights;
    private int SCREE_WIDTH = 0;

    private OnItemClickLitener mOnItemClickLitener;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }



    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public MeizhiAdapter(Context context , List<String>dataList){
        this.mContext = context;
        this.dataList = dataList;
        mHeights = new ArrayList<>() ;
        SCREE_WIDTH = mContext.getResources().getDisplayMetrics().widthPixels;
    }


    @Override
    public MeizhiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout, null);
        MeizhiViewHolder holder = new MeizhiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MeizhiViewHolder holder, int position) {
        //Log.d("zhanga222", String.valueOf(position));
        //holder.tv.setText(dataList.get(position));
        if (mHeights.size() <= position) {
            mHeights.add((position % 2)*100 + 500);
        }
        ViewGroup.LayoutParams lp = holder.cardView.getLayoutParams();
        lp.height = mHeights.get(position);

        holder.cardView.setLayoutParams(lp);
        //holder.cardView.getLayoutParams().height = new Random().nextInt(150)+400;
        holder.cardView.getLayoutParams().width = SCREE_WIDTH/2;
        Glide.with(mContext)
                .load(dataList.get(position))
                .into(holder.imageView);
        if (mOnItemClickLitener != null) {
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.frameLayout, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class MeizhiViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        CardView cardView;
        FrameLayout frameLayout;
        public MeizhiViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            imageView = (ImageView) itemView.findViewById(R.id.img_meizhi);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.framelayout);
            /*cardView.getLayoutParams().height = new Random().nextInt(150)+400;
            cardView.getLayoutParams().width = SCREE_WIDTH/2;*/
            //tv = (TextView) itemView.findViewById(R.id.tv);

        }
    }


}
