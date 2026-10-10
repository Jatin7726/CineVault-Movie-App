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
public final class ToggleWatchlistUseCase_Factory implements Factory<ToggleWatchlistUseCase> {
  private final Provider<MovieRepository> repositoryProvider;

  public ToggleWatchlistUseCase_Factory(Provider<MovieRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ToggleWatchlistUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ToggleWatchlistUseCase_Factory create(
      Provider<MovieRepository> repositoryProvider) {
    return new ToggleWatchlistUseCase_Factory(repositoryProvider);
  }

  public static ToggleWatchlistUseCase newInstance(MovieRepository repository) {
    return new ToggleWatchlistUseCase(repository);
  }
}
