package com.cinevault.app;

import com.cinevault.app.domain.repository.AccountRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<AccountRepository> accountRepositoryProvider;

  public MainActivity_MembersInjector(Provider<AccountRepository> accountRepositoryProvider) {
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<AccountRepository> accountRepositoryProvider) {
    return new MainActivity_MembersInjector(accountRepositoryProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectAccountRepository(instance, accountRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.cinevault.app.MainActivity.accountRepository")
  public static void injectAccountRepository(MainActivity instance,
      AccountRepository accountRepository) {
    instance.accountRepository = accountRepository;
  }
}
