package com.roudikk.guia.sample.feature.dialogs

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roudikk.guia.extensions.LocalNavigator
import com.roudikk.guia.extensions.currentOrThrow
import com.roudikk.guia.extensions.push
import com.roudikk.guia.sample.feature.common.navigation.LocalRootNavigator
import com.roudikk.guia.sample.feature.common.theme.AppTheme
import com.roudikk.guia.sample.feature.dialogs.navigation.BlockingBottomSheetKey
import com.roudikk.guia.sample.feature.dialogs.navigation.BlockingDialogKey
import com.roudikk.guia.sample.feature.dialogs.navigation.CancelableDialogKey

@Composable
internal fun DialogsScreen() {
    val navigator = LocalNavigator.currentOrThrow
    val rootNavigator = LocalRootNavigator.current

    DialogsContent(
        onCancelableDialogClicked = { navigator.push(CancelableDialogKey(false)) },
        onBlockingDialogClicked = { navigator.push(BlockingDialogKey(false)) },
        onDialogToDialogClicked = { navigator.push(BlockingDialogKey(true)) },
        onBlockingBottomSheetClicked = { rootNavigator.push(BlockingBottomSheetKey()) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialogsContent(
    onCancelableDialogClicked: () -> Unit = {},
    onBlockingDialogClicked: () -> Unit = {},
    onDialogToDialogClicked: () -> Unit = {},
    onBlockingBottomSheetClicked: () -> Unit = {},
) {
    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Dialogs") },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            state = lazyListState,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Button(
                    modifier = Modifier
                        .widthIn(min = 300.dp),
                    onClick = onCancelableDialogClicked
                ) {
                    Text(text = "Cancelable Dialog")
                }
            }

            item {
                Button(
                    modifier = Modifier
                        .widthIn(min = 300.dp),
                    onClick = onBlockingDialogClicked
                ) {
                    Text(text = "Blocking Dialog")
                }
            }
            item {
                Button(
                    modifier = Modifier
                        .widthIn(min = 300.dp),
                    onClick = onDialogToDialogClicked
                ) {
                    Text(text = "Dialog To Dialog")
                }
            }
            item {
                Button(
                    modifier = Modifier
                        .widthIn(min = 300.dp),
                    onClick = onBlockingBottomSheetClicked
                ) {
                    Text(text = "Blocking Bottom Sheet")
                }
            }
        }
    }
}

@Preview(
    device = Devices.PIXEL_3
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_3
)
@Composable
private fun DialogsContentPreview() = AppTheme {
    DialogsContent()
}
