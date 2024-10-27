package id.febimaharani.kulinermusi.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import id.febimaharani.kulinermusi.R

/**
 * A data class to represent the information presented in the dog card
 */
data class Kuliner(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val hobbies: Int
)

val kuliners = listOf(
    Kuliner(R.drawable.eskacang, R.string.kuliner_name_1, R.string.kuliner_description_1),
    Kuliner(R.drawable.lakso, R.string.kuliner_name_2, R.string.kuliner_description_2),
    Kuliner(R.drawable.model, R.string.kuliner_name_3, R.string.kuliner_description_3),
    Kuliner(R.drawable.otakotak, R.string.kuliner_name_4, R.string.kuliner_description_4),
    Kuliner(R.drawable.pempek, R.string.kuliner_name_5, R.string.kuliner_description_5),
    Kuliner(R.drawable.pempekpanggang, R.string.kuliner_name_6, R.string.kuliner_description_6),
    Kuliner(R.drawable.pinangdara, R.string.kuliner_name_7, R.string.kuliner_description_7),
    Kuliner(R.drawable.pindangpatin, R.string.kuliner_name_8, R.string.kuliner_description_8),
    Kuliner(R.drawable.srikaya, R.string.kuliner_name_9, R.string.kuliner_description_9),
    Kuliner(R.drawable.tekwan, R.string.kuliner_name_10, R.string.kuliner_deskription_10)
)
