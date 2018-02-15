package gits.id.mvp_android_tv.data.local

import gits.id.mvp_android_tv.GitsApplication
import gits.id.mvp_android_tv.data.DataSource
import gits.id.mvp_android_tv.data.remote.dao.Movie

/**
 * @author radhikayusuf.
 */
class LocalDataSource : DataSource {

    val applicationContext = GitsApplication.getApplicationContext()

    override fun getHomeCarouselData(response: DataSource.BasicCallback<List<Movie>>) {

    }



}