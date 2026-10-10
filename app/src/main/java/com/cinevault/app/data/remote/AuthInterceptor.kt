package com.cinevault.app.data.remote

import android.util.Base64
import com.cinevault.app.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import javax.inject.Inject

/**
 * Adds TMDB authentication credentials to every outgoing network request.
 * - Adds `Authorization: Bearer <TOKEN>` header.
 * - Extracted v3 `api_key=<KEY>` query parameter is attached to all URLs so endpoints
 *   accepting either v3 key or v4 Bearer token succeed cleanly.
 */
class AuthInterceptor @Inject constructor() : Interceptor {

    private val apiKey: String? by lazy {
        val token = BuildConfig.TMDB_TOKEN.trim()
        if (token.isEmpty()) return@lazy null

        if (token.startsWith("eyJ")) {
            try {
                val parts = token.split(".")
                if (parts.size >= 2) {
                    val payload = String(Base64.decode(parts[1], Base64.DEFAULT or Base64.NO_PADDING or Base64.URL_SAFE))
                    val json = JSONObject(payload)
                    json.optString("aud").ifEmpty { null }
                } else null
            } catch (_: Exception) {
                null
            }
        } else {
            token
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = BuildConfig.TMDB_TOKEN.trim()
        val originalRequest = chain.request()
        val urlBuilder = originalRequest.url.newBuilder()

        // Attach api_key parameter if available
        apiKey?.let { key ->
            if (originalRequest.url.queryParameter("api_key") == null) {
                urlBuilder.addQueryParameter("api_key", key)
            }
        }

        val requestBuilder = originalRequest.newBuilder()
            .url(urlBuilder.build())
            .addHeader("accept", "application/json")

        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}
