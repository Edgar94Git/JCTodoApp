package com.ereyes.jctodoapp.addtasks.core.di

import android.content.Context
import androidx.room.Room
import com.ereyes.jctodoapp.addtasks.data.database.JcTodoAppDataBase
import com.ereyes.jctodoapp.addtasks.data.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.core.di
 * Created by Edgar Reyes Gonzalez on 11/25/2023 at 8:47 AM
 * All rights reserved 2023.
 ****/

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideTaskDao(dataBase: JcTodoAppDataBase): TaskDao{
        return dataBase.getTaskDao()
    }

    @Provides
    @Singleton
    fun provideJcTodoAppDataBase(@ApplicationContext context: Context): JcTodoAppDataBase{
        return Room.databaseBuilder(context, JcTodoAppDataBase::class.java, "BdJcTodoApp").build()
    }
}