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
public final class GetWatchlistUseCase_Factory implements Factory<GetWatchlistUseCase> {
  private final Provider<MovieRepository> repositoryProvider;

  public GetWatchlistUseCase_Factory(Provider<MovieRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetWatchlistUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetWatchlistUseCase_Factory create(Provider<MovieRepository> repositoryProvider) {
    return new GetWatchlistUseCase_Factory(repositoryProvider);
  }

  public static GetWatchlistUseCase newInstance(MovieRepository repository) {
    return new GetWatchlistUseCase(repository);
  }
}
