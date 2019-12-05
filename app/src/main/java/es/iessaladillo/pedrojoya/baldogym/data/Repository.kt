package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

interface Repository {

    fun markSessionAsJoined(id: Long)
    fun markSessionAsQuitted(id: Long)

    fun querySessionsOfMonday(): List<TrainingSession>
    fun querySessionsOfTuesday(): List<TrainingSession>
    fun querySessionsOfWednesday(): List<TrainingSession>
    fun querySessionsOfThursday(): List<TrainingSession>
    fun querySessionsOfFriday(): List<TrainingSession>
    fun querySessionsOfSaturday(): List<TrainingSession>
    fun querySessionsOfSunday(): List<TrainingSession>
}