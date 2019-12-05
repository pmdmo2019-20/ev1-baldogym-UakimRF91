package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.ui.trainingsession.TrainingSessionActivity
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleActivityViewModel by viewModels {
        ScheduleActivityViewModelFactory(LocalRepository, application)
    }

    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().also {
        it.onClickListener = { position -> setSessionJoined(position) }
        it.onSessionListener = { position -> goToSession(position) }
    }

    private fun goToSession(position: Int) {
        val session = listAdapter.currentList[position]
        navigateToTrainingSessionActivity(session)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupViews()
        observeViewModelData()
    }

    private fun setupViews() {
        setupRecyclerView()
        schedule_monday.setOnClickListener {
            viewModel.filterMonday()
        }
        schedule_tuesday.setOnClickListener {
            viewModel.filterTuesday()
        }
        schedule_wednesday.setOnClickListener {
            viewModel.filterWednesday()
        }
        schedule_thursday.setOnClickListener {
            viewModel.filterThursday()
        }
        schedule_friday.setOnClickListener {
            viewModel.filterFriday()
        }
        schedule_saturday.setOnClickListener {
            viewModel.filterSaturday()
        }
        schedule_sunday.setOnClickListener {
            viewModel.filterSunday()
        }
    }

    private fun navigateToTrainingSessionActivity(session: TrainingSession) {
        val intent = TrainingSessionActivity.newIntent(this, session)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        lstSessions.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun observeViewModelData() {
        viewModel.sessions.observe(this) {
            showSessions(it)
        }

        viewModel.currentDayTitle.observe(this) {
            schedule_today.text = it
        }
    }

    private fun showSessions(sessions: List<TrainingSession>) {
        lstSessions.post {
            listAdapter.submitList(sessions)
        }
    }

    private fun setSessionJoined(position: Int) {
        val session = listAdapter.currentList[position]
        viewModel.markSessionAsJoined(session, !session.userJoined)
    }

}
