package com.bokor.shopping.New_Main.ui.fragment.fourth;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bokor.shopping.New_Main.Main2Activity;
import com.bokor.shopping.New_Main.base.BaseMainFragment;
import com.bokor.shopping.New_Main.ui.fragment.fourth.child.AvatarFragment;
import com.bokor.shopping.New_Main.ui.fragment.fourth.child.MeFragment;
import com.bokor.shopping.R;

/**
 * Created on 18/10/19.
 */
public class ZhihuFourthFragment extends BaseMainFragment {
    private Toolbar mToolbar;
    private View mView;
    private Main2Activity main2Activity ;
    public static ZhihuFourthFragment newInstance() {

        Bundle args = new Bundle();

        ZhihuFourthFragment fragment = new ZhihuFourthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.zhihu_fragment_fourth, container, false);

        main2Activity = (Main2Activity)this.getActivity();
        return mView;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(AvatarFragment.class) == null) {
            loadFragment();
        }

        mToolbar = mView.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.me);
        mToolbar.setNavigationIcon(R.drawable.icon_back_left_arrow);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                main2Activity.setVisiBottom();
                _mActivity.onBackPressed();
            }
        });
    }

    private void loadFragment() {
        loadRootFragment(R.id.fl_fourth_container_upper, AvatarFragment.newInstance());
        loadRootFragment(R.id.fl_fourth_container_lower, MeFragment.newInstance());
    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }

}
