package me.bokor.fragmentation_swipeback.core;

import androidx.annotation.FloatRange;
import android.view.View;

import me.bokor.fragmentation.SwipeBackLayout;

/**
 * Created by YoKey on 17/6/29.
 */

public interface ISwipeBackFragment {

    View attachToSwipeBack(View view);

    SwipeBackLayout getSwipeBackLayout();

    void setSwipeBackEnable(boolean enable);

    void setEdgeLevel(SwipeBackLayout.EdgeLevel edgeLevel);

    void setEdgeLevel(int widthPixel);

    /**
     * Set the offset of the parallax slip.
     */
    void setParallaxOffset(@FloatRange(from = 0.0f, to = 1.0f) float offset);
}
