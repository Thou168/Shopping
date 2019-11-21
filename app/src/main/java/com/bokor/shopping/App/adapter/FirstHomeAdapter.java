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
import com.bokor.shopping.App.entity.Item;
import com.bokor.shopping.App.listener.OnItemClickListener;
import com.bokor.shopping.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 20/09/2019.
 */
public class FirstHomeAdapter extends RecyclerView.Adapter<FirstHomeAdapter.VH> {
    private List<Item> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    private OnItemClickListener mClickListener;

    public FirstHomeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
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
        Item item = mItems.get(position);

        /* Set each image view with a different Transition name to prevent multiple identical names in one view, causing confusion when transforming
         Fragment supports multiple View transformations. When using adapters, you need to distinguish
         */
        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");
        ViewCompat.setTransitionName(holder.tvTitle, String.valueOf(position) + "_tv");

//        holder.img.setImageResource(item.getFront_image_path());
        Glide.with(mContext).load(item.getFront_image_path()).apply(new RequestOptions().placeholder(R.drawable.bg_fifth)).into(holder.img);
        holder.tvTitle.setText(item.getPost_sub_title());
    }

    public void setDatas(List<Item> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Item getItem(int position) {
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
