package jp.shirataki707.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.shirataki707.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                val artList = fetchArtList(this)
                ArtSpaceApp(artList = artList)

            }
        }
    }
}

@Composable
fun ArtSpaceApp(artList: List<Art>) {
    var currentArt by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ArtImageAndText(
            art = artList[currentArt],
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    currentArt = updateCurrentArt(
                        currentArt = currentArt,
                        artList = artList,
                        buttonType = ButtonType.Previous
                    )
                },
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    currentArt = updateCurrentArt(
                        currentArt = currentArt,
                        artList = artList,
                        buttonType = ButtonType.Next
                    )
                },
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ArtImageAndText(
    art: Art,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(art.image),
            contentDescription = art.title,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = art.title,
                fontSize = 36.sp
            )
            Text(
                text = art.artist
            )
            Text(
                text = art.year
            )
        }
    }
}

enum class ButtonType {
    Previous,
    Next
}

@VisibleForTesting
internal fun updateCurrentArt(
    currentArt: Int,
    artList: List<Art>,
    buttonType: ButtonType
): Int {
    return when (buttonType) {
        ButtonType.Previous -> if (currentArt > 0) {
            currentArt - 1
        } else {
            artList.size - 1
        }
        ButtonType.Next -> if (currentArt < artList.size - 1) {
            currentArt + 1
        } else {
            0
        }
    }
}

private fun fetchArtList(
    context: ComponentActivity
): List<Art> {
    val artList: List<Art> = listOf(
        Art(
            title = context.getString(R.string.mona_lisa),
            artist = context.getString(R.string.leonardo_da_vinci),
            year = context.getString(R.string._1503_1519),
            image = R.drawable.leonardo_da_vinci_mona_lisa_jpg
        ),
        Art(
            title = context.getString(R.string.the_birth_of_venus),
            artist = context.getString(R.string.sandro_botticelli),
            year = context.getString(R.string._1485),
            image = R.drawable.the_birth_of_venus_jpg
        ),
        Art(
            title = context.getString(R.string.the_gleaners),
            artist = context.getString(R.string.jean_fran_ois_millet),
            year = context.getString(R.string._1857),
            image = R.drawable.millet_glaneuses_jpg
        ),
        Art(
            title = context.getString(R.string.the_scream),
            artist = context.getString(R.string.edvard_munch),
            year = context.getString(R.string._1893),
            image = R.drawable.munch_scream_1_jpg
        ),
        Art(
            title = context.getString(R.string.gernika),
            artist = context.getString(R.string.pablo_picasso),
            year = context.getString(R.string._1937),
            image = R.drawable.gernika_jpg
        )
    )
    return artList
}
