package com.wpay.core.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.wpay.core.data.database.UserDatabase
import com.wpay.core.data.database.dao.AppointmentDao
import com.wpay.core.data.database.dao.PrescriptionDao
import com.wpay.core.data.database.dao.UserDao
import com.wpay.core.data.datastore.UserPreferencesRepositoryImp
import com.wpay.core.data.datastore.UserPreferencesSerializer
import com.wpay.core.data.repository.ConsultationRepositoryImp
import com.wpay.core.data.repository.PrescriptionRepositoryImp
import com.wpay.core.data.repository.UserRepositoryImp
import com.wpay.core.domain.repository.UserRepository
import com.wpay.core.domain.model.UserPreferences
import com.wpay.core.domain.repository.ConsultationRepository
import com.wpay.core.domain.repository.PrescriptionRepository
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
    fun providePrescriptionDao(database: UserDatabase): PrescriptionDao {
        return database.prescriptionDao()
    }

    @Provides
    @Singleton
    fun provideAppointmentDao(database: UserDatabase): AppointmentDao {
        return database.appointmentDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImp(userDao)
    }

    @Provides
    fun provideConsultationRepository(dao: AppointmentDao): ConsultationRepository {
        return ConsultationRepositoryImp(dao)
    }

    @Provides
    fun providePrescriptionRepository(dao: PrescriptionDao): PrescriptionRepository {
        return PrescriptionRepositoryImp(dao)
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