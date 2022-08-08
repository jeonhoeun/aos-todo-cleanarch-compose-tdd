package com.riyusoft.todo.core.data.repository.di

import android.content.Context
import androidx.room.Room
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.data.repository.todo.database.LocalDatabaseTodoRepository
import com.riyusoft.todo.core.data.repository.todo.database.TodoDao
import com.riyusoft.todo.core.data.repository.todo.database.TodoDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    companion object {
        @Provides
        fun provideTodoDao(
            @ApplicationContext context: Context
        ): TodoDao {
            println("testtest provideTodoDao")
            return Room.databaseBuilder(
                context,
                TodoDatabase::class.java,
                "todo_database.sqlite.db"
            ).build().todoDao()
        }
    }

    @Binds
    fun bindsTodoRepository(
        impl: LocalDatabaseTodoRepository
    ): TodoRepository

//    @Binds
//    fun bindsTodoRepository(
//        impl: LocalMemoryTodoRepository
//    ): TodoRepository
}
