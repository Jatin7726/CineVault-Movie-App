package com.cinevault.app.domain.usecase;

import com.cinevault.app.domain.repository.MovieRepository;
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
public final class GetTrendingMoviesUseCase_Factory implements Factory<GetTrendingMoviesUseCase> {
  private final Provider<MovieRepository> repositoryProvider;

  public GetTrendingMoviesUseCase_Factory(Provider<MovieRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetTrendingMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetTrendingMoviesUseCase_Factory create(
      Provider<MovieRepository> repositoryProvider) {
    return new GetTrendingMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetTrendingMoviesUseCase newInstance(MovieRepository repository) {
    return new GetTrendingMoviesUseCase(repository);
  }
}
