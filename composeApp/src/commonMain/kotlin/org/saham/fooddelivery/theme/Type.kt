package org.saham.fooddelivery.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import fooddeliverymp.composeapp.generated.resources.Res
import fooddeliverymp.composeapp.generated.resources.cairo_bold
import fooddeliverymp.composeapp.generated.resources.cairo_medium
import fooddeliverymp.composeapp.generated.resources.cairo_regular
import fooddeliverymp.composeapp.generated.resources.cairo_semibold
import org.jetbrains.compose.resources.Font


@Composable
fun appFontFamily() = FontFamily(
    Font(Res.font.cairo_regular, FontWeight.Normal),
    Font(Res.font.cairo_medium, FontWeight.Medium),
    Font(Res.font.cairo_semibold, FontWeight.SemiBold),
    Font(Res.font.cairo_bold, FontWeight.Bold)
)



@Composable
fun appTypography() = Typography().run {
    val fontFamily = appFontFamily()
    copy(
        displayLarge = displayLarge.copy(
            fontFamily = fontFamily,
        ),
        displayMedium = displayMedium.copy(
            fontFamily = fontFamily,
        ),
        displaySmall = displaySmall.copy(
            fontFamily = fontFamily,
        ),
        headlineLarge = headlineLarge.copy(
            fontFamily = fontFamily,
        ),
        headlineMedium = headlineMedium.copy(
            fontFamily = fontFamily,
        ),
        headlineSmall = headlineSmall.copy(
            fontFamily = fontFamily,
        ),
        titleLarge = titleLarge.copy(
            fontFamily = fontFamily,
        ),
        titleMedium = titleMedium.copy(
            fontFamily = fontFamily,
        ),
        titleSmall = titleSmall.copy(
            fontFamily = fontFamily,
        ),
        bodyLarge = bodyLarge.copy(
            fontFamily = fontFamily,
        ),
        bodyMedium = bodyMedium.copy(
            fontFamily = fontFamily,
        ),
        bodySmall = bodySmall.copy(
            fontFamily = fontFamily,
        ),
        labelLarge = labelLarge.copy(
            fontFamily = fontFamily,
        ),
        labelMedium = labelMedium.copy(
            fontFamily = fontFamily,
        ),
        labelSmall = labelSmall.copy(
            fontFamily = fontFamily,
        ),
    )
}


val TextStyle.regular: TextStyle
    get() = this.copy(fontWeight = FontWeight.Normal, lineHeight = fontSize)

val TextStyle.medium: TextStyle
    get() = this.copy(fontWeight = FontWeight.Medium, lineHeight = fontSize)

val TextStyle.semiBold: TextStyle
    get() = this.copy(fontWeight = FontWeight.SemiBold, lineHeight = fontSize)

val TextStyle.bold: TextStyle
    get() = this.copy(fontWeight = FontWeight.Bold, lineHeight = fontSize)