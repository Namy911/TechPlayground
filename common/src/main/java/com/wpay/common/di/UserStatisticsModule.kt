package com.wpay.common.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.wpay.common.data.database.UserDatabase
import com.wpay.common.data.database.dao.UserDao
import com.wpay.common.data.datastore.UserPreferencesRepositoryImp
import com.wpay.common.data.datastore.UserPreferencesSerializer
import com.wpay.common.data.repository.UserRepositoryImp
import com.wpay.common.domain.UserRepository
import com.wpay.common.domain.model.UserPreferences
import com.wpay.common.domain.repository.UserPreferencesRepository
import com.wpay.common.util.DefaultDispatchers
import com.wpay.common.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserStatisticsModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            "app_database"
        ).build()
    }

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
    @Singleton
    fun provideUserPreferencesDataStore(@ApplicationContext context: Context): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = UserPreferencesSerializer,
            produceFile = { context.dataStoreFile("user_prefs.pb") }
        )
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(dataStore: DataStore<UserPreferences>): UserPreferencesRepository {
        return UserPreferencesRepositoryImp(dataStore)
    }
}