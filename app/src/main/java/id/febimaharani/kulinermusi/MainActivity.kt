package id.febimaharani.kulinermusi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import id.febimaharani.kulinermusi.data.Kuliner
import id.febimaharani.kulinermusi.data.kuliners
import id.febimaharani.kulinermusi.ui.theme.KulinerMusiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // KulinerMusiTheme composable menerapkan warna, tipografi dan bentuk aplikasi
            KulinerMusiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    KulinerMusiApp()
                }
            }
        }
    }
}

//Composable menampilkan aplikasi dan list kuliner.
@Composable
fun KulinerMusiApp() {
    Scaffold(
        topBar = {
            KulinerTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(kuliners) {
                KulinerItem(
                    kuliner = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}


 //Composable untuk menampilkan item daftar yang berisi ikon kuliner dan informasi kuliner.
@Composable
fun KulinerItem(
    kuliner: Kuliner, // berisi data yang mengisi item daftar kuliner.
    modifier: Modifier = Modifier // akan melakukan modifikasi yang diterapkan di dalam composable ini.
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                KulinerIcon(kuliner.imageResourceId)
                KulinerInformation(kuliner.name)
                Spacer(Modifier.weight(1f))
                KulinerItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                KulinerHobby(
                    kuliner.hobbies, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

//Composable untuk menampilkan item daftar yang berisi ikon kuliner dan informasi kuliner.
@Composable
private fun KulinerItemButton(
    expanded: Boolean, // menunjukkan apakah ikon mempperluas atau mengecilkan ditampilkan.
    onClick: () -> Unit, // menunjukkan aksi yang akan terjadi ketika tombol di klik
    modifier: Modifier = Modifier // modifikasi yang diterapkan pada somposable ini.
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class) // ditambahkan untuk bisa menggunakan TopAppBar tampa eror
@Composable
fun KulinerTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_ampera_logo),

                    contentDescription = null // memastikan ikon atau gambar dekoratif tidak dibacakan pembaca layar.
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun KulinerIcon(
    @DrawableRes KulinerIcon: Int, // ID sumber gambar kuliner
    modifier: Modifier = Modifier // modifier yang akan diterapkan pada composable ini
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(KulinerIcon),

        contentDescription = null // memungkinkan layanan untuk mengabaikan elemen ini.
    )
}

@Composable
fun KulinerInformation(
    @StringRes kulinerName: Int, // menampilkan nama kuliner
    modifier: Modifier = Modifier // untuk melakukan modifikasi composable.
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(kulinerName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
    }
}

@Composable
fun KulinerHobby(
    @StringRes KulinerHobby: Int, // ID untuk string teks yang akan ditampilkan.
    modifier: Modifier = Modifier // untuk melakukan modifikasi composable.
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(KulinerHobby),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun KulinerPreview() {
    KulinerMusiTheme(darkTheme = false) { // untuk menampilkan aplikasi dengan tema terang.
        KulinerMusiApp()
    }
}

@Preview
@Composable
fun KulinerDarkThemePreview() {
    KulinerMusiTheme(darkTheme = true) { // untuk menampilkan aplikasi dengan tema gelap.
        KulinerMusiApp()
    }
}