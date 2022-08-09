package com.riyusoft.todo.core.data.repository.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 2,
    entities = [TodoEntity::class, TodoGroupEntity::class],
    exportSchema = true
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
