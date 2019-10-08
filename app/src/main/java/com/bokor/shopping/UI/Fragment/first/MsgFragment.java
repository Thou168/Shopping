package com.bokor.shopping.UI.Fragment.first;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bokor.shopping.R;
import com.bokor.shopping.base.BaseBackFragment;
import com.bokor.shopping.entity.Chat;

/**
 * Created on 20/09/19.
 */
public class MsgFragment extends BaseBackFragment {
    private static final String ARG_MSG = "arg_msg";

    private Toolbar mToolbar;
    private RecyclerView mRecy;
    private EditText mEtSend;
    private Button mBtnSend;

    private String name;

//    private Chat mChat;
//    private MsgAdapter mAdapter;

    public static MsgFragment newInstance(String name) {

        Bundle args = new Bundle();
//        args.putParcelable(ARG_MSG, name);
        args.putString(ARG_MSG, name);
        Log.d("545454545","2121"+name);
        MsgFragment fragment = new MsgFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        name = getArguments().getParcelable(ARG_MSG);
        name = getArguments().getString(ARG_MSG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wechat_fragment_tab_first_msg, container, false);
        initView(view);
        return attachToSwipeBack(view);
    }

    private void initView(View view) {
        mToolbar =  view.findViewById(R.id.toolbar);
//        mBtnSend =  view.findViewById(R.id.btn_send);
//        mEtSend =  view.findViewById(R.id.et_send);
//        mRecy =  view.findViewById(R.id.recy);

        mToolbar.setTitle(name);
        initToolbarNav(mToolbar);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);

//        _mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//
//        mRecy.setLayoutManager(new LinearLayoutManager(_mActivity));
//        mRecy.setHasFixedSize(true);
//        mAdapter = new MsgAdapter(_mActivity);
//        mRecy.setAdapter(mAdapter);
//
//        mBtnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = mEtSend.getText().toString().trim();
//                if (TextUtils.isEmpty(str)) return;
//
//                mAdapter.addMsg(new Msg(str));
//                mEtSend.setText("");
//                mRecy.scrollToPosition(mAdapter.getItemCount() - 1);
//            }
//        });
//
//        mAdapter.addMsg(new Msg(mChat.message));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecy = null;
        _mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        hideSoftInput();
    }
}
