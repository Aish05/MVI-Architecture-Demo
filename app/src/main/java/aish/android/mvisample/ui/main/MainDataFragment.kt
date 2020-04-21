package aish.android.mvisample.ui.main

import aish.android.mvisample.R
import aish.android.mvisample.model.CountriesItem
import aish.android.mvisample.ui.DataStateListener
import aish.android.mvisample.ui.state.CovidStateEvent
import aish.android.mvisample.util.DataState
import aish.android.mvisample.viewmodel.MainDataViewModel
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_maindata.*
import java.lang.ClassCastException

class MainDataFragment : Fragment() {

    private lateinit var mainDataViewModel: MainDataViewModel
    private lateinit var dataStateListener: DataStateListener
    private lateinit var mainDataAdapter: MainDataAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStateListener = context as DataStateListener
        } catch (e : ClassCastException) {
            Log.d("Exception", "$context must implement DataStateListener" )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maindata, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainDataViewModel = activity?.let {
            ViewModelProvider(this).get(MainDataViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        subscribeObservers()
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getCovidData()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            mainDataAdapter = MainDataAdapter()
            adapter = mainDataAdapter
        }
    }

    private fun getCovidData() {
        mainDataViewModel.setCovidStateEvent(CovidStateEvent.GetSummary())
    }

    fun subscribeObservers() {

        mainDataViewModel.dataState.observe(viewLifecycleOwner, Observer { covidDataState ->


            //Handle progressbar loading and error messsage
            dataStateListener.onDataStateChange(covidDataState)

            //Handle Data<T>
            covidDataState.data?.let {  event->
                event.getContentIfNotHandled()?.let { viewState->
                    Log.d("Debug", "Data State $viewState")
                    viewState.summary?.let { summary ->
                        mainDataViewModel.setSummary(summary)
                    }
                }
            }


        })

        mainDataViewModel.viewState.observe(viewLifecycleOwner, Observer { covidViewState ->
            Log.d("Debug", "ViewState State $covidViewState")
            covidViewState.summary?.let {
                it.countries?.let { countresList->
                    mainDataAdapter.submitList(countresList)
                }
            }

        })
    }


}