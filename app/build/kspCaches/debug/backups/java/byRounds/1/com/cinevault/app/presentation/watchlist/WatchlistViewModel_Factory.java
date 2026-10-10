package com.cinevault.app.presentation.watchlist;

import com.cinevault.app.domain.usecase.GetWatchlistUseCase;
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
public final class WatchlistViewModel_Factory implements Factory<WatchlistViewModel> {
  private final Provider<GetWatchlistUseCase> getWatchlistProvider;

  private final Provider<ToggleWatchlistUseCase> toggleWatchlistProvider;

  public WatchlistViewModel_Factory(Provider<GetWatchlistUseCase> getWatchlistProvider,
      Provider<ToggleWatchlistUseCase> toggleWatchlistProvider) {
    this.getWatchlistProvider = getWatchlistProvider;
    this.toggleWatchlistProvider = toggleWatchlistProvider;
  }

  @Override
  public WatchlistViewModel get() {
    return newInstance(getWatchlistProvider.get(), toggleWatchlistProvider.get());
  }

  public static WatchlistViewModel_Factory create(
      Provider<GetWatchlistUseCase> getWatchlistProvider,
      Provider<ToggleWatchlistUseCase> toggleWatchlistProvider) {
    return new WatchlistViewModel_Factory(getWatchlistProvider, toggleWatchlistProvider);
  }

  public static WatchlistViewModel newInstance(GetWatchlistUseCase getWatchlist,
      ToggleWatchlistUseCase toggleWatchlist) {
    return new WatchlistViewModel(getWatchlist, toggleWatchlist);
  }
}
