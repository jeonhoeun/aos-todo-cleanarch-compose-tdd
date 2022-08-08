package com.riyusoft.todo.core.data.repository.todo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo ORDER BY priority ASC")
    fun getTodoAll(): Flow<List<TodoEntity>>

    @Query("SELECT MAX(rowid) FROM todo")
    fun getTodoMaxRowId(): Long

    @Insert
    fun innerInsert(todo: TodoEntity): Long

    @Transaction
    fun insertTodo(todo: TodoEntity): Long {
        val maxRowId = getTodoMaxRowId()
        todo.priority = maxRowId + 1L
        return innerInsert(todo)
    }

    @Update
    fun updateTodo(todo: TodoEntity): Int

    @Query("SELECT * FROM todo WHERE id=:id")
    fun getTodoById(id: Long): TodoEntity
}
