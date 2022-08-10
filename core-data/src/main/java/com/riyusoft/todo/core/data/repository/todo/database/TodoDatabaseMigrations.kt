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
        database.execSQL(" INSERT INTO todo_group VALUES('trash',1) ")
        database.execSQL(" INSERT INTO todo_group VALUES('default',2) ")
        database.execSQL(
            " ALTER TABLE todo ADD COLUMN group_id INTEGER NOT NULL DEFAULT 2 " +
                " REFERENCES todo_group(id) ON UPDATE CASCADE ON DELETE CASCADE "
        )
        database.execSQL(" COMMIT ")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("BEGIN TRANSACTION")
        database.execSQL(
            " CREATE VIEW `TodoGroupView`" +
                " AS SELECT todo_group.id as id," +
                " todo_group.name as name, COUNT(todo.id) as todoCount" +
                " FROM todo_group JOIN todo" +
                " where todo.group_id=todo_group.id" +
                " group by todo_group.id"
        )
        database.execSQL("COMMIT")
    }
}
