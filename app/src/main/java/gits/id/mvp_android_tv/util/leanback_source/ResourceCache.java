package gits.id.mvp_android_tv.util.leanback_source;

import android.util.SparseArray;
import android.view.View;

/**
 * @author radhikayusuf.
 */

public class ResourceCache {
    private final SparseArray<View> mCachedViews = new SparseArray<View>();

    public <ViewType extends View> ViewType getViewById(View view, int resId) {
        View child = mCachedViews.get(resId, null);
        if (child == null) {
            child = view.findViewById(resId);
            mCachedViews.put(resId, child);
        }
        return (ViewType) child;
    }
}
