package aish.android.mvisample.ui.main.state

sealed class CovidStateEvent  {

    class GetSummary : CovidStateEvent()

    class None : CovidStateEvent()
}