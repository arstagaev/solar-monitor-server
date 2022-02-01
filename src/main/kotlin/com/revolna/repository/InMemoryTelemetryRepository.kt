package com.revolna.repository

import com.revolna.entities.Telemetry
import com.revolna.entities.TelemetryDraft

class InMemoryTelemetryRepository : TelemetryRepository {

    private var telemetrys = mutableListOf<Telemetry>()

    override fun getAllTelemetry(): List<Telemetry> {
        return telemetrys
    }

    override fun getTelemetry(id: Int): Telemetry? {
        return telemetrys.firstOrNull { it.id == id }
    }

    override fun addTelemetry(draft: TelemetryDraft): Telemetry {
        var telemetry = Telemetry(
            id = telemetrys.size + 1,
            data = draft.data,
            value = draft.value
        )
        telemetrys.add(telemetry)
        return telemetry
    }

    override fun removeTelemetry(id: Int): Boolean {
        return telemetrys.removeIf { it.id == id }
    }

    override fun updateTelemetry(id: Int, draft: TelemetryDraft): Boolean {
        var telemetry = telemetrys.firstOrNull { it.id == id } ?: return false
        telemetry.data = draft.data
        telemetry.value = draft.value
        return true
    }
}