package com.hieubui.jetflix.inject.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hieubui.jetflix.BuildConfig
import com.hieubui.jetflix.core.data.remote.request.TheMovieDatabaseService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        StorageModule::class,
        InterceptorModule::class,
    ]
)
class NetworkModule {

    @Provides
    fun provideCache(@Named("cache") cacheStorage: File): Cache {
        return Cache(cacheStorage, 32 * 1024 * 1024L)
    }

    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        @Named("authentication") authenticationInterceptor: Interceptor,
        @Named("logging") loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor(authenticationInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_HOST)
        .build()

    @Singleton
    @Provides
    fun provideTheMovieDatabaseService(retrofit: Retrofit): TheMovieDatabaseService {
        return retrofit.create(TheMovieDatabaseService::class.java)
    }
}