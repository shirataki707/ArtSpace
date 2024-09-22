package jp.shirataki707.artspace

import androidx.annotation.DrawableRes

data class Art(

    val title: String,
    val artist: String,
    val year: String,
    @DrawableRes val image: Int,
)
