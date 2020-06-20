package com.skyeng.model

data class SearchResult (
    val id: Int,
    val meanings: List<Meaning>,
    val text: String
)