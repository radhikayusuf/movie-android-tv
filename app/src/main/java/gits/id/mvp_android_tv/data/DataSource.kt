package gits.id.mvp_android_tv.data

import gits.id.mvp_android_tv.base.BaseApiModel
import gits.id.mvp_android_tv.data.remote.dao.Movie

/**
 * @author radhikayusuf.
 */
interface DataSource {

    fun getHomeCarouselData(response: BasicCallback<List<Movie>>)

    interface BasicCallback<T> {

        fun onSuccess(data: T)

        fun onDataNotAvailable()

        fun onError(message: String)

    }
}