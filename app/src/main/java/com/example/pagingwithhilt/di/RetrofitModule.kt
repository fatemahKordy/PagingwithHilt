package com.example.pagingwithhilt.di

import com.example.pagingwithhilt.Network.APIService
import android.provider.SyncStateContract
import com.example.pagingwithhilt.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)//Application scope
@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    fun provideUrl():String=Constants.BASE_URL

    @Singleton
    @Provides
    fun provideApiService( moshi: Moshi,base_url:String): APIService {
        // The Retrofit object with the Moshi converter.
        return  Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(base_url)
            .build().create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi {
        // Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
        return Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .build()

    }
}