package vn.vntravel.architecturalprinciples.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.vntravel.architecturalprinciples.data.remote.FlightApi
import vn.vntravel.architecturalprinciples.data.repository.FlightRepository
import vn.vntravel.architecturalprinciples.data.repository.FlightRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideFlightRepository(flightApi: FlightApi): FlightRepository {
        return FlightRepositoryImpl(flightApi)
    }
}