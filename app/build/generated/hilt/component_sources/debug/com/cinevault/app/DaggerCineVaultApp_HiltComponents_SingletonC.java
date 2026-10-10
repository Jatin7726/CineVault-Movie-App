package com.cinevault.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.cinevault.app.data.local.CineVaultDatabase;
import com.cinevault.app.data.local.MovieDao;
import com.cinevault.app.data.local.SessionManager;
import com.cinevault.app.data.remote.AuthInterceptor;
import com.cinevault.app.data.remote.TmdbApi;
import com.cinevault.app.data.repository.AccountRepositoryImpl;
import com.cinevault.app.data.repository.MovieRepositoryImpl;
import com.cinevault.app.di.DataStoreModule_ProvideDataStoreFactory;
import com.cinevault.app.di.DatabaseModule_ProvideDatabaseFactory;
import com.cinevault.app.di.DatabaseModule_ProvideMovieDaoFactory;
import com.cinevault.app.di.NetworkModule_ProvideChuckerInterceptorFactory;
import com.cinevault.app.di.NetworkModule_ProvideJsonFactory;
import com.cinevault.app.di.NetworkModule_ProvideLoggingInterceptorFactory;
import com.cinevault.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.cinevault.app.di.NetworkModule_ProvideRetrofitFactory;
import com.cinevault.app.di.NetworkModule_ProvideTmdbApiFactory;
import com.cinevault.app.domain.usecase.GetMovieDetailUseCase;
import com.cinevault.app.domain.usecase.GetPopularMoviesUseCase;
import com.cinevault.app.domain.usecase.GetTrendingMoviesUseCase;
import com.cinevault.app.domain.usecase.GetWatchlistUseCase;
import com.cinevault.app.domain.usecase.SearchMoviesUseCase;
import com.cinevault.app.domain.usecase.ToggleWatchlistUseCase;
import com.cinevault.app.presentation.detail.DetailViewModel;
import com.cinevault.app.presentation.detail.DetailViewModel_HiltModules;
import com.cinevault.app.presentation.home.HomeViewModel;
import com.cinevault.app.presentation.home.HomeViewModel_HiltModules;
import com.cinevault.app.presentation.profile.ProfileViewModel;
import com.cinevault.app.presentation.profile.ProfileViewModel_HiltModules;
import com.cinevault.app.presentation.search.SearchViewModel;
import com.cinevault.app.presentation.search.SearchViewModel_HiltModules;
import com.cinevault.app.presentation.watchlist.WatchlistViewModel;
import com.cinevault.app.presentation.watchlist.WatchlistViewModel_HiltModules;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

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
public final class DaggerCineVaultApp_HiltComponents_SingletonC {
  private DaggerCineVaultApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public CineVaultApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements CineVaultApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public CineVaultApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements CineVaultApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public CineVaultApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements CineVaultApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public CineVaultApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements CineVaultApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CineVaultApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements CineVaultApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CineVaultApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements CineVaultApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public CineVaultApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements CineVaultApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public CineVaultApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends CineVaultApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends CineVaultApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends CineVaultApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends CineVaultApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(5).put(LazyClassKeyProvider.com_cinevault_app_presentation_detail_DetailViewModel, DetailViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cinevault_app_presentation_home_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cinevault_app_presentation_profile_ProfileViewModel, ProfileViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cinevault_app_presentation_search_SearchViewModel, SearchViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_cinevault_app_presentation_watchlist_WatchlistViewModel, WatchlistViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectAccountRepository(instance, singletonCImpl.accountRepositoryImplProvider.get());
      return instance;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_cinevault_app_presentation_home_HomeViewModel = "com.cinevault.app.presentation.home.HomeViewModel";

      static String com_cinevault_app_presentation_profile_ProfileViewModel = "com.cinevault.app.presentation.profile.ProfileViewModel";

      static String com_cinevault_app_presentation_watchlist_WatchlistViewModel = "com.cinevault.app.presentation.watchlist.WatchlistViewModel";

      static String com_cinevault_app_presentation_search_SearchViewModel = "com.cinevault.app.presentation.search.SearchViewModel";

      static String com_cinevault_app_presentation_detail_DetailViewModel = "com.cinevault.app.presentation.detail.DetailViewModel";

      @KeepFieldType
      HomeViewModel com_cinevault_app_presentation_home_HomeViewModel2;

      @KeepFieldType
      ProfileViewModel com_cinevault_app_presentation_profile_ProfileViewModel2;

      @KeepFieldType
      WatchlistViewModel com_cinevault_app_presentation_watchlist_WatchlistViewModel2;

      @KeepFieldType
      SearchViewModel com_cinevault_app_presentation_search_SearchViewModel2;

      @KeepFieldType
      DetailViewModel com_cinevault_app_presentation_detail_DetailViewModel2;
    }
  }

  private static final class ViewModelCImpl extends CineVaultApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<DetailViewModel> detailViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<SearchViewModel> searchViewModelProvider;

    private Provider<WatchlistViewModel> watchlistViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private GetMovieDetailUseCase getMovieDetailUseCase() {
      return new GetMovieDetailUseCase(singletonCImpl.movieRepositoryImplProvider.get());
    }

    private ToggleWatchlistUseCase toggleWatchlistUseCase() {
      return new ToggleWatchlistUseCase(singletonCImpl.movieRepositoryImplProvider.get());
    }

    private GetTrendingMoviesUseCase getTrendingMoviesUseCase() {
      return new GetTrendingMoviesUseCase(singletonCImpl.movieRepositoryImplProvider.get());
    }

    private GetPopularMoviesUseCase getPopularMoviesUseCase() {
      return new GetPopularMoviesUseCase(singletonCImpl.movieRepositoryImplProvider.get());
    }

    private SearchMoviesUseCase searchMoviesUseCase() {
      return new SearchMoviesUseCase(singletonCImpl.movieRepositoryImplProvider.get());
    }

    private GetWatchlistUseCase getWatchlistUseCase() {
      return new GetWatchlistUseCase(singletonCImpl.movieRepositoryImplProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.detailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.searchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.watchlistViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(5).put(LazyClassKeyProvider.com_cinevault_app_presentation_detail_DetailViewModel, ((Provider) detailViewModelProvider)).put(LazyClassKeyProvider.com_cinevault_app_presentation_home_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_cinevault_app_presentation_profile_ProfileViewModel, ((Provider) profileViewModelProvider)).put(LazyClassKeyProvider.com_cinevault_app_presentation_search_SearchViewModel, ((Provider) searchViewModelProvider)).put(LazyClassKeyProvider.com_cinevault_app_presentation_watchlist_WatchlistViewModel, ((Provider) watchlistViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_cinevault_app_presentation_search_SearchViewModel = "com.cinevault.app.presentation.search.SearchViewModel";

      static String com_cinevault_app_presentation_home_HomeViewModel = "com.cinevault.app.presentation.home.HomeViewModel";

      static String com_cinevault_app_presentation_profile_ProfileViewModel = "com.cinevault.app.presentation.profile.ProfileViewModel";

      static String com_cinevault_app_presentation_watchlist_WatchlistViewModel = "com.cinevault.app.presentation.watchlist.WatchlistViewModel";

      static String com_cinevault_app_presentation_detail_DetailViewModel = "com.cinevault.app.presentation.detail.DetailViewModel";

      @KeepFieldType
      SearchViewModel com_cinevault_app_presentation_search_SearchViewModel2;

      @KeepFieldType
      HomeViewModel com_cinevault_app_presentation_home_HomeViewModel2;

      @KeepFieldType
      ProfileViewModel com_cinevault_app_presentation_profile_ProfileViewModel2;

      @KeepFieldType
      WatchlistViewModel com_cinevault_app_presentation_watchlist_WatchlistViewModel2;

      @KeepFieldType
      DetailViewModel com_cinevault_app_presentation_detail_DetailViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.cinevault.app.presentation.detail.DetailViewModel 
          return (T) new DetailViewModel(viewModelCImpl.savedStateHandle, viewModelCImpl.getMovieDetailUseCase(), viewModelCImpl.toggleWatchlistUseCase());

          case 1: // com.cinevault.app.presentation.home.HomeViewModel 
          return (T) new HomeViewModel(viewModelCImpl.getTrendingMoviesUseCase(), viewModelCImpl.getPopularMoviesUseCase());

          case 2: // com.cinevault.app.presentation.profile.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.accountRepositoryImplProvider.get());

          case 3: // com.cinevault.app.presentation.search.SearchViewModel 
          return (T) new SearchViewModel(viewModelCImpl.searchMoviesUseCase());

          case 4: // com.cinevault.app.presentation.watchlist.WatchlistViewModel 
          return (T) new WatchlistViewModel(viewModelCImpl.getWatchlistUseCase(), viewModelCImpl.toggleWatchlistUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends CineVaultApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends CineVaultApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends CineVaultApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<HttpLoggingInterceptor> provideLoggingInterceptorProvider;

    private Provider<ChuckerInterceptor> provideChuckerInterceptorProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Json> provideJsonProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<TmdbApi> provideTmdbApiProvider;

    private Provider<DataStore<Preferences>> provideDataStoreProvider;

    private Provider<SessionManager> sessionManagerProvider;

    private Provider<AccountRepositoryImpl> accountRepositoryImplProvider;

    private Provider<CineVaultDatabase> provideDatabaseProvider;

    private Provider<MovieDao> provideMovieDaoProvider;

    private Provider<MovieRepositoryImpl> movieRepositoryImplProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideLoggingInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<HttpLoggingInterceptor>(singletonCImpl, 4));
      this.provideChuckerInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<ChuckerInterceptor>(singletonCImpl, 5));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 3));
      this.provideJsonProvider = DoubleCheck.provider(new SwitchingProvider<Json>(singletonCImpl, 6));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 2));
      this.provideTmdbApiProvider = DoubleCheck.provider(new SwitchingProvider<TmdbApi>(singletonCImpl, 1));
      this.provideDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<DataStore<Preferences>>(singletonCImpl, 8));
      this.sessionManagerProvider = DoubleCheck.provider(new SwitchingProvider<SessionManager>(singletonCImpl, 7));
      this.accountRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<AccountRepositoryImpl>(singletonCImpl, 0));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<CineVaultDatabase>(singletonCImpl, 11));
      this.provideMovieDaoProvider = DoubleCheck.provider(new SwitchingProvider<MovieDao>(singletonCImpl, 10));
      this.movieRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<MovieRepositoryImpl>(singletonCImpl, 9));
    }

    @Override
    public void injectCineVaultApp(CineVaultApp cineVaultApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.cinevault.app.data.repository.AccountRepositoryImpl 
          return (T) new AccountRepositoryImpl(singletonCImpl.provideTmdbApiProvider.get(), singletonCImpl.sessionManagerProvider.get());

          case 1: // com.cinevault.app.data.remote.TmdbApi 
          return (T) NetworkModule_ProvideTmdbApiFactory.provideTmdbApi(singletonCImpl.provideRetrofitProvider.get());

          case 2: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideJsonProvider.get());

          case 3: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(new AuthInterceptor(), singletonCImpl.provideLoggingInterceptorProvider.get(), singletonCImpl.provideChuckerInterceptorProvider.get());

          case 4: // okhttp3.logging.HttpLoggingInterceptor 
          return (T) NetworkModule_ProvideLoggingInterceptorFactory.provideLoggingInterceptor();

          case 5: // com.chuckerteam.chucker.api.ChuckerInterceptor 
          return (T) NetworkModule_ProvideChuckerInterceptorFactory.provideChuckerInterceptor(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // kotlinx.serialization.json.Json 
          return (T) NetworkModule_ProvideJsonFactory.provideJson();

          case 7: // com.cinevault.app.data.local.SessionManager 
          return (T) new SessionManager(singletonCImpl.provideDataStoreProvider.get());

          case 8: // androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> 
          return (T) DataStoreModule_ProvideDataStoreFactory.provideDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 9: // com.cinevault.app.data.repository.MovieRepositoryImpl 
          return (T) new MovieRepositoryImpl(singletonCImpl.provideTmdbApiProvider.get(), singletonCImpl.provideMovieDaoProvider.get(), singletonCImpl.sessionManagerProvider.get());

          case 10: // com.cinevault.app.data.local.MovieDao 
          return (T) DatabaseModule_ProvideMovieDaoFactory.provideMovieDao(singletonCImpl.provideDatabaseProvider.get());

          case 11: // com.cinevault.app.data.local.CineVaultDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
