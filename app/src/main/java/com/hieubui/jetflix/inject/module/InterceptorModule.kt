package com.hieubui.jetflix.inject.module

import com.hieubui.jetflix.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.internal.platform.Platform
import javax.inject.Named

@Module
class InterceptorModule {

    @Provides
    @Named("authentication")
    fun provideAuthenticationInterceptor(): Interceptor = Interceptor {
        val url = it.request().url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val request = it.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor it.proceed(request)
    }

    @Provides
    @Named("logging")
    fun provideLoggingInterceptor(): Interceptor = LoggingInterceptor.Builder()
        .loggable(BuildConfig.DEBUG)
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .request("Request")
        .response("Response")
        .build()
}
