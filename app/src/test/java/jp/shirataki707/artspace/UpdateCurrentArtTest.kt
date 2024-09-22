package jp.shirataki707.artspace

import junit.framework.TestCase.assertEquals
import org.junit.Test

class UpdateCurrentArtTest {

    @Test
    fun testUpdateCurrentArtPrevious() {
        val artList = listOf(
            Art("Mona Lisa", "Leonardo da Vinci", "1503-1519", R.drawable.leonardo_da_vinci_mona_lisa_jpg),
            Art("The Scream", "Edvard Munch", "1893", R.drawable.munch_scream_1_jpg)
        )

        val currentArt = 1
        val updatedArt = updateCurrentArt(currentArt, artList, ButtonType.Previous)
        assertEquals(0, updatedArt)
    }

    @Test
    fun testUpdateCurrentArtNext() {
        val artList = listOf(
            Art("Mona Lisa", "Leonardo da Vinci", "1503-1519", R.drawable.leonardo_da_vinci_mona_lisa_jpg),
            Art("The Scream", "Edvard Munch", "1893", R.drawable.munch_scream_1_jpg)
        )

        val currentArt = 0
        val updatedArt = updateCurrentArt(currentArt, artList, ButtonType.Next)
        assertEquals(1, updatedArt)
    }

    @Test
    fun testUpdateCurrentArtPreviousWrapAround() {
        val artList = listOf(
            Art("Mona Lisa", "Leonardo da Vinci", "1503-1519", R.drawable.leonardo_da_vinci_mona_lisa_jpg),
            Art("The Scream", "Edvard Munch", "1893", R.drawable.munch_scream_1_jpg)
        )

        val currentArt = 0
        val updatedArt = updateCurrentArt(currentArt, artList, ButtonType.Previous)
        assertEquals(1, updatedArt)
    }

    @Test
    fun testUpdateCurrentArtNextWrapAround() {
        val artList = listOf(
            Art("Mona Lisa", "Leonardo da Vinci", "1503-1519", R.drawable.leonardo_da_vinci_mona_lisa_jpg),
            Art("The Scream", "Edvard Munch", "1893", R.drawable.munch_scream_1_jpg)
        )

        val currentArt = 1
        val updatedArt = updateCurrentArt(currentArt, artList, ButtonType.Next)
        assertEquals(0, updatedArt)
    }
}
