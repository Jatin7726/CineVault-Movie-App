package com.cinevault.app.di;

import com.cinevault.app.data.local.CineVaultDatabase;
import com.cinevault.app.data.local.MovieDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideMovieDaoFactory implements Factory<MovieDao> {
  private final Provider<CineVaultDatabase> databaseProvider;

  public DatabaseModule_ProvideMovieDaoFactory(Provider<CineVaultDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MovieDao get() {
    return provideMovieDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideMovieDaoFactory create(
      Provider<CineVaultDatabase> databaseProvider) {
    return new DatabaseModule_ProvideMovieDaoFactory(databaseProvider);
  }

  public static MovieDao provideMovieDao(CineVaultDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMovieDao(database));
  }
}
