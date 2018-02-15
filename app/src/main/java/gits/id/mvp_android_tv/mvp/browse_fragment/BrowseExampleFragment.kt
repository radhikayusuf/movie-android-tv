package gits.id.mvp_android_tv.mvp.browse_fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v17.leanback.app.BrowseFragment
import android.support.v17.leanback.widget.ArrayObjectAdapter
import android.support.v4.content.ContextCompat
import android.widget.Toast
import gits.id.mvp_android_tv.R

/**
 * @author radhikayusuf.
 */
class BrowseExampleFragment : BrowseFragment(), BrowseExampleContract.View {


    var mPresenter: BrowseExampleContract.Presenter = BrowseExamplePresenter()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter.takeView(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.initComponent()
    }


    override fun context(): Context {
        return activity
    }

    override fun parseActivity(): Activity {
        return activity
    }

    override fun createTVComponent() {
        title = "GITS Indonesia"
        brandColor = ContextCompat.getColor(activity, R.color.colorPrimary)
        searchAffordanceColor = ContextCompat.getColor(activity, R.color.colorAccent)

        // setup all tv listener
        setOnSearchClickedListener {
            showMessage("Good Game Well Played")
        }
        onItemViewClickedListener = mPresenter as BrowseExamplePresenter
        onItemViewSelectedListener = mPresenter as BrowseExamplePresenter
    }

    override fun provideAdapter(adapter: ArrayObjectAdapter) {
        setAdapter(adapter)
    }

    override fun showMessage(msg: Int) {
        showMessage(getString(msg))
    }

    override fun showMessage(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

}