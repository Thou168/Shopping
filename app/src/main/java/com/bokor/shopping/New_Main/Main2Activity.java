package com.bokor.shopping.New_Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;

import com.bokor.shopping.New_Main.base.BaseMainFragment;
import com.bokor.shopping.New_Main.event.TabSelectedEvent;
import com.bokor.shopping.New_Main.ui.fragment.first.ZhihuFirstFragment;
import com.bokor.shopping.New_Main.ui.fragment.first.child.FirstHomeFragment;
import com.bokor.shopping.New_Main.ui.fragment.fourth.ZhihuFourthFragment;
import com.bokor.shopping.New_Main.ui.fragment.fourth.child.MeFragment;
import com.bokor.shopping.New_Main.ui.fragment.second.ZhihuSecondFragment;
import com.bokor.shopping.New_Main.ui.fragment.second.child.ViewPagerFragment;
import com.bokor.shopping.New_Main.ui.fragment.third.ZhihuThirdFragment;
import com.bokor.shopping.New_Main.ui.fragment.third.child.ShopFragment;
import com.bokor.shopping.R;
import com.bokor.shopping.UI.view.BottomBar;
import com.bokor.shopping.UI.view.BottomBarTab;

import me.bokor.eventbusactivityscope.EventBusActivityScope;
import me.bokor.fragmentation.SupportActivity;
import me.bokor.fragmentation.SupportFragment;
import me.bokor.fragmentation.anim.DefaultHorizontalAnimator;
import me.bokor.fragmentation.anim.FragmentAnimator;

public class Main2Activity extends SupportActivity implements BaseMainFragment.OnBackToFirstListener  {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];
    private BottomBar mBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SupportFragment firstFragment = findFragment(ZhihuFirstFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = ZhihuFirstFragment.newInstance();
            mFragments[SECOND] = ZhihuSecondFragment.newInstance();
            mFragments[THIRD] = ZhihuThirdFragment.newInstance();
            mFragments[FOURTH] = ZhihuFourthFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            /* The library has already done Fragment recovery, all without extra processing, no overlapping problems
             Here we need to get a reference to mFragments
             */
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(ZhihuSecondFragment.class);
            mFragments[THIRD] = findFragment(ZhihuThirdFragment.class);
            mFragments[FOURTH] = findFragment(ZhihuFourthFragment.class);
        }
        initView();
    }

    private void initView() {
        mBottomBar = findViewById(R.id.bottomBar);
        mBottomBar.addItem(new BottomBarTab(this, R.drawable.icon_home, getString(R.string.bottombar_home)))
                .addItem(new BottomBarTab(this, R.drawable.png_camera, getString(R.string.bottombar_post)))
                .addItem(new BottomBarTab(this, R.drawable.png_account, getString(R.string.bottombar_account)))
                .addItem(new BottomBarTab(this, R.drawable.png_account, getString(R.string.bottombar_more)));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
//                if (position == 3)
//                    setGoneBottom();
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                final SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // If not on the home page of the category Fragment, return to the home page;
                if (count > 1) {
                    if (currentFragment instanceof ZhihuFirstFragment) {
                        currentFragment.popToChild(FirstHomeFragment.class, false);
                    } else if (currentFragment instanceof ZhihuSecondFragment) {
                        currentFragment.popToChild(ViewPagerFragment.class, false);
                    } else if (currentFragment instanceof ZhihuThirdFragment) {
                        currentFragment.popToChild(ShopFragment.class, false);
                    } else if (currentFragment instanceof ZhihuFourthFragment) {
                        currentFragment.popToChild(MeFragment.class, false);
                    }
                    return;
                }


                // It is recommended to use EventBus to implement -> decoupling
                if (count == 1) {
                    /* Received in FirstPagerFragment, because it is a nested grandson Fragment, it is convenient to use EventBus
                     Mainly for interaction: Reselect tab. Move to the top if the list is not at the top, or refresh if it is already at the top
                     */
                    EventBusActivityScope.getDefault(Main2Activity.this).post(new TabSelectedEvent(position));
                }
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }
    public void setVisiBottom(){
        mBottomBar.setVisibility(View.VISIBLE);
    }
    public void setGoneBottom(){
        mBottomBar.setVisibility(View.GONE);
    }
    //Animation fragment
//    @Override
//    public FragmentAnimator onCreateFragmentAnimator() {
//        // Set landscape (same as Android 4.x animation)
//        return new DefaultHorizontalAnimator();
//    }
    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }
}
