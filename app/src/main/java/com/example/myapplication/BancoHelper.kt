import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "senhas.db"
        private const val DATABASE_VERSION = 1

        // Nomes das tabelas e colunas
        const val TABLE_NAME = "senhas"
        const val COLUMN_ID = "id"
        const val COLUMN_DESCRICAO = "descricao"
        const val COLUMN_SENHA = "senha"
        const val COLUMN_DATA_CRI = "data_cri"
        const val COLUMN_DATA_ATT = "data_att"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_DESCRICAO TEXT," +
                "$COLUMN_SENHA TEXT," +
                "$COLUMN_DATA_CRI INTEGER," +
                "$COLUMN_DATA_ATT INTEGER" +
                ")"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}