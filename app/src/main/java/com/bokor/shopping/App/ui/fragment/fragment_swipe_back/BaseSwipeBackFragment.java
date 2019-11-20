package com.bokor.shopping.App.ui.fragment.fragment_swipe_back;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.bokor.shopping.R;

import me.bokor.fragmentation_swipeback.SwipeBackFragment;


/**
 * Created on 15/10/19.
 */
public class BaseSwipeBackFragment extends SwipeBackFragment {

    protected void _initToolbar(Toolbar toolbar) {
//        toolbar.setTitle("SwipeBackActivity's Fragment");
        toolbar.setNavigationIcon(R.drawable.icon_back_left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
