package gits.id.mvp_android_tv.base

/**
 * @author radhikayusuf.
 */
data class BaseApiModel<T>(
        var page: Int,
        var total_results: Int,
        var total_pages: Int,
        var results: T? = null

        //Todo code above just for testing. Change it with real base response from API
)