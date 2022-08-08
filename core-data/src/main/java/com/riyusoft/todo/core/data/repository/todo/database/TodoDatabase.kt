package com.riyusoft.todo.core.data.repository.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                return Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}