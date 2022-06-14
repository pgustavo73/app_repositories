package com.pgustavo.app.repositories.data.module

import com.google.gson.annotations.SerializedName

data class User (
    val id: Long,
    val login: String,
    val name: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val location: String,
    @SerializedName("avatar_url")
    val avatarURL: String
        )