package com.bokor.shopping.App.ui.fragment.second.child.childpager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bokor.shopping.App.Main2Activity;
import com.bokor.shopping.App.adapter.HomeAdapter;
import com.bokor.shopping.App.entity.Article;
import com.bokor.shopping.App.event.TabSelectedEvent;
import com.bokor.shopping.App.listener.OnItemClickListener;
import com.bokor.shopping.App.ui.fragment.second.child.DetailFragment;
import com.bokor.shopping.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import me.bokor.eventbusactivityscope.EventBusActivityScope;
import me.bokor.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class FirstPagerFragment extends SupportFragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecy;
    private SwipeRefreshLayout mRefreshLayout;
    private HomeAdapter mAdapter;
    private boolean mAtTop = true;
    private int mScrollTotal;
    private String[] mTitles;
    private String[] mContents;

    private InterstitialAd mInterstitialAd;

    public static FirstPagerFragment newInstance() {

        Bundle args = new Bundle();

        FirstPagerFragment fragment = new FirstPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_second_pager_first, container, false);
        EventBusActivityScope.getDefault(_mActivity).register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecy =  view.findViewById(R.id.recy);
        mRefreshLayout =  view.findViewById(R.id.refresh_layout);

        mTitles = getResources().getStringArray(R.array.array_title);
        mContents = getResources().getStringArray(R.array.array_content);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mInterstitialAd = new InterstitialAd(_mActivity);
        mInterstitialAd.setAdUnitId("ca-app-pub-2123636611592840/3351022654");
//        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mAdapter = new HomeAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                // The DetailFragment here is in the flow package.
                // Here is the parent Fragment startup, pay attention to the stack level
                ((SupportFragment) getParentFragment()).start(DetailFragment.newInstance(mAdapter.getItem(position).getTitle()));
                mInterstitialAd.show();
            }
        });

        // Init Datas
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int index = (int) (Math.random() * 3);
            Article article = new Article(mTitles[index], mContents[index]);
            articleList.add(article);
        }
        mAdapter.setDatas(articleList);

        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                Log.d("12121212DY", String.valueOf(mScrollTotal));
                if (mScrollTotal <= 0) {
                    mAtTop = true;
                } else {
                    mAtTop = false;
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    private void scrollToTop() {
        mRecy.smoothScrollToPosition(0);
    }

    /**
     * Select tab event
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != Main2Activity.SECOND) return;

        if (mAtTop) {
            mRefreshLayout.setRefreshing(true);
            onRefresh();
        } else {
            scrollToTop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusActivityScope.getDefault(_mActivity).unregister(this);
    }

}
