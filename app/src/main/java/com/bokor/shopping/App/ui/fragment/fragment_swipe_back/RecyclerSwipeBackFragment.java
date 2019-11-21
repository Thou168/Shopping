package com.bokor.shopping.App.ui.fragment.fragment_swipe_back;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bokor.shopping.App.adapter.FirstHomeAdapter;
import com.bokor.shopping.App.entity.Article;
import com.bokor.shopping.App.entity.Item;
import com.bokor.shopping.App.listener.OnItemClickListener;
import com.bokor.shopping.App.ui.fragment.first.child.Home_Detail1;
import com.bokor.shopping.R;

import java.util.ArrayList;
import java.util.List;


public class RecyclerSwipeBackFragment extends BaseSwipeBackFragment {
    private static final String ARG_FROM = "arg_from";

    private Toolbar mToolbar;

    private RecyclerView mRecy;
//    private PagerAdapter mAdapter;
    private FirstHomeAdapter mAdapter;
    private String title;

    public static RecyclerSwipeBackFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(ARG_FROM, title);
        RecyclerSwipeBackFragment fragment = new RecyclerSwipeBackFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private String[] mTitles = new String[]{
            "Use imagery to express a distinctive voice and exemplify creative excellence.",
            "An image that tells a story is infinitely more interesting and informative.",
            "The most powerful iconic images consist of a few meaningful elements, with minimal distractions.",
            "Properly contextualized concepts convey your message and brand more effectively.",
            "Have an iconic point of focus in your imagery. Focus ranges from a single entity to an overarching composition."
    };

    private int[] mImgRes = new int[]{
            R.drawable.bg_first, R.drawable.bg_second, R.drawable.bg_third, R.drawable.bg_fourth, R.drawable.bg_fifth
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(ARG_FROM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_back_recy, container, false);

        initView(view);

        return attachToSwipeBack(view);
//        return view;
    }

    private void initView(View view) {
        mRecy = view.findViewById(R.id.recy);

        mToolbar =  view.findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        _initToolbar(mToolbar);

        mAdapter = new FirstHomeAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {

                start(Home_Detail1.newInstance(mAdapter.getItem(position)));
//                Home_Detail1 fragment = Home_Detail1.newInstance(mAdapter.getItem(position));
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//                    setExitTransition(new Fade());
//                    fragment.setEnterTransition(new Fade());
//                    fragment.setSharedElementReturnTransition(new DetailTransition());
//                    fragment.setSharedElementEnterTransition(new DetailTransition());
//
//                    extraTransaction()
//                            .addSharedElement(((FirstHomeAdapter.VH) vh).img, getString(R.string.image_transition))
//                            .addSharedElement(((FirstHomeAdapter.VH) vh).tvTitle, "tv")
//                            .start(fragment);
//                } else {
//                start(fragment);
//                }
            }
        });

//        mAdapter = new PagerAdapter(_mActivity);
//        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
//        mRecy.setLayoutManager(manager);
//        mRecy.setAdapter(mAdapter);
//
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View view) {
//                start(FirstSwipeBackFragment.newInstance());
//            }
//        });

//        // Init Datas
//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            String item;
//            item = getString(R.string.favorite) + " " + i;
//            items.add(item);
//        }
        // Init Datas
//        List<Item> articleList = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            int index = i % 5;
//            Article article = new Article(mTitles[index], mImgRes[index]);
//            articleList.add(article);
//        }
//        mAdapter.setDatas(articleList);
//        mAdapter.setDatas(items);
    }
}
