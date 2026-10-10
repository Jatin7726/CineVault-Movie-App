package com.cinevault.app.presentation.home;

import com.cinevault.app.domain.usecase.GetPopularMoviesUseCase;
import com.cinevault.app.domain.usecase.GetTrendingMoviesUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetTrendingMoviesUseCase> getTrendingMoviesProvider;

  private final Provider<GetPopularMoviesUseCase> getPopularMoviesProvider;

  public HomeViewModel_Factory(Provider<GetTrendingMoviesUseCase> getTrendingMoviesProvider,
      Provider<GetPopularMoviesUseCase> getPopularMoviesProvider) {
    this.getTrendingMoviesProvider = getTrendingMoviesProvider;
    this.getPopularMoviesProvider = getPopularMoviesProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getTrendingMoviesProvider.get(), getPopularMoviesProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetTrendingMoviesUseCase> getTrendingMoviesProvider,
      Provider<GetPopularMoviesUseCase> getPopularMoviesProvider) {
    return new HomeViewModel_Factory(getTrendingMoviesProvider, getPopularMoviesProvider);
  }

  public static HomeViewModel newInstance(GetTrendingMoviesUseCase getTrendingMovies,
      GetPopularMoviesUseCase getPopularMovies) {
    return new HomeViewModel(getTrendingMovies, getPopularMovies);
  }
}
