package com.bokor.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bokor.shopping.UI.Fragment.MainFragment;
import com.bokor.shopping.UI.Fragment.first.WechatFirstTabFragment;
import com.bokor.shopping.UI.Fragment.first.ZhihuFirstFragment;
import com.bokor.shopping.UI.view.BottomBar;
import com.bokor.shopping.UI.view.BottomBarTab;
import com.bokor.shopping.base.BaseMainFragment;
import com.bokor.shopping.event.TabSelectedEvent;

import me.bokor.eventbusactivityscope.EventBusActivityScope;
import me.bokor.fragmentation.SupportActivity;
import me.bokor.fragmentation.SupportFragment;
import me.bokor.fragmentation.anim.DefaultHorizontalAnimator;
import me.bokor.fragmentation.anim.FragmentAnimator;

public class MainActivity extends SupportActivity implements BaseMainFragment.OnBackToFirstListener {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private SupportFragment[] mFragments = new SupportFragment[4];

    private BottomBar mBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SupportFragment firstFragment = findFragment(WechatFirstTabFragment.class);
//        if (firstFragment == null) {
//            mFragments[FIRST] = WechatFirstTabFragment.newInstance();
//            mFragments[SECOND] = WechatFirstTabFragment.newInstance();
//            mFragments[THIRD] = WechatFirstTabFragment.newInstance();
//
//            loadMultipleRootFragment(R.id.fl_container, FIRST,
//                    mFragments[FIRST],
//                    mFragments[SECOND],
//                    mFragments[THIRD]);
//        } else {
//
//            mFragments[FIRST] = firstFragment;
//            mFragments[SECOND] = findFragment(WechatFirstTabFragment.class);
//            mFragments[THIRD] = findFragment(WechatFirstTabFragment.class);
//        }

        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
//        initView();
    }

    @Override
    public void onBackToFirstFragment() {

    }
//    private void initView() {
//        mBottomBar =  findViewById(R.id.bottomBar);
//
//        mBottomBar .addItem(new BottomBarTab(this, R.drawable.png_home, getString(R.string.bottombar_home)))
//                .addItem(new BottomBarTab(this, R.drawable.png_camera, getString(R.string.bottombar_post)))
//                .addItem(new BottomBarTab(this, R.drawable.png_account, getString(R.string.bottombar_account)));
//
//        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position, int prePosition) {
//                showHideFragment(mFragments[position], mFragments[prePosition]);
//            }
//
//            @Override
//            public void onTabUnselected(int position) {
//
//            }
//
//            @Override
//            public void onTabReselected(int position) {
//                final SupportFragment currentFragment = mFragments[position];
//                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
//
//                // 如果不在该类别Fragment的主页,则回到主页;
//                if (count > 1) {
//                    if (currentFragment instanceof ZhihuFirstFragment) {
//                        currentFragment.popToChild(ZhihuFirstFragment.class, false);
//                    } else if (currentFragment instanceof ZhihuFirstFragment) {
//                        currentFragment.popToChild(ZhihuFirstFragment.class, false);
//                    } else if (currentFragment instanceof ZhihuFirstFragment) {
//                        currentFragment.popToChild(ZhihuFirstFragment.class, false);
//                    } else if (currentFragment instanceof ZhihuFirstFragment) {
//                        currentFragment.popToChild(ZhihuFirstFragment.class, false);
//                    }
//                    return;
//                }
//
//
//                // 这里推荐使用EventBus来实现 -> 解耦
//                if (count == 1) {
//                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
//                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
//                    EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
//                }
//            }
//        });
//    }
//    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
