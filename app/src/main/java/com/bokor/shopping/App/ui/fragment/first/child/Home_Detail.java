package com.bokor.shopping.App.ui.fragment.first.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bokor.shopping.App.base.BaseBackFragment;
import com.bokor.shopping.App.entity.Article;
import com.bokor.shopping.App.ui.fragment.CycleFragment;
import com.bokor.shopping.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * Created on 21/09/19.
 */
public class Home_Detail extends BaseBackFragment {
    private static final String ARG_ITEM = "arg_item";

    private Article mArticle;

    private Toolbar mToolbar;
    private ImageView mImgDetail;
    private TextView mTvTitle;
    private FloatingActionButton mFab;

    public static Home_Detail newInstance(Article article) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, article);
        Home_Detail fragment = new Home_Detail();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticle = getArguments().getParcelable(ARG_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.zhihu_fragment_first_detail, container, false);
            initView(view);
            return view;
    }

    private void initView(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        mImgDetail = view.findViewById(R.id.img_detail);
        mTvTitle = view.findViewById(R.id.tv_content);
        mFab = view.findViewById(R.id.fab);

        mToolbar.setTitle("");
        initToolbarNav(mToolbar);
        mImgDetail.setImageResource(mArticle.getImgRes());
        mTvTitle.setText(mArticle.getTitle());

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(1));
            }
        });
    }
}
