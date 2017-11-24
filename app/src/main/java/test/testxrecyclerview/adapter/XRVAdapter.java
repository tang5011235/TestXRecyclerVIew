package test.testxrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import test.testxrecyclerview.Bean;
import test.testxrecyclerview.R;

/**
 * Created by Administrator on 2017/11/23.
 */

public class XRVAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private List<Bean.ResultsBean> mData;

    public XRVAdapter(List<Bean.ResultsBean> bean) {
        mData = bean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_img, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(mData.get(position).getUrl())
                .into(((MyViewHolder) holder).mIv);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        mClickListener.onClick(view,position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mIv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
        }
    }


    private OnItemClickListener mClickListener;
    public interface OnItemClickListener{
        void onClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        mClickListener = clickListener;
    }

    public List<Bean.ResultsBean> getData(){
        return mData;
    }
}


