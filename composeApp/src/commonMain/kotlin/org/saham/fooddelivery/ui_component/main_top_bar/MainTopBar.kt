package org.saham.fooddelivery.ui_component.main_top_bar


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import fooddeliverymp.composeapp.generated.resources.Res
import fooddeliverymp.composeapp.generated.resources.app_name
import fooddeliverymp.composeapp.generated.resources.ic_vector_arrow_back
import fooddeliverymp.composeapp.generated.resources.ic_vector_menu
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.saham.fooddelivery.ui_component.app_top_bar.AppTopAppBar
import org.saham.fooddelivery.ui_component.icon_wrapper.IconWrapper
import org.saham.fooddelivery.ui_component.input_fields.clearFocusOnTouch
import org.saham.fooddelivery.ui_component.pull_to_refresh_indicator.PullToRefreshIndicator


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MainTopBar(
    isShowBottomBar: Boolean = false,
    isShowRightIcon: Boolean = false,
    isRefreshing: Boolean = false,
    isPullRefresh: Boolean = true,
    title: StringResource = Res.string.app_name,
    titleText: String? = null,
    leftIcon: DrawableResource = Res.drawable.ic_vector_arrow_back,
    rightIcon: DrawableResource = Res.drawable.ic_vector_menu,
    contentAlignment: Alignment = Alignment.TopStart,
    onRefresh: () -> Unit = {},
    onRightIconClicked: () -> Unit = {},
    onLeftIconClicked: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val refreshState = rememberPullToRefreshState()


    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .clearFocusOnTouch(),
        topBar = {
            AppTopAppBar(
                title = if (titleText.isNullOrEmpty()) {
                    stringResource(resource = title)
                } else {
                    titleText
                }, navigationIcon = {
                    if (!isShowBottomBar) {
                        IconButton(onClick = { onLeftIconClicked() }) {
                            IconWrapper(size = 24.dp) {
                                Icon(
                                    painter = painterResource(resource = leftIcon),
                                    contentDescription = "Menu icon",
                                )
                            }
                        }
                    }
                }, actions = {
                    if (isShowRightIcon) {
                        IconButton(onClick = dropUnlessResumed(block = onRightIconClicked)) {
                            IconWrapper(size = 24.dp) {
                                Icon(
                                    painter = painterResource(resource = rightIcon),
                                    contentDescription = "Profile",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }, scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullToRefresh(
                    state = refreshState, isRefreshing = isRefreshing, onRefresh = onRefresh
                ), contentAlignment = contentAlignment
        ) {

            content()

            if (isPullRefresh)
                PullToRefreshIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    state = refreshState
                )
        }
    }
}
