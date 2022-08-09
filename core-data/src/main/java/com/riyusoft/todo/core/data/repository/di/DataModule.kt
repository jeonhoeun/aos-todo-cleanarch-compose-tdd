package com.riyusoft.todo.core.data.repository.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.data.repository.todo.database.LocalDatabaseTodoRepository
import com.riyusoft.todo.core.data.repository.todo.database.MIGRATION_1_2
import com.riyusoft.todo.core.data.repository.todo.database.TodoDao
import com.riyusoft.todo.core.data.repository.todo.database.TodoDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            ).addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch {
                            db.execSQL("INSERT INTO todo_group VALUES('default',1)")
                        }
                    }
                }
            ).addMigrations(
                MIGRATION_1_2
            ).build().todoDao()
        }
    }

    @Binds
    fun bindsTodoRepository(
        impl: LocalDatabaseTodoRepository
    ): TodoRepository
}
