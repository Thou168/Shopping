package com.bokor.shopping.App.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bokor.shopping.App.entity.Article;
import com.bokor.shopping.App.listener.OnItemClickListener;
import com.bokor.shopping.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 20/09/2019.
 */
public class FirstHomeAdapter extends RecyclerView.Adapter<FirstHomeAdapter.VH> {
    private List<Article> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;

    public FirstHomeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_zhihu_home_first, parent, false);
        final VH holder = new VH(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Article item = mItems.get(position);

        /* Set each image view with a different Transition name to prevent multiple identical names in one view, causing confusion when transforming
         Fragment supports multiple View transformations. When using adapters, you need to distinguish
         */
        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");
        ViewCompat.setTransitionName(holder.tvTitle, String.valueOf(position) + "_tv");

        holder.img.setImageResource(item.getImgRes());
        holder.tvTitle.setText(item.getTitle());
    }

    public void setDatas(List<Article> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Article getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView img;

        public VH(View itemView) {
            super(itemView);
            tvTitle =  itemView.findViewById(R.id.tv_title);
            img =  itemView.findViewById(R.id.img);
        }
    }
}
