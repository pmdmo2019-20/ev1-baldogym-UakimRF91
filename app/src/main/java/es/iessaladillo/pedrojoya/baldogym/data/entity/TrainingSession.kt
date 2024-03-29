package es.iessaladillo.pedrojoya.baldogym.data.entity

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrainingSession(
    val id: Long,
    val name: String,
    val weekDay: WeekDay,
    @DrawableRes val photoResId: Int,
    val description: String,
    val time: String,
    val trainer: String,
    val room: String,
    val participants: Int,
    val userJoined: Boolean
): Parcelable