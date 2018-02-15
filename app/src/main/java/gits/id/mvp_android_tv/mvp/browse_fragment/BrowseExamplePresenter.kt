package gits.id.mvp_android_tv.mvp.browse_fragment

import android.graphics.drawable.Drawable
import android.os.Handler
import android.support.v17.leanback.app.BackgroundManager
import android.support.v17.leanback.widget.*
import android.util.DisplayMetrics
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import gits.id.mvp_android_tv.R
import gits.id.mvp_android_tv.data.DataSource
import gits.id.mvp_android_tv.data.Repository
import gits.id.mvp_android_tv.data.remote.dao.Movie
import gits.id.mvp_android_tv.util.leanback_source.models.Card
import gits.id.mvp_android_tv.util.leanback_source.presenters.ImageCardPresenter
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.MaskTransformation
import java.util.*

/**
 * @author radhikayusuf.
 */
class BrowseExamplePresenter : BrowseExampleContract.Presenter, OnItemViewClickedListener, OnItemViewSelectedListener {

    var mView: BrowseExampleContract.View? = null

    private var mBackgroundManager: BackgroundManager? = null
    private var mBackgroundTimer: Timer? = null
    private var mBackgroundURI: String = ""
    private var mAdapter: ArrayObjectAdapter? = null

    private val mHandler = Handler()
    private val BACKGROUND_UPDATE_DELAY = 20
    private val mRepository: Repository = Repository()

    override fun initComponent() {
        prepareBackgroundManager()
        loadData()
        mView!!.createTVComponent()
    }

    private fun loadData() {
        mRepository.getHomeCarouselData(object : DataSource.BasicCallback<List<Movie>> {
            override fun onSuccess(data: List<Movie>) {
                handleLoadedData(data)
            }

            override fun onDataNotAvailable() {
                mView!!.showMessage(R.string.data_not_available)
            }

            override fun onError(message: String) {
                mView!!.showMessage(message)
            }

        })
    }

    private fun handleLoadedData(data: List<Movie>) {
        val listRowPresenter = ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM)
        val listCard = convertMovieToCard(data)

        val cardPresenter = ImageCardPresenter(mView!!.parseActivity())

        val firstRowAdapter = ArrayObjectAdapter(cardPresenter)
        val secondRowAdapter = ArrayObjectAdapter(cardPresenter)

        firstRowAdapter.addAll(0, listCard)
        secondRowAdapter.addAll(0, listCard)

        mAdapter = ArrayObjectAdapter(listRowPresenter)
        mAdapter!!.add(ListRow(HeaderItem("Overview"), firstRowAdapter))
        mAdapter!!.add(ListRow(HeaderItem("Popular"), secondRowAdapter))

        mView!!.provideAdapter(mAdapter!!)
    }

    private fun convertMovieToCard(data: List<Movie>): List<Card> {
        return java.util.ArrayList<Card>().apply {
            for (movie in data) {
                val card = Card()
                card.title = movie.title
                card.description = movie.overview
                card.imageUrl = "http://image.tmdb.org/t/p/w185/" + movie.poster_path
                card.backdgroundUrl = "http://image.tmdb.org/t/p/w1280/" + movie.backdrop_path
                add(card)
            }
        }

    }

    override fun takeView(view: BrowseExampleContract.View) {
        mView = view
    }

    override fun dropView() {
        mView = null
    }

    override fun onItemClicked(itemViewHolder: Presenter.ViewHolder?, item: Any?, rowViewHolder: RowPresenter.ViewHolder?, row: Row?) {

    }

    override fun onItemSelected(itemViewHolder: Presenter.ViewHolder?, item: Any?, rowViewHolder: RowPresenter.ViewHolder?, row: Row?) {
        if (item is Card) {
            mBackgroundURI = item.backdgroundUrl
            startBackgroundTimer()
        }
    }

    fun prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(mView!!.parseActivity())
        mBackgroundManager!!.attach(mView!!.parseActivity().window)
        mView!!.parseActivity().windowManager.defaultDisplay.getMetrics(DisplayMetrics())
    }

    fun startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer!!.cancel()
        }
        mBackgroundTimer = Timer()
        mBackgroundTimer!!.schedule(UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY.toLong())

    }

    fun updateBackground(uri: String) {
        loadImage(uri)
        mBackgroundTimer!!.cancel()
    }

    fun loadImage(uri: String) {
        Glide.with(mView!!.parseActivity())
                .load(uri)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(20)))
                .apply(RequestOptions.bitmapTransform(MaskTransformation(R.color.semi_background)))
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable?, transition: Transition<in Drawable>?) {
                        if (mBackgroundManager != null) {
                            if (resource != null) {
                                mBackgroundManager!!.drawable = resource
                            }
                        }
                    }
                })

    }

    private inner class UpdateBackgroundTask : TimerTask() {

        override fun run() {
            mHandler.post({
                if (mBackgroundURI != null) {
                    if (mBackgroundURI.isNotEmpty()) {
                        updateBackground(mBackgroundURI)
                    }
                }
            })

        }
    }


}