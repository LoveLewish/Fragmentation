package com.lewish.start.mydemo.flow.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewish.start.mydemo.R;

import java.util.ArrayList;

/**
 * Created by sundong on 2016/11/16 14:45.
 */

public class ShopMenuAdapter  extends RecyclerView.Adapter<ShopMenuAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<String> mMenuList;
    private LayoutInflater mInflater;
    private onItemClickListener onItemClickListener;
    private SparseBooleanArray mIsCheckedSparseArray;
    private int mLastCheckedPosition;

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(ShopMenuAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setDatas(ArrayList<String> menuList){
        mMenuList.clear();
        mMenuList.addAll(menuList);
        mIsCheckedSparseArray = new SparseBooleanArray(mMenuList.size());
        notifyDataSetChanged();
    }

    public ShopMenuAdapter(Context context, ArrayList<String> mMenuList) {
        this.mContext = context;
        this.mMenuList = mMenuList;

        mInflater = LayoutInflater.from(context);
        mIsCheckedSparseArray = new SparseBooleanArray(mMenuList.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyitem_shop_menu, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null) {
                    int position = myViewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(mIsCheckedSparseArray.get(position)) {//该项被选中
            holder.view.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.textView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }else{
            holder.view.setVisibility(View.INVISIBLE);
            holder.itemView.setBackgroundResource(R.color.bg_app);
            holder.textView.setTextColor(Color.BLACK);
        }
        holder.textView.setText(mMenuList.get(position));
    }

    public void setItemChecked(int position){
        if(mLastCheckedPosition >-1) {
            mIsCheckedSparseArray.put(mLastCheckedPosition,false);
            notifyItemChanged(mLastCheckedPosition);
        }
        mIsCheckedSparseArray.put(position,true);
        notifyItemChanged(position);
        mLastCheckedPosition = position;
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView textView;
        MyViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
