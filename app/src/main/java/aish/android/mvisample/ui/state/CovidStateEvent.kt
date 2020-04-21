package aish.android.mvisample.ui.state

sealed class CovidStateEvent  {

    class GetSummary : CovidStateEvent()

    class None : CovidStateEvent()
}