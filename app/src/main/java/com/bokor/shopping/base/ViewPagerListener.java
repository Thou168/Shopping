package com.bokor.shopping.base;

import androidx.viewpager.widget.ViewPager;

import com.bokor.shopping.adapter.Category.Indicator;

/**
 * Created by asus on 2016/9/1.
 */
public class ViewPagerListener implements ViewPager.OnPageChangeListener {

    private Indicator mIndicator;

    public ViewPagerListener(Indicator indicator) {
        mIndicator = indicator;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mIndicator.setOffset(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
