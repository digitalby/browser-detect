package me.digitalby.browserdetect.sample

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.digitalby.browserdetect.BrowserInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowserListScreen(viewModel: BrowserListViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.app_name)) })
        },
    ) { padding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
            contentAlignment = Alignment.Center,
        ) {
            when (val s = state) {
                is BrowserListState.Loading -> CircularProgressIndicator()
                is BrowserListState.Loaded -> BrowserList(s.browsers)
            }
        }
    }
}

@Composable
private fun BrowserList(browsers: List<BrowserInfo>) {
    if (browsers.isEmpty()) {
        Text("No browsers detected on this device.")
        return
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(browsers, key = { it.packageName }) { info ->
            BrowserRow(info)
            HorizontalDivider()
        }
    }
}

@Composable
private fun BrowserRow(info: BrowserInfo) {
    val context = LocalContext.current
    val iconBitmap =
        remember(info.packageName) {
            runCatching { info.loadIcon(context).toBitmap() }.getOrNull()
        }

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://digitalby.me"))
                            .setPackage(info.packageName)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    try {
                        context.startActivity(intent)
                    } catch (_: ActivityNotFoundException) {
                        // Browser uninstalled between detection and tap; ignore.
                    }
                }.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (iconBitmap != null) {
            Icon(
                painter = BitmapPainter(iconBitmap.asImageBitmap()),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = androidx.compose.ui.graphics.Color.Unspecified,
            )
        } else {
            Spacer(Modifier.size(40.dp))
        }
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = info.displayLabel,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                if (info.isDefault) {
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "DEFAULT",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            Text(
                text = info.packageName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            val versionLabel = info.versionName ?: "v?"
            Text(
                text = "$versionLabel  ·  ${info.category}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
