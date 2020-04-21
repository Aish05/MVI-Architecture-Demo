package aish.android.mvisample.ui.main

import aish.android.mvisample.R
import aish.android.mvisample.replaceFragment
import aish.android.mvisample.ui.DataStateListener
import aish.android.mvisample.util.DataState
import aish.android.mvisample.viewmodel.MainDataViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataStateListener {

    lateinit var mainDataViewModel: MainDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainDataViewModel = ViewModelProvider(this).get(MainDataViewModel::class.java)

        addMainDataFragment()
    }

    private fun addMainDataFragment() {
        replaceFragment(MainDataFragment(), R.id.fragment_container, "MainFragment")
    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let {
            // handle progress loading
            showProgressBar(it.loading)
            //handle error message


            dataState.message?.let { event ->
                event.getContentIfNotHandled()?.let { message->
                    showToast(message)
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar(isVisible : Boolean) {
        if(isVisible) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}
