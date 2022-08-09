package com.riyusoft.todo.core.data.repository.todo.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("BEGIN TRANSACTION")
        database.execSQL(
            " CREATE TABLE IF NOT EXISTS todo_group " +
                " (`name` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT) "
        )
        database.execSQL(" INSERT INTO todo_group VALUES('default',1) ")
        database.execSQL(
            " ALTER TABLE todo ADD COLUMN group_id INTEGER NOT NULL DEFAULT 1 " +
                " REFERENCES todo_group(id) ON UPDATE CASCADE ON DELETE CASCADE "
        )
        database.execSQL(" COMMIT ")
    }
}
