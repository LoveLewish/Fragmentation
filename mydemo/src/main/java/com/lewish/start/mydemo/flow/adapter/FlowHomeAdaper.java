package com.lewish.start.mydemo.flow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewish.start.mydemo.R;
import com.lewish.start.mydemo.flow.entity.Article;

import java.util.ArrayList;
import java.util.List;

import static com.lewish.start.mydemo.R.id.tv_content;

/**
 * Created by Administrator on 2016/11/4 16:33.
 */

public class FlowHomeAdaper extends RecyclerView.Adapter<FlowHomeAdaper.MyViewHolder> {
    private List<Article> mArticleList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private onItemClickListener onItemClickListener;

    public FlowHomeAdaper(Context mContext, List<Article> mArticleList) {
        this.mContext = mContext;
        this.mArticleList = mArticleList;
        mInflater = LayoutInflater.from(mContext);
    }

    public FlowHomeAdaper(Context mContext) {
        this(mContext, null);
    }

    public void setmArticleList(List<Article> mArticleList) {
        this.mArticleList = mArticleList;
    }

    /**
     * 返回一个自定义的ViewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyitem_flow_home, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myViewHolder.getAdapterPosition();
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
        return myViewHolder;
    }

    /**
     * 填充onCreateViewHolder方法返回的holder中的控件
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(mArticleList.get(position).getTitle());
        holder.tvContent.setText(mArticleList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mArticleList == null ? 0 : mArticleList.size();
    }

    /**
     * 自定义ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvContent;

        MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(tv_content);
        }
    }

    public interface onItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(FlowHomeAdaper.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
