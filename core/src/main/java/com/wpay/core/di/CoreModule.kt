package com.wpay.core.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.wpay.core.data.database.UserDatabase
import com.wpay.core.data.database.dao.UserDao
import com.wpay.core.data.datastore.UserPreferencesRepositoryImp
import com.wpay.core.data.datastore.UserPreferencesSerializer
import com.wpay.core.data.repository.UserRepositoryImp
import com.wpay.core.domain.UserRepository
import com.wpay.core.domain.model.UserPreferences
import com.wpay.core.domain.repository.UserPreferencesRepository
import com.wpay.core.ui.Constants
import com.wpay.core.util.DefaultDispatchers
import com.wpay.core.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @DatabaseName
    fun provideDatabaseName(): String = Constants.DATABASE_NAME

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        @DatabaseName dbName: String,
    ): UserDatabase = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        dbName
    ).build()

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }


    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImp(userDao)
    }

    @Provides
    fun provideDispatchers(): DispatcherProvider {
        return DefaultDispatchers()
    }

    @Provides
    @DataStoreFileName
    fun provideDataStoreFileName(): String = Constants.DATASTORE_FILE_NAME

    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @DataStoreFileName fileName: String,
    ): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = UserPreferencesSerializer,
            produceFile = { context.dataStoreFile(fileName) }
        )
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(dataStore: DataStore<UserPreferences>): UserPreferencesRepository {
        return UserPreferencesRepositoryImp(dataStore)
    }
}