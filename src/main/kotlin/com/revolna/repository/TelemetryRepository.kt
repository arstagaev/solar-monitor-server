package com.revolna.repository

import com.revolna.entities.Telemetry
import com.revolna.entities.TelemetryDraft

interface TelemetryRepository {
    fun getAllTelemetry() : List<Telemetry>

    fun getTelemetry(id : Int): Telemetry?

    fun addTelemetry(draft : TelemetryDraft) : Telemetry

    fun removeTelemetry(id : Int) : Boolean

    fun updateTelemetry(id : Int, draft: TelemetryDraft) : Boolean
}