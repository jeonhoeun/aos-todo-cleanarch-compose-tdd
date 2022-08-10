package com.riyusoft.todo.core.data.repository.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import com.riyusoft.todo.core.data.repository.todo.database.LocalDatabaseTodoRepository
import com.riyusoft.todo.core.data.repository.todo.database.MIGRATION_1_2
import com.riyusoft.todo.core.data.repository.todo.database.MIGRATION_2_3
import com.riyusoft.todo.core.data.repository.todo.database.TodoDao
import com.riyusoft.todo.core.data.repository.todo.database.TodoDatabase
import com.riyusoft.todo.core.data.repository.todo.database.TodoGroupDao
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
        val INSTANCE: TodoDatabase? = null
        @Provides
        fun getInstance(
            context: Context
        ): TodoDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context,
                TodoDatabase::class.java,
                "todo_database.sqlite.db"
            ).addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch {
                            db.execSQL("INSERT INTO todo_group VALUES('trash',1)")
                            db.execSQL("INSERT INTO todo_group VALUES('default',2)")
                        }
                    }
                }
            ).addMigrations(
                MIGRATION_1_2, MIGRATION_2_3
            ).build()
        }
        @Provides
        fun provideTodoDao(
            @ApplicationContext context: Context
        ): TodoDao {
            return getInstance(context).todoDao()
        }

        @Provides
        fun provideTodoGroupDao(
            @ApplicationContext context: Context
        ): TodoGroupDao {
            return getInstance(context).todoGroupDao()
        }
    }

    @Binds
    fun bindsTodoRepository(
        impl: LocalDatabaseTodoRepository
    ): TodoRepository
}
