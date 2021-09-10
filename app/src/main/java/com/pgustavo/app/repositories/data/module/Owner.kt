package com.pgustavo.app.repositories.data.module

import com.google.gson.annotations.SerializedName

data class Owner (
    val login: String,
    @SerializedName("avatar_url")
    val avatarURL: String
)