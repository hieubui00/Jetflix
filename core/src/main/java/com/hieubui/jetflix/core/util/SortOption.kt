package com.hieubui.jetflix.core.util

enum class SortBy {
    POPULARITY,
    RELEASE_DATE,
    REVENUE,
    PRIMARY_RELEASE_DATE,
    ORIGINAL_TITLE,
    VOTE_AVERAGE,
    VOTE_COUNT
}

enum class SortOrder(val code: String) {
    DESCENDING("desc"),
    ASCENDING("asc")
}
