package gits.id.mvp_android_tv.data.remote.dao

/**
 * @author radhikayusuf.
 */
data class Movie(
        var vote_count: Int,
        var id: Int,
        var isVideo: Boolean = false,
        var vote_average: Double,
        var title: String? = null,
        var popularity: Double,
        var poster_path: String? = null,
        var original_language: String? = null,
        var original_title: String? = null,
        var backdrop_path: String? = null,
        var isAdult: Boolean = false,
        var overview: String? = null,
        var release_date: String? = null,
        var genre_ids: List<Int>? = null
)