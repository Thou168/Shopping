package com.bokor.shopping.App.ui.fragment.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bokor.shopping.App.base.BaseMainFragment;
import com.bokor.shopping.App.ui.fragment.third.child.ShopFragment;
import com.bokor.shopping.R;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class ZhihuThirdFragment extends BaseMainFragment {

    public static ZhihuThirdFragment newInstance() {

        Bundle args = new Bundle();

        ZhihuThirdFragment fragment = new ZhihuThirdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_third, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        if (findChildFragment(ShopFragment.class) == null) {
            // ShopFragment是flow包里的
            loadRootFragment(R.id.fl_third_container, ShopFragment.newInstance());
        }
    }
}
