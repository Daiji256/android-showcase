package io.github.daiji256.showcase.feature.license

data class Dependency(
    val id: String,
    val name: String,
    val uri: String,
    val author: String,
    val license: License,
)

data class License(
    val name: String,
    val uri: String,
)
