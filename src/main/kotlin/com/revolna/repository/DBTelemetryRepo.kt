package com.revolna.repository

import com.revolna.database.DatabaseManager
import com.revolna.entities.Telemetry
import com.revolna.entities.TelemetryDraft

class DBTelemetryRepo : TelemetryRepository {

    private val database = DatabaseManager()


    override fun getAllTelemetry(): List<Telemetry> {
        return database.getAllTelemetrys()
            .map { Telemetry(it.id, it.data, it.value) }
    }

    override fun getTelemetry(id: Int): Telemetry? {
        TODO("Not yet implemented")
    }

    override fun addTelemetry(draft: TelemetryDraft): Telemetry {
        return database.addTelemetry(draft)
    }

    override fun removeTelemetry(id: Int): Boolean {
        return database.removeTelemetry(id)
    }

    override fun updateTelemetry(id: Int, draft: TelemetryDraft): Boolean {
        TODO("Not yet implemented")
    }
}