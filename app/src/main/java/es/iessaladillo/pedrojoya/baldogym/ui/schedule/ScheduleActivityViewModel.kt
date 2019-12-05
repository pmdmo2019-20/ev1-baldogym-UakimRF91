package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

class ScheduleActivityViewModel(private val repository: Repository,
                                private val application: Application) : ViewModel() {

    private val _sessions: MutableLiveData<List<TrainingSession>> = MutableLiveData(repository.querySessionsOfMonday())
    val sessions: LiveData<List<TrainingSession>>
        get() = _sessions

    private val _currentDay: MutableLiveData<WeekDay> =
        MutableLiveData(WeekDay.MONDAY)
    val currentDay: LiveData<WeekDay>
        get() = _currentDay

    private val _currentDayTitle: MutableLiveData<String> =
        MutableLiveData(currentDay.value!!.nameResId.toString())
    val currentDayTitle: LiveData<String>
        get() = _currentDayTitle


    // Hace que se muestre en el RecyclerView sólo las sesiones de hoy.
    fun filterMonday() {
        _currentDay.value = WeekDay.MONDAY
        _currentDayTitle.value = currentDay.value!!.nameResId.toString()
        querySessions(_currentDay.value!!)
    }

    fun filterTuesday() {
        _currentDay.value = WeekDay.TUESDAY
        _currentDayTitle.value = currentDay.value!!.nameResId.toString()
        querySessions(_currentDay.value!!)
    }

    fun filterWednesday() {
        _currentDay.value = WeekDay.WEDNESDAY
        _currentDayTitle.value = currentDay.value!!.nameResId.toString()
        querySessions(_currentDay.value!!)
    }

    fun filterThursday() {
        _currentDay.value = WeekDay.THURSDAY
        _currentDayTitle.value = currentDay.value!!.nameResId.toString()
        querySessions(_currentDay.value!!)
    }

    fun filterFriday() {
        _currentDay.value = WeekDay.FRIDAY
        _currentDayTitle.value = currentDay.value!!.nameResId.toString()
        querySessions(_currentDay.value!!)
    }

    fun filterSaturday() {
        _currentDay.value = WeekDay.SATURDAY
        _currentDayTitle.value = currentDay.value!!.nameResId.toString()
        querySessions(_currentDay.value!!)
    }

    fun filterSunday() {
        _currentDay.value = WeekDay.SUNDAY
        _currentDayTitle.value = currentDay.value!!.nameResId.toString()
        querySessions(_currentDay.value!!)
    }

    fun markSessionAsJoined(session: TrainingSession, join: Boolean) {
        if (join) {
            repository.markSessionAsJoined(session.id)
        } else {
            repository.markSessionAsQuitted(session.id)
        }
        querySessions(_currentDay.value!!)
    }

    // Pide las tareas al repositorio, atendiendo al filtro recibido
    private fun querySessions(day: WeekDay) {
        when (day) {
            WeekDay.MONDAY -> _sessions.value = repository.querySessionsOfMonday()
            WeekDay.TUESDAY -> _sessions.value = repository.querySessionsOfTuesday()
            WeekDay.WEDNESDAY -> _sessions.value = repository.querySessionsOfWednesday()
            WeekDay.THURSDAY -> _sessions.value = repository.querySessionsOfThursday()
            WeekDay.FRIDAY -> _sessions.value = repository.querySessionsOfFriday()
            WeekDay.SATURDAY -> _sessions.value = repository.querySessionsOfSaturday()
            WeekDay.SUNDAY -> _sessions.value = repository.querySessionsOfSunday()
        }
    }
}