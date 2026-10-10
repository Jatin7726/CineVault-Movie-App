package com.cinevault.app.presentation.profile;

import com.cinevault.app.domain.repository.AccountRepository;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<AccountRepository> accountRepositoryProvider;

  public ProfileViewModel_Factory(Provider<AccountRepository> accountRepositoryProvider) {
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(accountRepositoryProvider.get());
  }

  public static ProfileViewModel_Factory create(
      Provider<AccountRepository> accountRepositoryProvider) {
    return new ProfileViewModel_Factory(accountRepositoryProvider);
  }

  public static ProfileViewModel newInstance(AccountRepository accountRepository) {
    return new ProfileViewModel(accountRepository);
  }
}
