package com.cinevault.app.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.cinevault.app.data.local.entity.MovieEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDao_Impl implements MovieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MovieEntity> __insertionAdapterOfMovieEntity;

  private final EntityDeletionOrUpdateAdapter<MovieEntity> __updateAdapterOfMovieEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearPopular;

  public MovieDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieEntity = new EntityInsertionAdapter<MovieEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `movies` (`id`,`title`,`overview`,`posterPath`,`backdropPath`,`releaseDate`,`voteAverage`,`isInWatchlist`,`isTrending`,`isPopular`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getOverview());
        if (entity.getPosterPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPosterPath());
        }
        if (entity.getBackdropPath() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBackdropPath());
        }
        statement.bindString(6, entity.getReleaseDate());
        statement.bindDouble(7, entity.getVoteAverage());
        final int _tmp = entity.isInWatchlist() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isTrending() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        final int _tmp_2 = entity.isPopular() ? 1 : 0;
        statement.bindLong(10, _tmp_2);
        statement.bindLong(11, entity.getCachedAt());
      }
    };
    this.__updateAdapterOfMovieEntity = new EntityDeletionOrUpdateAdapter<MovieEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `movies` SET `id` = ?,`title` = ?,`overview` = ?,`posterPath` = ?,`backdropPath` = ?,`releaseDate` = ?,`voteAverage` = ?,`isInWatchlist` = ?,`isTrending` = ?,`isPopular` = ?,`cachedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getOverview());
        if (entity.getPosterPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPosterPath());
        }
        if (entity.getBackdropPath() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBackdropPath());
        }
        statement.bindString(6, entity.getReleaseDate());
        statement.bindDouble(7, entity.getVoteAverage());
        final int _tmp = entity.isInWatchlist() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isTrending() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        final int _tmp_2 = entity.isPopular() ? 1 : 0;
        statement.bindLong(10, _tmp_2);
        statement.bindLong(11, entity.getCachedAt());
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfClearPopular = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM movies WHERE isPopular = 1";
        return _query;
      }
    };
  }

  @Override
  public Object upsertAll(final List<MovieEntity> movies,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovieEntity.insert(movies);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final MovieEntity movie, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMovieEntity.handle(movie);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearPopular(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearPopular.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearPopular.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MovieEntity>> observeTrending() {
    final String _sql = "SELECT * FROM movies WHERE isTrending = 1 ORDER BY cachedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"movies"}, new Callable<List<MovieEntity>>() {
      @Override
      @NonNull
      public List<MovieEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfIsInWatchlist = CursorUtil.getColumnIndexOrThrow(_cursor, "isInWatchlist");
          final int _cursorIndexOfIsTrending = CursorUtil.getColumnIndexOrThrow(_cursor, "isTrending");
          final int _cursorIndexOfIsPopular = CursorUtil.getColumnIndexOrThrow(_cursor, "isPopular");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<MovieEntity> _result = new ArrayList<MovieEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MovieEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpOverview;
            _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            final String _tmpPosterPath;
            if (_cursor.isNull(_cursorIndexOfPosterPath)) {
              _tmpPosterPath = null;
            } else {
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            }
            final String _tmpBackdropPath;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdropPath = null;
            } else {
              _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            }
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final boolean _tmpIsInWatchlist;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsInWatchlist);
            _tmpIsInWatchlist = _tmp != 0;
            final boolean _tmpIsTrending;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsTrending);
            _tmpIsTrending = _tmp_1 != 0;
            final boolean _tmpIsPopular;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPopular);
            _tmpIsPopular = _tmp_2 != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new MovieEntity(_tmpId,_tmpTitle,_tmpOverview,_tmpPosterPath,_tmpBackdropPath,_tmpReleaseDate,_tmpVoteAverage,_tmpIsInWatchlist,_tmpIsTrending,_tmpIsPopular,_tmpCachedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public PagingSource<Integer, MovieEntity> pagingSourcePopular() {
    final String _sql = "SELECT * FROM movies WHERE isPopular = 1 ORDER BY cachedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new LimitOffsetPagingSource<MovieEntity>(_statement, __db, "movies") {
      @Override
      @NonNull
      protected List<MovieEntity> convertRows(@NonNull final Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
        final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(cursor, "overview");
        final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(cursor, "posterPath");
        final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(cursor, "backdropPath");
        final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(cursor, "releaseDate");
        final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(cursor, "voteAverage");
        final int _cursorIndexOfIsInWatchlist = CursorUtil.getColumnIndexOrThrow(cursor, "isInWatchlist");
        final int _cursorIndexOfIsTrending = CursorUtil.getColumnIndexOrThrow(cursor, "isTrending");
        final int _cursorIndexOfIsPopular = CursorUtil.getColumnIndexOrThrow(cursor, "isPopular");
        final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(cursor, "cachedAt");
        final List<MovieEntity> _result = new ArrayList<MovieEntity>(cursor.getCount());
        while (cursor.moveToNext()) {
          final MovieEntity _item;
          final int _tmpId;
          _tmpId = cursor.getInt(_cursorIndexOfId);
          final String _tmpTitle;
          _tmpTitle = cursor.getString(_cursorIndexOfTitle);
          final String _tmpOverview;
          _tmpOverview = cursor.getString(_cursorIndexOfOverview);
          final String _tmpPosterPath;
          if (cursor.isNull(_cursorIndexOfPosterPath)) {
            _tmpPosterPath = null;
          } else {
            _tmpPosterPath = cursor.getString(_cursorIndexOfPosterPath);
          }
          final String _tmpBackdropPath;
          if (cursor.isNull(_cursorIndexOfBackdropPath)) {
            _tmpBackdropPath = null;
          } else {
            _tmpBackdropPath = cursor.getString(_cursorIndexOfBackdropPath);
          }
          final String _tmpReleaseDate;
          _tmpReleaseDate = cursor.getString(_cursorIndexOfReleaseDate);
          final double _tmpVoteAverage;
          _tmpVoteAverage = cursor.getDouble(_cursorIndexOfVoteAverage);
          final boolean _tmpIsInWatchlist;
          final int _tmp;
          _tmp = cursor.getInt(_cursorIndexOfIsInWatchlist);
          _tmpIsInWatchlist = _tmp != 0;
          final boolean _tmpIsTrending;
          final int _tmp_1;
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTrending);
          _tmpIsTrending = _tmp_1 != 0;
          final boolean _tmpIsPopular;
          final int _tmp_2;
          _tmp_2 = cursor.getInt(_cursorIndexOfIsPopular);
          _tmpIsPopular = _tmp_2 != 0;
          final long _tmpCachedAt;
          _tmpCachedAt = cursor.getLong(_cursorIndexOfCachedAt);
          _item = new MovieEntity(_tmpId,_tmpTitle,_tmpOverview,_tmpPosterPath,_tmpBackdropPath,_tmpReleaseDate,_tmpVoteAverage,_tmpIsInWatchlist,_tmpIsTrending,_tmpIsPopular,_tmpCachedAt);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  @Override
  public Flow<List<MovieEntity>> observeWatchlist() {
    final String _sql = "SELECT * FROM movies WHERE isInWatchlist = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"movies"}, new Callable<List<MovieEntity>>() {
      @Override
      @NonNull
      public List<MovieEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfIsInWatchlist = CursorUtil.getColumnIndexOrThrow(_cursor, "isInWatchlist");
          final int _cursorIndexOfIsTrending = CursorUtil.getColumnIndexOrThrow(_cursor, "isTrending");
          final int _cursorIndexOfIsPopular = CursorUtil.getColumnIndexOrThrow(_cursor, "isPopular");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<MovieEntity> _result = new ArrayList<MovieEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MovieEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpOverview;
            _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            final String _tmpPosterPath;
            if (_cursor.isNull(_cursorIndexOfPosterPath)) {
              _tmpPosterPath = null;
            } else {
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            }
            final String _tmpBackdropPath;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdropPath = null;
            } else {
              _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            }
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final boolean _tmpIsInWatchlist;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsInWatchlist);
            _tmpIsInWatchlist = _tmp != 0;
            final boolean _tmpIsTrending;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsTrending);
            _tmpIsTrending = _tmp_1 != 0;
            final boolean _tmpIsPopular;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPopular);
            _tmpIsPopular = _tmp_2 != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new MovieEntity(_tmpId,_tmpTitle,_tmpOverview,_tmpPosterPath,_tmpBackdropPath,_tmpReleaseDate,_tmpVoteAverage,_tmpIsInWatchlist,_tmpIsTrending,_tmpIsPopular,_tmpCachedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final int movieId, final Continuation<? super MovieEntity> $completion) {
    final String _sql = "SELECT * FROM movies WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MovieEntity>() {
      @Override
      @Nullable
      public MovieEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfIsInWatchlist = CursorUtil.getColumnIndexOrThrow(_cursor, "isInWatchlist");
          final int _cursorIndexOfIsTrending = CursorUtil.getColumnIndexOrThrow(_cursor, "isTrending");
          final int _cursorIndexOfIsPopular = CursorUtil.getColumnIndexOrThrow(_cursor, "isPopular");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final MovieEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpOverview;
            _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            final String _tmpPosterPath;
            if (_cursor.isNull(_cursorIndexOfPosterPath)) {
              _tmpPosterPath = null;
            } else {
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            }
            final String _tmpBackdropPath;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdropPath = null;
            } else {
              _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            }
            final String _tmpReleaseDate;
            _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final boolean _tmpIsInWatchlist;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsInWatchlist);
            _tmpIsInWatchlist = _tmp != 0;
            final boolean _tmpIsTrending;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsTrending);
            _tmpIsTrending = _tmp_1 != 0;
            final boolean _tmpIsPopular;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPopular);
            _tmpIsPopular = _tmp_2 != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _result = new MovieEntity(_tmpId,_tmpTitle,_tmpOverview,_tmpPosterPath,_tmpBackdropPath,_tmpReleaseDate,_tmpVoteAverage,_tmpIsInWatchlist,_tmpIsTrending,_tmpIsPopular,_tmpCachedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
