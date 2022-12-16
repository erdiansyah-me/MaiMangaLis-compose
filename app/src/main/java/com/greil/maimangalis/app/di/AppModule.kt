package com.greil.maimangalis.app.di

import com.greil.maimangalis.domain.usecase.MangaInteractor
import com.greil.maimangalis.domain.usecase.MangaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideMangaUseCase(mangaInteractor: MangaInteractor): MangaUseCase
}