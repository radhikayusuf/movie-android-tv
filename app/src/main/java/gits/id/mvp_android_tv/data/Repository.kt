package gits.id.mvp_android_tv.data

import gits.id.mvp_android_tv.data.local.LocalDataSource
import gits.id.mvp_android_tv.data.remote.RemoteDataSource
import gits.id.mvp_android_tv.data.remote.dao.Movie

/**
 * @author radhikayusuf.
 */
class Repository : DataSource {

    private val remoteDataSource: RemoteDataSource = RemoteDataSource()
    private val localDataSource: LocalDataSource = LocalDataSource()


    override fun getHomeCarouselData(response: DataSource.BasicCallback<List<Movie>>) {
        remoteDataSource.getHomeCarouselData(object : DataSource.BasicCallback<List<Movie>> {
            override fun onSuccess(data: List<Movie>) {
                response.onSuccess(data)
            }

            override fun onDataNotAvailable() {
                response.onDataNotAvailable()
            }

            override fun onError(message: String) {
                response.onError(message)
            }

        })
    }

}