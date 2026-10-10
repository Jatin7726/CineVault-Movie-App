package com.cinevault.app.presentation.detail;

import androidx.lifecycle.SavedStateHandle;
import com.cinevault.app.domain.usecase.GetMovieDetailUseCase;
import com.cinevault.app.domain.usecase.ToggleWatchlistUseCase;
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
public final class DetailViewModel_Factory implements Factory<DetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetMovieDetailUseCase> getMovieDetailProvider;

  private final Provider<ToggleWatchlistUseCase> toggleWatchlistProvider;

  public DetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetMovieDetailUseCase> getMovieDetailProvider,
      Provider<ToggleWatchlistUseCase> toggleWatchlistProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getMovieDetailProvider = getMovieDetailProvider;
    this.toggleWatchlistProvider = toggleWatchlistProvider;
  }

  @Override
  public DetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getMovieDetailProvider.get(), toggleWatchlistProvider.get());
  }

  public static DetailViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetMovieDetailUseCase> getMovieDetailProvider,
      Provider<ToggleWatchlistUseCase> toggleWatchlistProvider) {
    return new DetailViewModel_Factory(savedStateHandleProvider, getMovieDetailProvider, toggleWatchlistProvider);
  }

  public static DetailViewModel newInstance(SavedStateHandle savedStateHandle,
      GetMovieDetailUseCase getMovieDetail, ToggleWatchlistUseCase toggleWatchlist) {
    return new DetailViewModel(savedStateHandle, getMovieDetail, toggleWatchlist);
  }
}
