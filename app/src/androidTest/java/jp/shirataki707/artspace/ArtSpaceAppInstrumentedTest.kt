package jp.shirataki707.artspace

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArtSpaceAppInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testArtSpaceAppDisplaysArt() {
        composeTestRule.setContent {
            val artList = listOf(
                Art("Mona Lisa", "Leonardo da Vinci", "1503-1519", R.drawable.leonardo_da_vinci_mona_lisa_jpg)
            )
            ArtSpaceApp(artList = artList)
        }

        composeTestRule.onNodeWithText("Mona Lisa").assertExists()
        composeTestRule.onNodeWithText("Leonardo da Vinci").assertExists()
        composeTestRule.onNodeWithText("1503-1519").assertExists()
    }

    @Test
    fun testNextButton() {
        composeTestRule.setContent {
            val artList = listOf(
                Art("Mona Lisa", "Leonardo da Vinci", "1503-1519", R.drawable.leonardo_da_vinci_mona_lisa_jpg),
                Art("The Scream", "Edvard Munch", "1893", R.drawable.munch_scream_1_jpg)
            )
            ArtSpaceApp(artList = artList)
        }

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("The Scream").assertExists()
    }

    @Test
    fun testPreviousButton() {
        composeTestRule.setContent {
            val artList = listOf(
                Art("Mona Lisa", "Leonardo da Vinci", "1503-1519", R.drawable.leonardo_da_vinci_mona_lisa_jpg),
                Art("The Scream", "Edvard Munch", "1893", R.drawable.munch_scream_1_jpg)
            )
            ArtSpaceApp(artList = artList)
        }

        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText("The Scream").assertExists()
    }
}