package com.bokor.shopping.App.base;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.bokor.shopping.R;

import me.bokor.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class BaseBackFragment extends SupportFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.icon_back_left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
