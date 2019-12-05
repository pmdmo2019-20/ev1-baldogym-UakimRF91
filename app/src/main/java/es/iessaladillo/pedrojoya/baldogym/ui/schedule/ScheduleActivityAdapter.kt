package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.schedule_activity_item.*
import kotlinx.android.synthetic.main.schedule_activity_item.view.*


class ScheduleActivityAdapter : ListAdapter<TrainingSession, ScheduleActivityAdapter.ViewHolder>(SessionDiffCallback) {

    var onClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = currentList[position]
        holder.bind(session)
    }

    inner class ViewHolder (override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            btn_join.setOnClickListener {
                onClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(session: TrainingSession) {
            session.run {
                containerView.lbl_time.text = time
                containerView.imgSession.setImageResource(photoResId)
                containerView.lbl_name_session.text = name
                containerView.lbl_trainer_session.text = trainer
                containerView.lbl_room_session.text = room
                containerView.lbl_participants.text = participants.toString()
                if (userJoined) {
                    containerView.btn_join.text = R.string.schedule_item_join.toString()
                } else {
                    containerView.btn_join.text = R.string.schedule_item_quit.toString()
                }
            }
        }
    }


    object SessionDiffCallback: DiffUtil.ItemCallback<TrainingSession>() {
        override fun areItemsTheSame(oldItem: TrainingSession, newItem: TrainingSession): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrainingSession, newItem: TrainingSession): Boolean {
            return oldItem.time == newItem.time && oldItem.photoResId == newItem.photoResId &&
                    oldItem.name == newItem.name && oldItem.trainer == newItem.trainer &&
                    oldItem.room == newItem.room && oldItem.participants == newItem.participants

        }

    }
}