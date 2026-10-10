package com.cinevault.app.data.repository;

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
public final class AccountRepositoryImpl_Factory implements Factory<AccountRepositoryImpl> {
  private final Provider<TmdbApi> apiProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public AccountRepositoryImpl_Factory(Provider<TmdbApi> apiProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.apiProvider = apiProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public AccountRepositoryImpl get() {
    return newInstance(apiProvider.get(), sessionManagerProvider.get());
  }

  public static AccountRepositoryImpl_Factory create(Provider<TmdbApi> apiProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new AccountRepositoryImpl_Factory(apiProvider, sessionManagerProvider);
  }

  public static AccountRepositoryImpl newInstance(TmdbApi api, SessionManager sessionManager) {
    return new AccountRepositoryImpl(api, sessionManager);
  }
}
