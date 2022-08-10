package com.riyusoft.todo.core.data

import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.riyusoft.todo.core.data.repository.todo.database.MIGRATION_1_2
import com.riyusoft.todo.core.data.repository.todo.database.MIGRATION_2_3
import com.riyusoft.todo.core.data.repository.todo.database.TodoDatabase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    private val dbName = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        TodoDatabase::class.java
    )

    @Test
    fun migrate1To2() {
        helper.createDatabase(dbName, 1).apply {
            execSQL("INSERT INTO todo VALUES(1,'title1','desc1',1);")
            execSQL("INSERT INTO todo VALUES(1,'title2','desc2',2);")
            execSQL("INSERT INTO todo VALUES(1,'title3','desc3',3);")
            close()
        }

        helper.runMigrationsAndValidate(dbName, 2, true, MIGRATION_1_2)
    }

    @Test
    fun migrate2To3() {
        helper.createDatabase(dbName, 2).apply {
            execSQL("INSERT INTO todo_group VALUES('default',1);")
            execSQL("INSERT INTO todo VALUES(1,'title1','desc1',1,1)")
            execSQL("INSERT INTO todo VALUES(1,'title2','desc2',1,2)")
            execSQL("INSERT INTO todo VALUES(1,'title3','desc3',1,3)")
            close()
        }

        helper.runMigrationsAndValidate(dbName, 3, true, MIGRATION_2_3)
    }
}
