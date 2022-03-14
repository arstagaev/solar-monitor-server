package com.revolna.database

import com.revolna.Keys.DATABASE_NAME_HIDE
import com.revolna.Keys.HOSTNAME_HIDE
import com.revolna.Keys.PASSWORD_HIDE
import com.revolna.Keys.USERNAME_HIDE
import com.revolna.entities.Telemetry
import com.revolna.entities.TelemetryDraft
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {

    // config
    private var hostname     = "localhost"
    private var databaseName = "piz"
    private var username     = "postgres"
    private var password     = "1997"

    //config db remote
    private var rem_hostname     =  HOSTNAME_HIDE
    private var rem_databaseName =  DATABASE_NAME_HIDE
    private var rem_username     =  USERNAME_HIDE
    private var rem_password     =  PASSWORD_HIDE

    //db
    private val ktormDatabase : Database

    init {
        //val jdbcUrl = "jdbc:postgresql://$hostname:5432/$databaseName?user=$username&password=$password&useSSL=false"
        val jdbcUrl = "jdbc:postgresql://$rem_hostname:5432/$rem_databaseName?user=$rem_username&password=$rem_password&useSSL=false"

        // val jdbcURL_REMOTE = "jdbc:postgresql://$hostname:5432/$databaseName?user=$username&password=$password&useSSL=false"


        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllTelemetrys(): List<DBTodoEntity> {
        println("WOWOWOW ${ktormDatabase.from(DBTodoTable).select(DBTodoTable.data).rowSet}")
        ktormDatabase.from(DBTodoTable).select(DBTodoTable.data)
            .forEach { row ->
                println("WOWWOW2 "+row.getString(1))

            }

        return ktormDatabase.sequenceOf(DBTodoTable).toList()
    }

    fun addTelemetry(draft: TelemetryDraft) : Telemetry {
        //var sqle = ktormDatabase.from(DBTodoTable).select().toString()
        println(">>> inputTelemetry: ${draft}")
        ktormDatabase.insert(DBTodoTable) {
            set(DBTodoTable.id, draft.id)
            set(DBTodoTable.data, draft.data)
            set(DBTodoTable.value, draft.value)
        }

        return Telemetry(draft.id, draft.data, draft.value)
    }

    fun removeTelemetry(id : Int) : Boolean {
        val deletedRows = ktormDatabase.delete(DBTodoTable) {
            it.id eq id
        }
        return deletedRows > 0
    }


}