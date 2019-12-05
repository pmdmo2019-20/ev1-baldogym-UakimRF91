package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.ui.schedule.ScheduleActivity
import kotlinx.android.synthetic.main.training_session_activity.*

class TrainingSessionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
        setupViews()
    }

    private fun setupViews() {
        setSelectedSession(intent.getParcelableExtra<TrainingSession>(EXTRA_SESSION))
        btn_join.setOnClickListener {  }
    }

    private fun setSelectedSession(session: TrainingSession?) {
        session.run {
            imgSession.setImageResource(this!!.photoResId)
            lbl_name_session.text = this!!.name
            lbl_trainer_session.text = trainer
            lbl_day_session.text = weekDay.name
            lbl_time_session.text = time
            lbl_room_session.text = room
            lbl_description_session.text = description
            lbl_participants_session.text = participants.toString()
            if (userJoined) {
                btn_join.text = R.string.schedule_item_quit.toString()
            } else {
                btn_join.text = R.string.schedule_item_join.toString()
            }

        }
    }

    companion object {
        const val EXTRA_SESSION = "EXTRA_SESSION"
        fun newIntent(context: Context, session: TrainingSession): Intent =
            Intent(context, TrainingSessionActivity::class.java).putExtra(EXTRA_SESSION, session)


    }

}
