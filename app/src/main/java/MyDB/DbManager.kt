package MyDB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.ScrollView
import android.widget.TextView

class DbManager(context: Context) {
    val dbHelper = DbHelper(context)
    lateinit var db: SQLiteDatabase

    fun openDb() {
        db = dbHelper.writableDatabase
    }

    fun writeToTable(title: String, todo: String) {
        val values = ContentValues().apply {
            put(DbName.TITLE_COLUMN_NAME, title)
            put(DbName.TODO_COLUMN_NAME, todo)
        }
        val newRowId = db.insert(DbName.TABLE_NAME, null, values)
    }

    fun readDbData(): ArrayList<DataContainer> {
        val dataList = ArrayList<DataContainer>()
        val cursor = db.query(DbName.TABLE_NAME, null, null, null, null, null, null)
        val columnIndexTitle = cursor?.getColumnIndex(DbName.TITLE_COLUMN_NAME)!!
        val columnIndexTodo = cursor.getColumnIndex(DbName.TODO_COLUMN_NAME)
        val columnIndexId = cursor.getColumnIndex("_id")
        while (cursor.moveToNext()) {
            val title = cursor.getString(columnIndexTitle)
            val todo = cursor.getString(columnIndexTodo)
            val id = cursor.getString(columnIndexId)
            dataList.add(DataContainer(title, todo, id))
        }
        cursor.close()
        return dataList
    }

    fun showInfo(textForm: TextView) {
        textForm.text = ""
        val dataList = readDbData()
        for (item in dataList) {
            textForm.append("ID: ${item.id}\nTitle: ${item.title}\nTo do: ${item.todo}\n")
        }
    }

    fun clearData() {
        dbHelper.clearData(db)
    }

    fun closeDb() {
        dbHelper.close()
    }
}