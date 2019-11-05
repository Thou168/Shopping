package com.bokor.shopping.New_Main.ui.fragment.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bokor.shopping.New_Main.base.BaseMainFragment;
import com.bokor.shopping.New_Main.ui.fragment.first.child.FirstHomeFragment;
import com.bokor.shopping.R;

/**
 * Created by  on 8/10/2019.
 */
public class ZhihuFirstFragment extends BaseMainFragment {

    public static ZhihuFirstFragment newInstance() {
        Bundle args = new Bundle();
        ZhihuFirstFragment fragment = new ZhihuFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_first, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(FirstHomeFragment.class) == null) {
            loadRootFragment(R.id.fl_first_container, FirstHomeFragment.newInstance());
        }
    }
}
