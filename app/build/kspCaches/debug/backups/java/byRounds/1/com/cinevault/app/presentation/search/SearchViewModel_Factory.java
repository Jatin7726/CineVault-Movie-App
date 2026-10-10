package com.cinevault.app.presentation.search;

import com.cinevault.app.domain.usecase.SearchMoviesUseCase;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<SearchMoviesUseCase> searchMoviesProvider;

  public SearchViewModel_Factory(Provider<SearchMoviesUseCase> searchMoviesProvider) {
    this.searchMoviesProvider = searchMoviesProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(searchMoviesProvider.get());
  }

  public static SearchViewModel_Factory create(Provider<SearchMoviesUseCase> searchMoviesProvider) {
    return new SearchViewModel_Factory(searchMoviesProvider);
  }

  public static SearchViewModel newInstance(SearchMoviesUseCase searchMovies) {
    return new SearchViewModel(searchMovies);
  }
}
