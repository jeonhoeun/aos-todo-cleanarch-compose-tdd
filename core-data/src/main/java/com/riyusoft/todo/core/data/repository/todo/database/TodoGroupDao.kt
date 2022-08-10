package com.riyusoft.todo.core.data.repository.todo.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoGroupDao {

    @Query("SELECT * FROM todo_group")
    fun getTodoGroupsAll(): Flow<List<TodoGroupEntity>>

    @Query("SELECT * FROM TodoGroupView")
    fun getView(): Flow<List<TodoGroupView>>

    @Query(
        "SELECT todo_group.id as id, todo_group.name as name, COUNT(todo.id) as todoCount " +
            " FROM todo_group " +
            " JOIN todo " +
            " where todo.group_id=todo_group.id " +
            " group by todo_group.id "
    )
    fun getTodoGroups(): Flow<List<TodoGroupView>>
}
