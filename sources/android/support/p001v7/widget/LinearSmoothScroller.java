package android.support.p001v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.p001v7.widget.RecyclerView.LayoutManager;
import android.support.p001v7.widget.RecyclerView.LayoutParams;
import android.support.p001v7.widget.RecyclerView.SmoothScroller;
import android.support.p001v7.widget.RecyclerView.SmoothScroller.Action;
import android.support.p001v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.support.p001v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* renamed from: android.support.v7.widget.LinearSmoothScroller */
public class LinearSmoothScroller extends SmoothScroller {
    private final float MILLISECONDS_PER_PX;
    protected final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
    protected int mInterimTargetDx = 0;
    protected int mInterimTargetDy = 0;
    protected final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
    protected PointF mTargetVector;

    public LinearSmoothScroller(Context context) {
        this.MILLISECONDS_PER_PX = calculateSpeedPerPixel(context.getResources().getDisplayMetrics());
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
    }

    /* Access modifiers changed, original: protected */
    public void onTargetFound(View targetView, State state, Action action) {
        int dx = calculateDxToMakeVisible(targetView, getHorizontalSnapPreference());
        int dy = calculateDyToMakeVisible(targetView, getVerticalSnapPreference());
        int time = calculateTimeForDeceleration((int) Math.sqrt((double) ((dx * dx) + (dy * dy))));
        if (time > 0) {
            action.update(-dx, -dy, time, this.mDecelerateInterpolator);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSeekTargetStep(int dx, int dy, State state, Action action) {
        if (getChildCount() == 0) {
            stop();
            return;
        }
        this.mInterimTargetDx = clampApplyScroll(this.mInterimTargetDx, dx);
        this.mInterimTargetDy = clampApplyScroll(this.mInterimTargetDy, dy);
        if (this.mInterimTargetDx == 0 && this.mInterimTargetDy == 0) {
            updateActionForInterimTarget(action);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        this.mInterimTargetDy = 0;
        this.mInterimTargetDx = 0;
        this.mTargetVector = null;
    }

    /* Access modifiers changed, original: protected */
    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 25.0f / ((float) displayMetrics.densityDpi);
    }

    /* Access modifiers changed, original: protected */
    public int calculateTimeForDeceleration(int dx) {
        return (int) Math.ceil(((double) calculateTimeForScrolling(dx)) / 0.3356d);
    }

    /* Access modifiers changed, original: protected */
    public int calculateTimeForScrolling(int dx) {
        return (int) Math.ceil((double) (((float) Math.abs(dx)) * this.MILLISECONDS_PER_PX));
    }

    /* Access modifiers changed, original: protected */
    public int getHorizontalSnapPreference() {
        if (this.mTargetVector == null || this.mTargetVector.x == 0.0f) {
            return 0;
        }
        return this.mTargetVector.x > 0.0f ? 1 : -1;
    }

    /* Access modifiers changed, original: protected */
    public int getVerticalSnapPreference() {
        if (this.mTargetVector == null || this.mTargetVector.y == 0.0f) {
            return 0;
        }
        return this.mTargetVector.y > 0.0f ? 1 : -1;
    }

    /* Access modifiers changed, original: protected */
    public void updateActionForInterimTarget(Action action) {
        PointF scrollVector = computeScrollVectorForPosition(getTargetPosition());
        if (scrollVector == null || (scrollVector.x == 0.0f && scrollVector.y == 0.0f)) {
            action.jumpTo(getTargetPosition());
            stop();
            return;
        }
        normalize(scrollVector);
        this.mTargetVector = scrollVector;
        this.mInterimTargetDx = (int) (scrollVector.x * 10000.0f);
        this.mInterimTargetDy = (int) (scrollVector.y * 10000.0f);
        action.update((int) (((float) this.mInterimTargetDx) * 1.2f), (int) (((float) this.mInterimTargetDy) * 1.2f), (int) (((float) calculateTimeForScrolling(10000)) * 1.2f), this.mLinearInterpolator);
    }

    private int clampApplyScroll(int tmpDt, int dt) {
        int before = tmpDt;
        tmpDt -= dt;
        if (before * tmpDt <= 0) {
            return 0;
        }
        return tmpDt;
    }

    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        switch (snapPreference) {
            case -1:
                return boxStart - viewStart;
            case 0:
                int dtStart = boxStart - viewStart;
                if (dtStart > 0) {
                    return dtStart;
                }
                int dtEnd = boxEnd - viewEnd;
                if (dtEnd < 0) {
                    return dtEnd;
                }
                return 0;
            case 1:
                return boxEnd - viewEnd;
            default:
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
    }

    public int calculateDyToMakeVisible(View view, int snapPreference) {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            return 0;
        }
        LayoutParams params = (LayoutParams) view.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedTop(view) - params.topMargin, layoutManager.getDecoratedBottom(view) + params.bottomMargin, layoutManager.getPaddingTop(), layoutManager.getHeight() - layoutManager.getPaddingBottom(), snapPreference);
    }

    public int calculateDxToMakeVisible(View view, int snapPreference) {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
            return 0;
        }
        LayoutParams params = (LayoutParams) view.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedLeft(view) - params.leftMargin, layoutManager.getDecoratedRight(view) + params.rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), snapPreference);
    }

    @Nullable
    public PointF computeScrollVectorForPosition(int targetPosition) {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof ScrollVectorProvider) {
            return ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(targetPosition);
        }
        Log.w("LinearSmoothScroller", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + ScrollVectorProvider.class.getCanonicalName());
        return null;
    }
}
