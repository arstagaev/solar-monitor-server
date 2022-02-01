package com.revolna.database

import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBTodoTable: Table<DBTodoEntity>("test_telemetry") {

    val id  = int("id").primaryKey().bindTo { it.id }
    val data    = text("data").bindTo       { it.data }
    val value   = float("value").bindTo      { it.value }

}

interface DBTodoEntity: Entity<DBTodoEntity> {

    companion object : Entity.Factory<DBTodoEntity>()

    val id: Int
    val data: String
    val value: Float

}