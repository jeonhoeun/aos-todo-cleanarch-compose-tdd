package com.riyusoft.todo.core.data.repository.di

import com.riyusoft.todo.core.data.repository.todo.LocalTodoRepository
import com.riyusoft.todo.core.data.repository.todo.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsTodoRepository(
        impl: LocalTodoRepository
    ): TodoRepository
}
