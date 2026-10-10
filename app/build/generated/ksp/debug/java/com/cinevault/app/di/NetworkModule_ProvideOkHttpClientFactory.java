package com.cinevault.app.di;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.cinevault.app.data.remote.AuthInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<AuthInterceptor> authInterceptorProvider;

  private final Provider<HttpLoggingInterceptor> loggingInterceptorProvider;

  private final Provider<ChuckerInterceptor> chuckerInterceptorProvider;

  public NetworkModule_ProvideOkHttpClientFactory(Provider<AuthInterceptor> authInterceptorProvider,
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<ChuckerInterceptor> chuckerInterceptorProvider) {
    this.authInterceptorProvider = authInterceptorProvider;
    this.loggingInterceptorProvider = loggingInterceptorProvider;
    this.chuckerInterceptorProvider = chuckerInterceptorProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(authInterceptorProvider.get(), loggingInterceptorProvider.get(), chuckerInterceptorProvider.get());
  }

  public static NetworkModule_ProvideOkHttpClientFactory create(
      Provider<AuthInterceptor> authInterceptorProvider,
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<ChuckerInterceptor> chuckerInterceptorProvider) {
    return new NetworkModule_ProvideOkHttpClientFactory(authInterceptorProvider, loggingInterceptorProvider, chuckerInterceptorProvider);
  }

  public static OkHttpClient provideOkHttpClient(AuthInterceptor authInterceptor,
      HttpLoggingInterceptor loggingInterceptor, ChuckerInterceptor chuckerInterceptor) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClient(authInterceptor, loggingInterceptor, chuckerInterceptor));
  }
}
