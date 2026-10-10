package com.cinevault.app.data.repository;

import com.cinevault.app.data.local.MovieDao;
import com.cinevault.app.data.local.SessionManager;
import com.cinevault.app.data.remote.TmdbApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class MovieRepositoryImpl_Factory implements Factory<MovieRepositoryImpl> {
  private final Provider<TmdbApi> apiProvider;

  private final Provider<MovieDao> daoProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public MovieRepositoryImpl_Factory(Provider<TmdbApi> apiProvider, Provider<MovieDao> daoProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public MovieRepositoryImpl get() {
    return newInstance(apiProvider.get(), daoProvider.get(), sessionManagerProvider.get());
  }

  public static MovieRepositoryImpl_Factory create(Provider<TmdbApi> apiProvider,
      Provider<MovieDao> daoProvider, Provider<SessionManager> sessionManagerProvider) {
    return new MovieRepositoryImpl_Factory(apiProvider, daoProvider, sessionManagerProvider);
  }

  public static MovieRepositoryImpl newInstance(TmdbApi api, MovieDao dao,
      SessionManager sessionManager) {
    return new MovieRepositoryImpl(api, dao, sessionManager);
  }
}
