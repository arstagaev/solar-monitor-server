package com.revolna.database

import com.revolna.entities.Telemetry
import com.revolna.entities.TelemetryDraft
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {

    // config
    private var hostname = "localhost"
    private var databaseName = "piz"
    private var username = "postgres"
    private var password = "1997"

    //config db remote
    private var rem_hostname     = "ec2-34-247-249-224.eu-west-1.compute.amazonaws.com"
    private var rem_databaseName = "d73vg6mfudbqom"
    private var rem_username     = "eniyufrzgmqwyg"
    private var rem_password     = "11c6f880de0ab0208268eec2b94d3ed5e9fe70c7aaa888bb92f759c7b5739f76"

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