package gits.id.mvp_android_tv

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by irfanirawansukirman on 24/01/18.
 */

class GitsApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        GitsApplication.context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        fun getApplicationContext(): Context {
            return GitsApplication.context!!
        }
    }

}