package io.github.daiji256.showcase.feature.license

import android.content.res.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

suspend fun Resources.getDependencies(): List<Dependency> {
    val dependenciesJson = withContext(Dispatchers.IO) {
        openRawResource(R.raw.dependencies).bufferedReader().use { it.readText() }
    }
    val dependenciesArr = JSONObject(dependenciesJson).getJSONArray("dependencies")
    return buildList {
        for (dependencyIdx in 0 until dependenciesArr.length()) {
            val dependencyObj = dependenciesArr.getJSONObject(dependencyIdx)
            val dependency = Dependency(
                id = dependencyObj.getString("id"),
                name = dependencyObj.getString("name"),
                uri = dependencyObj.getString("uri"),
                author = dependencyObj.getString("author"),
                license = License(
                    name = dependencyObj.getString("license_name"),
                    uri = dependencyObj.getString("license_uri"),
                ),
            )
            add(dependency)
        }
        sortBy { it.name }
    }
}
