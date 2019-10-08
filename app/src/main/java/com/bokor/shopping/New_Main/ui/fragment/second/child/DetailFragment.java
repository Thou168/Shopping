package com.bokor.shopping.New_Main.ui.fragment.second.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bokor.shopping.New_Main.base.BaseBackFragment;
import com.bokor.shopping.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Created by YoKeyword on 16/2/3.
 */
public class DetailFragment extends BaseBackFragment {
    public static final String TAG = DetailFragment.class.getSimpleName();
    private static final int REQ_MODIFY_FRAGMENT = 100;
    private static final String ARG_TITLE = "arg_title";

    static final String KEY_RESULT_TITLE = "title";

    private Toolbar mToolbar;
    private TextView mTvContent;
    private FloatingActionButton mFab;
    private String mTitle;

    public static DetailFragment newInstance(String title) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(ARG_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mTvContent = (TextView) view.findViewById(R.id.tv_content);

        mToolbar.setTitle(mTitle);

        initToolbarNav(mToolbar);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initDelayView();
    }

    private void initDelayView() {
        mTvContent.setText(R.string.large_text);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForResult(ModifyDetailFragment.newInstance(mTitle), REQ_MODIFY_FRAGMENT);
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            mTitle = data.getString(KEY_RESULT_TITLE);
            mToolbar.setTitle(mTitle);
            // 保存被改变的 title
            getArguments().putString(ARG_TITLE, mTitle);
            Toast.makeText(_mActivity, R.string.modify_title, Toast.LENGTH_SHORT).show();
        }
    }
}
