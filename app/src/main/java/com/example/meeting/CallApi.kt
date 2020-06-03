package com.example.meeting

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CallApi {

    //val BASE_URL = "https://api.jsonbin.io/"

    companion object {
        const val BASE_URL = "https://api.jsonbin.io/"
        const val secret_key = "secret-key:"
        const val key_val = "$2b$10${"$"}wup2Y0uYiB5bJQII62xy4u.OG3LkaTA5TvaYxZWBIpKi6kEsSsDWu"

    }

    @Headers(secret_key + key_val)
    @GET("b/5ece36edd7f1581464c65dea")
    fun getDatas(): Call<List<Data>>
}