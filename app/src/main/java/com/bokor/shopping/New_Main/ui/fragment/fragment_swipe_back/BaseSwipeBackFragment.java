package com.bokor.shopping.New_Main.ui.fragment.fragment_swipe_back;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.bokor.shopping.R;

import me.bokor.fragmentation_swipeback.SwipeBackFragment;


/**
 * Created by YoKeyword on 16/4/21.
 */
public class BaseSwipeBackFragment extends SwipeBackFragment {

    protected void _initToolbar(Toolbar toolbar) {
//        toolbar.setTitle("SwipeBackActivity's Fragment");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
