package edu.ucne.freimyhidalgo_ap2_p2.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.dto.RepositoryDTO
import edu.ucne.freimyhidalgo_ap2_p2.data.remote.repositorys.RepositoryManajerApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL_Retenciones = " https://api.github.com/users/enelramon/repos/"

    @Provides
    @Singleton
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun providesRepositoryManagerApi(moshi: Moshi): RepositoryManajerApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_Retenciones)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RepositoryManajerApi::class.java)
    }

}