package com.scwang.smartrefresh.layout.listener;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Refresh listener
 * Created on 21/09/2019.
 */
public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
