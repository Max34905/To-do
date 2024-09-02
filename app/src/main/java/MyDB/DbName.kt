package MyDB

import android.provider.BaseColumns

object DbName : BaseColumns {
    const val TABLE_NAME = "todoList"

    const val TITLE_COLUMN_NAME = "title"
    const val TODO_COLUMN_NAME = "todo"

    const val DATABASE_NAME = "todoList.db"
    const val DATABASE_VERSION = 1

    const val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$TITLE_COLUMN_NAME TEXT," +
                "$TODO_COLUMN_NAME TEXT)"

    const val DELETE_DATABASE = "DROP TABLE IF EXISTS $TABLE_NAME"
    const val DELETE_DATA = "DELETE FROM $TABLE_NAME"
}