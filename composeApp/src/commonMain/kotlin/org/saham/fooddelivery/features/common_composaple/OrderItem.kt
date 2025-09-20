package com.saham.fooddelivery.features.common_composaple

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fooddeliverymp.composeapp.generated.resources.Res
import fooddeliverymp.composeapp.generated.resources.customerName
import fooddeliverymp.composeapp.generated.resources.orderStatus
import fooddeliverymp.composeapp.generated.resources.restaurant
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.saham.fooddelivery.theme.black_normal
import org.saham.fooddelivery.theme.bold
import org.saham.fooddelivery.theme.natural_light
import org.saham.fooddelivery.ui_component.ui_extensions.noRippleClickable

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    customerName: String,
    restaurant: String,
    orderStatus: String,
    onClicked: () -> Unit = {},
) {
    Surface(
        modifier = modifier.noRippleClickable(onClick = onClicked),
        color = natural_light,
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(Res.string.customerName),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.bold.copy(color = black_normal),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = customerName,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = black_normal),
                )
            }


            Spacer(modifier = modifier.height(8.dp))

            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(Res.string.restaurant),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.bold.copy(color = black_normal),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = restaurant,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = black_normal),
                )
            }


            Spacer(modifier = modifier.height(8.dp))


            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(Res.string.orderStatus),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.bold.copy(color = black_normal),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = orderStatus,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = black_normal),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OrderItemPreview() {
    OrderItem(
        customerName = "Alice",
        restaurant = "Pizza Palace",
        orderStatus = "Preparing",
        onClicked = {}
    )
}