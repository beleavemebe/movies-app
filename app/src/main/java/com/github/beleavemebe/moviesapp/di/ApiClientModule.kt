package com.github.beleavemebe.moviesapp.di

import com.github.beleavemebe.moviesapp.apiclient.ApiConstants
import com.github.beleavemebe.moviesapp.apiclient.ApiConstants.TIMEOUT
import com.github.beleavemebe.moviesapp.apiclient.NyTimesService
import com.github.beleavemebe.moviesapp.utils.log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {

    @[Provides IntoSet]
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor {
            log { it }
        }
    }

    @[Provides IntoSet]
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newUrl = chain.request().url.newBuilder()
                .addQueryParameter("api-key", ApiConstants.API_KEY)
                .build()

            val newRequest = chain.request().newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideOkHttpClient(
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptors(interceptors)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideNyTimesService(
        retrofit: Retrofit
    ): NyTimesService {
        return retrofit.create()
    }
}

private fun OkHttpClient.Builder.addInterceptors(
    interceptors: Set<Interceptor>
): OkHttpClient.Builder = apply {
    interceptors.forEach(::addInterceptor)
}
