package io.github.daiji256.showcase.feature.license

import android.content.res.Resources
import androidx.annotation.RawRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

suspend fun Resources.getDependencies(@RawRes resId: Int): List<Dependency> {
    val dependenciesJson = withContext(Dispatchers.IO) {
        openRawResource(resId).bufferedReader().use { it.readText() }
    }
    return Json.decodeFromString<Metadata>(dependenciesJson).dependencies.map { it.toDependency() }
}

@Serializable
private data class Metadata(
    @SerialName("dependencies") val dependencies: List<SerializableDependency>,
) {
    @Serializable
    data class SerializableDependency(
        @SerialName("id") val id: String,
        @SerialName("name") val name: String,
        @SerialName("uri") val uri: String,
        @SerialName("author") val author: String,
        @SerialName("license_name") val licenseName: String,
        @SerialName("license_uri") val licenseUri: String,
    ) {
        fun toDependency(): Dependency = Dependency(
            id = id,
            name = name,
            uri = uri,
            author = author,
            licenseName = licenseName,
            licenseUri = licenseUri,
        )
    }
}
