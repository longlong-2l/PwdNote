package com.surpassli.login.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser (
    var userId: String,
    var displayName: String
)