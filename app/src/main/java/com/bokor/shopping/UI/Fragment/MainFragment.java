package com.bokor.shopping.UI.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.bokor.shopping.R;
import com.bokor.shopping.UI.Fragment.first.WechatFirstTabFragment;
import com.bokor.shopping.UI.view.BottomBar;
import com.bokor.shopping.UI.view.BottomBarTab;
import com.bokor.shopping.event.TabSelectedEvent;

import me.bokor.fragmentation.SupportFragment;

import static me.bokor.eventbusactivityscope.EventBusActivityScope.getDefault;

/**
 * Created on 20/09/19.
 */
public class MainFragment extends SupportFragment {
    private static final int REQ_MSG = 10;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private SupportFragment[] mFragments = new SupportFragment[3];

    private BottomBar mBottomBar;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(WechatFirstTabFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = WechatFirstTabFragment.newInstance();
            mFragments[SECOND] = WechatFirstTabFragment.newInstance();
            mFragments[THIRD] = WechatFirstTabFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            // Here the library has done Fragment recovery, all without additional processing, no overlapping problems

            // Here we need to get a reference to mFragments
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(WechatFirstTabFragment.class);
            mFragments[THIRD] = findChildFragment(WechatFirstTabFragment.class);
        }
    }

    private void initView(View view) {
        mBottomBar = view.findViewById(R.id.bottomBar);
        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.icon_home, getString(R.string.bottombar_home)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.png_camera, getString(R.string.bottombar_post)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.png_account, getString(R.string.bottombar_account)));

        // Simulate unread messages
        mBottomBar.getItem(FIRST).setUnreadCount(9);

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

                BottomBarTab tab = mBottomBar.getItem(FIRST);
                if (position == FIRST) {
                    tab.setUnreadCount(0);
                } else {
                    tab.setUnreadCount(tab.getUnreadCount() + 1);
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                //Received in FirstPagerFragment, FirstHomeFragment, because it is a nested Fragment
                //Mainly for interaction: re-select tabs. Move to the top if the list is not at the top, or refresh if it is already at the top
                getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MSG && resultCode == RESULT_OK) {

        }
    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}
