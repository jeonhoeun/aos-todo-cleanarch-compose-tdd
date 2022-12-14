package com.riyusoft.todo.core.data.repository.todo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTodoGroup(todoGroupEntity: TodoGroupEntity)

    @Query("SELECT * FROM todo ORDER BY priority ASC")
    fun getTodoAll(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo WHERE group_id=:groupID ORDER BY priority ASC")
    fun getTodosByGroupId(groupID: Long): Flow<List<TodoEntity>>

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

    @Query("SELECT count(id) FROM TODO where group_id=:groupId")
    fun getTodoCountByGroupId(groupId: Long): Flow<Int>

    @Transaction
    fun moveTodoToTrash(id: Long) {
        val targetTodo = getTodoById(id)
        targetTodo.groupID = 1
        updateTodo(targetTodo)
    }
}
