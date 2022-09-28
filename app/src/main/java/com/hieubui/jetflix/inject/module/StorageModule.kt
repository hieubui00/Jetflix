package com.hieubui.jetflix.inject.module

import android.app.Application
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named

@Module
class StorageModule {

    @Provides
    @Named("cache")
    fun provideCacheStorage(application: Application): File = application.cacheDir
}