package com.bokor.shopping.UI.Fragment.first;

import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.bokor.shopping.R;
import com.bokor.shopping.UI.Fragment.MainFragment;
import com.bokor.shopping.adapter.Category.GridAdapter;
import com.bokor.shopping.adapter.Category.Indicator;
import com.bokor.shopping.adapter.SliderImage.WebBannerAdapter;
import com.bokor.shopping.adapter.Category.ViewPageAdapter;
import com.bokor.shopping.base.BaseMainFragment;
import com.bokor.shopping.base.ViewPagerListener;
import com.bokor.shopping.entity.HomeGridInfo;
import com.example.library.sliderimage.SliderLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 20/09/19.
 */
public class WechatFirstTabFragment extends BaseMainFragment{
    private Toolbar mToolbar;
    private RecyclerView mRecy;
    private boolean mInAtTop = true;
    private int mScrollTotal;
    private SmartRefreshLayout smartRefreshLayout;
    private List<HomeGridInfo> pageOneData = new ArrayList<>();
    private List<HomeGridInfo> pageTwoData = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();

//    private ChatAdapter mAdapter;

    public static WechatFirstTabFragment newInstance() {

        Bundle args = new Bundle();

        WechatFirstTabFragment fragment = new WechatFirstTabFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_fragment_tab_first, container, false);
        initView(view);
        SliderImage(view);
        initData(view);
        return view;
    }

    private void initView(View view) {
        mToolbar =  view.findViewById(R.id.toolbar);
        smartRefreshLayout =  view.findViewById(R.id.smartrefresh);
        mRecy = view.findViewById(R.id.recy);

//        getDefault(_mActivity).register(this);
//        EventBusActivityScope.getDefault(_mActivity).register(this);

        mToolbar.setTitle("Home");
    }
    private void SliderImage(View view){
        SliderLayout sliderLayout =  view.findViewById(R.id.slider_image);
        List<String> list = new ArrayList<>();
        list.add("data:image/jpeg;base64,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");
        list.add("data:image/jpeg;base64,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");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSm_cC--i7Kh209Kt14JkahCnz4y0-AnZbs-8zJsq6LOpdG4oM-");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQC70RjxpVUAP4Qs6rx9KrS6ndFdC9nJBDJD9axQad-5bYg-0Bo");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPHtwGENlHg2lXAD8Byh-Zl2aFQLYOQ_Pl1uu0P57-Sw_sxg7XVQ");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvDnq5_0Rty7xm83xhAWYyf-Foz3-UXQ7CPmDS90ghSJ8dyAjY");

        WebBannerAdapter webBannerAdapter = new WebBannerAdapter(getContext(),list);
        webBannerAdapter.setOnBannerItemClickListener(new SliderLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Clicked the first  " + position+"  item", Toast.LENGTH_SHORT).show();
            }
        });
        sliderLayout.setAdapter(webBannerAdapter);
    }
    private void initData(View view) {
        String[] gridTitles = getResources().getStringArray(R.array.home_bar_labels);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.home_bar_icon);
        for (int i = 0; i < gridTitles.length; i++) {
            if (i < 8) {
                pageOneData.add(new HomeGridInfo(typedArray.getResourceId(i, 0), gridTitles[i]));
            } else {
                pageTwoData.add(new HomeGridInfo(typedArray.getResourceId(i, 0), gridTitles[i]));
            }
        }

//        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.wechat_fragment_tab_first,null);

        Indicator headIndicator = view.findViewById(R.id.home_head_indicator);
        ViewPager headPager =  view.findViewById(R.id.home_head_pager);
        View pageOne = LayoutInflater.from(getActivity()).inflate(R.layout.home_gridview,null);
        GridView gridView1 = pageOne.findViewById(R.id.home_gridView);
        gridView1.setAdapter(new GridAdapter(getActivity(),pageOneData));
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((MainFragment) getParentFragment()).startBrotherFragment(MsgFragment.newInstance(pageOneData.get(i).getGridTitle()));
                Toast.makeText(getContext(),"Clicked"+pageOneData.get(i).getGridTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        //second page
        final View pageTwo = LayoutInflater.from(getActivity()).inflate(R.layout.home_gridview,null);
        GridView gridView2 =  pageTwo.findViewById(R.id.home_gridView);
        gridView2.setAdapter(new GridAdapter(getActivity(),pageTwoData));
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),pageTwoData.get(i).getGridTitle()+" is Coming soon",Toast.LENGTH_SHORT).show();
            }
        });
        mViewList.add(pageOne);
        mViewList.add(pageTwo);
        headPager.setAdapter(new ViewPageAdapter(mViewList));
        headPager.addOnPageChangeListener(new ViewPagerListener(headIndicator));
    }
//    @Override
//    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
//        super.onLazyInitView(savedInstanceState);
////        mRefreshLayout.setOnRefreshListener(this);
//
//        mRecy.setLayoutManager(new LinearLayoutManager(_mActivity));
//        mRecy.setHasFixedSize(true);
//        final int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f, getResources().getDisplayMetrics());
//        mRecy.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                outRect.set(0, 0, 0, space);
//            }
//        });
//        mAdapter = new ChatAdapter(_mActivity);
//        mRecy.setAdapter(mAdapter);
//
//        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                mScrollTotal += dy;
//                if (mScrollTotal <= 0) {
//                    mInAtTop = true;
//                } else {
//                    mInAtTop = false;
//                }
//            }
//        });
//
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
//
//              ((MainFragment) getParentFragment()).startBrotherFragment(MsgFragment.newInstance(mAdapter.getMsg(position)));
//            }
//        });
//
//        List<Chat> chatList = initDatas();
//        mAdapter.setDatas(chatList);
//    }

//    private List<Chat> initDatas() {
//        List<Chat> msgList = new ArrayList<>();
//
//        String[] name = new String[]{"Jake", "Eric", "Kenny", "Helen", "Carr"};
//        String[] chats = new String[]{"message1", "message2", "message3", "message4", "message5"};
//
//        for (int i = 0; i < 15; i++) {
//            int index = (int) (Math.random() * 5);
//            Chat chat = new Chat();
//            chat.name = name[index];
//            chat.message = chats[index];
//            msgList.add(chat);
//        }
//        return msgList;
//    }

//    @Override
//    public void onRefresh() {
//        mRefreshLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mRefreshLayout.setRefreshing(false);
//            }
//        }, 2000);
//    }


//    /**
//     * Reselected Tab
//     */
//    @Subscribe
//    public void onTabSelectedEvent(TabSelectedEvent event) {
//        if (event.position != MainFragment.FIRST) return;
//
//        if (mInAtTop) {
//            mRefreshLayout.setRefreshing(true);
//            onRefresh();
//        } else {
//            scrollToTop();
//        }
//    }
//
//    private void scrollToTop() {
//        mRecy.smoothScrollToPosition(0);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        getDefault(_mActivity).unregister(this);
//    }
}
