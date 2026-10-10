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
public final class GetPopularMoviesUseCase_Factory implements Factory<GetPopularMoviesUseCase> {
  private final Provider<MovieRepository> repositoryProvider;

  public GetPopularMoviesUseCase_Factory(Provider<MovieRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetPopularMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetPopularMoviesUseCase_Factory create(
      Provider<MovieRepository> repositoryProvider) {
    return new GetPopularMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetPopularMoviesUseCase newInstance(MovieRepository repository) {
    return new GetPopularMoviesUseCase(repository);
  }
}
