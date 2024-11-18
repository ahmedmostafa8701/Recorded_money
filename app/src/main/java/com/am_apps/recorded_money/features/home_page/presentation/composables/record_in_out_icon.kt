package com.am_apps.recorded_money.features.home_page.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.confg.responsiveSize
import com.am_apps.recorded_money.confg.responsiveWidth
import com.am_apps.recorded_money.confg.screenSize

@Composable
fun RecordInOutIcon(collected:Double, spent:Double) {
    val cashImageId = R.drawable.cash_icon
    val painter = ImageBitmap.imageResource(id = cashImageId)
    val layoutDirection = LocalLayoutDirection.current
    val rotateDegree = if (layoutDirection == LayoutDirection.Rtl) 0f else 180f
    val configuration = LocalConfiguration.current
    screenSize = Pair(configuration.screenWidthDp, configuration.screenHeightDp)
    val imageSize = responsiveSize(100)
    val style = TextStyle(fontSize = (imageSize * 0.18f).value.sp)
    Row {
        Image(
            bitmap = painter,
            contentDescription = stringResource(id = R.string.cash_icon_content_desc),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(imageSize)
                .rotate(rotateDegree), colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Column {
            Text(
                text = spent.toString(),
                modifier = Modifier.padding(
                    top = imageSize * 0.14f,
                    start = responsiveWidth(5)
                ),
                style = style,
            )
            Text(
                text = collected.toString(),
                modifier = Modifier.padding(
                    top = imageSize * 0.29f,
                    start = responsiveWidth(5)
                ),
                style = style
            )
        }
    }
}