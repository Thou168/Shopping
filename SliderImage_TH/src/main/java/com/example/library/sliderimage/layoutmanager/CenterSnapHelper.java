package com.example.library.sliderimage.layoutmanager;

import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Class intended to support snapping for a {@link RecyclerView}
 * which use {@link SliderLayoutManager} as its {@link LayoutManager}.
 * <p>
 * The implementation will snap the center of the target child view to the center of
 * the attached {@link RecyclerView}.
 */
public class CenterSnapHelper extends RecyclerView.OnFlingListener {

    RecyclerView mRecyclerView;
    Scroller mGravityScroller;

    /**
     * when the dataSet is extremely large
     * {@link #snapToCenterView(SliderLayoutManager, SliderLayoutManager.OnPageChangeListener)}
     * may keep calling itself because the accuracy of float
     */
    private boolean snapToCenter = false;

    // Handles the snap on scroll case.
    private final RecyclerView.OnScrollListener mScrollListener =
            new RecyclerView.OnScrollListener() {

                boolean mScrolled = false;

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    final SliderLayoutManager layoutManager =
                            (SliderLayoutManager) recyclerView.getLayoutManager();
                    final SliderLayoutManager.OnPageChangeListener onPageChangeListener =
                            layoutManager.onPageChangeListener;
                    if (onPageChangeListener != null) {
                        onPageChangeListener.onPageScrollStateChanged(newState);
                    }

                    if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolled) {
                        mScrolled = false;
                        if (!snapToCenter) {
                            snapToCenter = true;
                            snapToCenterView(layoutManager, onPageChangeListener);
                        } else {
                            snapToCenter = false;
                        }
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (dx != 0 || dy != 0) {
                        mScrolled = true;
                    }
                }
            };

    @Override
    public boolean onFling(int velocityX, int velocityY) {
        SliderLayoutManager layoutManager = (SliderLayoutManager) mRecyclerView.getLayoutManager();
        if (layoutManager == null) {
            return false;
        }
        RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
        if (adapter == null) {
            return false;
        }

        if (!layoutManager.getInfinite() &&
                (layoutManager.mOffset == layoutManager.getMaxOffset()
                        || layoutManager.mOffset == layoutManager.getMinOffset())) {
            return false;
        }

        final int minFlingVelocity = mRecyclerView.getMinFlingVelocity();
        mGravityScroller.fling(0, 0, velocityX, velocityY,
                Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);

        if (layoutManager.mOrientation == SliderLayoutManager.VERTICAL
                && Math.abs(velocityY) > minFlingVelocity) {
            final int currentPosition = layoutManager.getCurrentPosition();
            final int offsetPosition = (int) (mGravityScroller.getFinalY() /
                    layoutManager.mInterval / layoutManager.getDistanceRatio());
            mRecyclerView.smoothScrollToPosition(layoutManager.getReverseLayout() ?
                    currentPosition - offsetPosition : currentPosition + offsetPosition);
            return true;
        } else if (layoutManager.mOrientation == SliderLayoutManager.HORIZONTAL
                && Math.abs(velocityX) > minFlingVelocity) {
            final int currentPosition = layoutManager.getCurrentPosition();
            final int offsetPosition = (int) (mGravityScroller.getFinalX() /
                    layoutManager.mInterval / layoutManager.getDistanceRatio());
            mRecyclerView.smoothScrollToPosition(layoutManager.getReverseLayout() ?
                    currentPosition - offsetPosition : currentPosition + offsetPosition);
            return true;
        }

        return true;
    }

    /**
     * Please attach after {{@link RecyclerView.LayoutManager} is setting}
     * Attaches the {@link CenterSnapHelper} to the provided RecyclerView, by calling
     * {@link RecyclerView#setOnFlingListener(RecyclerView.OnFlingListener)}.
     * You can call this method with {@code null} to detach it from the current RecyclerView.
     *
     * @param recyclerView The RecyclerView instance to which you want to add this helper or
     *                     {@code null} if you want to remove CenterSnapHelper from the current
     *                     RecyclerView.
     * @throws IllegalArgumentException if there is already a {@link RecyclerView.OnFlingListener}
     *                                  attached to the provided {@link RecyclerView}.
     */
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView)
            throws IllegalStateException {
        if (mRecyclerView == recyclerView) {
            return; // nothing to do
        }
        if (mRecyclerView != null) {
            destroyCallbacks();
        }
        mRecyclerView = recyclerView;
        if (mRecyclerView != null) {
            final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
            if (!(layoutManager instanceof SliderLayoutManager)) return;

            setupCallbacks();
            mGravityScroller = new Scroller(mRecyclerView.getContext(),
                    new DecelerateInterpolator());

            snapToCenterView((SliderLayoutManager) layoutManager,
                    ((SliderLayoutManager) layoutManager).onPageChangeListener);
        }
    }

    void snapToCenterView(SliderLayoutManager layoutManager,
                          SliderLayoutManager.OnPageChangeListener listener) {
        final int delta = layoutManager.getOffsetToCenter();
        if (delta != 0) {
            if (layoutManager.getOrientation()
                    == SliderLayoutManager.VERTICAL)
                mRecyclerView.smoothScrollBy(0, delta);
            else
                mRecyclerView.smoothScrollBy(delta, 0);
        } else {
            // set it false to make smoothScrollToPosition keep trigger the listener
            snapToCenter = false;
        }

        if (listener != null)
            listener.onPageSelected(layoutManager.getCurrentPosition());
    }

    /**
     * Called when an instance of a {@link RecyclerView} is attached.
     */
    void setupCallbacks() throws IllegalStateException {
        if (mRecyclerView.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        mRecyclerView.addOnScrollListener(mScrollListener);
        mRecyclerView.setOnFlingListener(this);
    }

    /**
     * Called when the instance of a {@link RecyclerView} is detached.
     */
    void destroyCallbacks() {
        mRecyclerView.removeOnScrollListener(mScrollListener);
        mRecyclerView.setOnFlingListener(null);
    }
}
