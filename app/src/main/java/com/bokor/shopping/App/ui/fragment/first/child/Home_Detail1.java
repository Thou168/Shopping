package com.bokor.shopping.App.ui.fragment.first.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bokor.shopping.App.Main2Activity;
import com.bokor.shopping.App.entity.Article;
import com.bokor.shopping.App.ui.fragment.fragment_swipe_back.BaseSwipeBackFragment;
import com.bokor.shopping.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * Created on 21/09/19.
 */
public class Home_Detail1 extends BaseSwipeBackFragment {
    private static final String ARG_ITEM = "arg_item";

    private Article mArticle;

    private Toolbar mToolbar;
    private ImageView mImgDetail;
    private TextView mTvTitle;
    private FloatingActionButton mFab;
    AdView mAdView;

    public static Home_Detail1 newInstance(Article article) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, article);
        Home_Detail1 fragment = new Home_Detail1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Main2Activity)this.getActivity()).setGoneBottom();
        mArticle = getArguments().getParcelable(ARG_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_first_detail, container, false);
        initView(view);

//        MobileAds.initialize(getContext(), "ca-app-pub-9458278303026353~4105611830");
//        MobileAds.initialize(getContext(),"ca-app-pub-9458278303026353/6540203480");
//        AdView adView = new AdView(getContext());
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-9458278303026353/6540203480");
        MobileAds.initialize(_mActivity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return attachToSwipeBack(view);
    }
    private void initView(View view) {
        mToolbar =  view.findViewById(R.id.toolbar);
        mImgDetail =  view.findViewById(R.id.img_detail);
        mTvTitle =  view.findViewById(R.id.tv_content);
//        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

        mToolbar.setTitle("");
        _initToolbar(mToolbar);
        mImgDetail.setImageResource(mArticle.getImgRes());
        mTvTitle.setText(mArticle.getTitle());

//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                start(CycleFragment.newInstance(1));
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        ((Main2Activity)this.getActivity()).setVisiBottom();
        super.onDestroyView();
    }
}
