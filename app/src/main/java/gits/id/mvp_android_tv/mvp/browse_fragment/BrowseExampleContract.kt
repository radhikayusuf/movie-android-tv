package gits.id.mvp_android_tv.mvp.browse_fragment

import android.app.Activity
import android.support.v17.leanback.widget.OnItemViewClickedListener
import android.support.v17.leanback.widget.OnItemViewSelectedListener
import android.view.View
import gits.id.mvp_android_tv.base.BasePresenter
import gits.id.mvp_android_tv.base.BaseView

/**
 * @author radhikayusuf.
 */
interface BrowseExampleContract {

    interface View : BaseView<BrowseExampleContract.Presenter> {
        fun parseActivity() : Activity
    }

    interface Presenter : BasePresenter<BrowseExampleContract.View> {
        fun initComponent()
    }

}