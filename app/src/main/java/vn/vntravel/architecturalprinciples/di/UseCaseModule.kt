package vn.vntravel.architecturalprinciples.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.vntravel.architecturalprinciples.data.repository.FlightRepository
import vn.vntravel.architecturalprinciples.domain.usecase.SearchFlightTicketsUseCase


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideSearchFlightTicketUseCase(flightRepository: FlightRepository): SearchFlightTicketsUseCase {
        return SearchFlightTicketsUseCase(flightRepository)
    }
}