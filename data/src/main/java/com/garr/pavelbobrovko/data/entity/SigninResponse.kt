package com.garr.pavelbobrovko.data.entity

import com.google.gson.annotations.SerializedName

class SigninResponse(val objectId: String = ""
                     , @SerializedName("user-token") val userToken: String = "")