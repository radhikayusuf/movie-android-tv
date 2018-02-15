package gits.id.mvp_android_tv.data.remote

import gits.id.mvp_android_tv.data.DataSource
import gits.id.mvp_android_tv.data.remote.dao.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author radhikayusuf.
 */
class RemoteDataSource : DataSource {


    private val mApiService: GitsApiService = GitsApiService.create()

    override fun getHomeCarouselData(callback: DataSource.BasicCallback<List<Movie>>) {
        mApiService.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results ->
                    run {
                        if (results.results!!.isNotEmpty()) {
                            callback.onSuccess(results.results!!)
                        } else {
                            callback.onDataNotAvailable()
                        }
                    }
                }, { error ->
                    callback.onError(error.message!!)
                })
    }
}