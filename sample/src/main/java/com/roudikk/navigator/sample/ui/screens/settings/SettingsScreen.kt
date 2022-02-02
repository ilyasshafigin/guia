package com.roudikk.navigator.sample.ui.screens.settings

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.roudikk.navigator.Screen
import com.roudikk.navigator.findNavigator
import com.roudikk.navigator.sample.AppPreview
import com.roudikk.navigator.sample.ui.composables.AppTopAppBar
import kotlinx.parcelize.Parcelize

@Parcelize
class SettingsScreen : Screen {

    @Composable
    override fun AnimatedVisibilityScope.Content() {
        SettingsContent()
    }
}

@Composable
private fun AnimatedVisibilityScope.SettingsContent() {
    val navigator = findNavigator()
    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            AppTopAppBar(
                title = "Compose Navigator",
                lazyListState = lazyListState,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigator.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            )
        },
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Text(
                        text = "Compose Navigator is an alternative to the androidx compose navigation component."
                    )
                }

                item {
                    Text(
                        text = "The main difference is that, unlike navigation component, destinations don't"
                                + " need to be declared beforehand with their transitions (With Accompanist navigation transitions). "
                    )
                }

                item {
                    Text(
                        text = "Compose navigator also supports dialog and bottom sheet navigation out of the box. " +
                                "A navigation node can be defined by simply extending any of Screen, Dialog or BottomSheet"
                    )
                }

                item {
                    Text(
                        text = "It also supports all Enter/Exit transitions provided by Compose animations"
                    )
                }

                item {
                    Text(text = "Check out the code and usages at: ")
                }

                item {
                    val uriHandler = LocalUriHandler.current
                    val annotatedString = buildAnnotatedString {
                        pushStringAnnotation(
                            tag = "URL",
                            annotation = "https://github.com/roudikk/compose-navigator",
                        )
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("https://github.com/roudikk/compose-navigator")
                        }
                    }
                    ClickableText(
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        text = annotatedString,
                        onClick = {
                            annotatedString
                                .getStringAnnotations("URL", it, it)
                                .firstOrNull()?.let { stringAnnotation ->
                                    uriHandler.openUri(stringAnnotation.item)
                                }
                        }
                    )
                }

            }

            val uriHandler = LocalUriHandler.current
            val annotatedString = buildAnnotatedString {
                pushStringAnnotation(
                    tag = "URL",
                    annotation = "https://github.com/roudikk",
                )
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append("By Roudi Korkis Kanaan")
                }
            }
            ClickableText(
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .animateEnterExit(
                        enter = slideInVertically { it * 3 }
                                + fadeIn(),
                        exit = slideOutVertically { it }
                                + fadeOut()
                    )
                    .padding(16.dp),
                text = annotatedString,
                onClick = {
                    annotatedString
                        .getStringAnnotations("URL", it, it)
                        .firstOrNull()?.let { stringAnnotation ->
                            uriHandler.openUri(stringAnnotation.item)
                        }
                }
            )
        }
    }
}

@Preview(
    device = Devices.PIXEL_3
)
@Composable
private fun SettingsContentPreview() = AppPreview {
    AnimatedVisibility(visible = true) {
        SettingsContent()
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_3
)
@Composable
private fun SettingsContentPreviewDark() = AppPreview {
    AnimatedVisibility(visible = true) {
        SettingsContent()
    }
}