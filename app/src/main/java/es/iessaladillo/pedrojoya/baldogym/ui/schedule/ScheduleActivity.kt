package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity() {

    //Este es nuestro adaptador. Es un campo de la clase, no un método
    //also nos permite recibe como it el objeto de MainActivityAdapter y lo retorna después de ejecutar la lambda
    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().also {
        //Se le da funcionalidad a los tipos función del adaptador.
        //Si se llama a onCheckListener (se llamaba pulsando en el viewholder), para la posición en la que este, llama a checkMenuItem
        it.onClickListener = { position -> setSessionJoined(position) }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupViews()
        observeViewModelData()
    }

    private fun setupViews() {
        setupRecyclerView()
        constraintLayoutScheduleActivity.setOnClickListener {
            changeDayOfWeek()
        }
    }

    private fun changeDayOfWeek() {
        //METODOS PARA CAMBIAR DE MON A TUE
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
        //HACER LOS OBSERVE
    }

    private fun setSessionJoined(position: Int) {
        //METODOS PARA DEFINIR QUÉ PASA CUANDO ALGUIEN LE DA A JOIN
    }

}
