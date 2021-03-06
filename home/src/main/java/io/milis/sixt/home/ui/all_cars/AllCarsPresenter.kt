package io.milis.sixt.home.ui.all_cars

import io.milis.sixt.core.common.mvp.MvpRxPresenter
import io.milis.sixt.core.dagger.providers.SchedulerModule
import io.milis.sixt.core.domain.repositories.CarsRepository
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class AllCarsPresenter @Inject constructor(@Named(SchedulerModule.Io) private val schedulerIo: Scheduler,
                                           @Named(SchedulerModule.Main) private val schedulerMain: Scheduler,
                                           private val carsRepository: CarsRepository) : MvpRxPresenter<AllCarsView>() {

    fun onCreated() {
        carsRepository.observe()
                .observeOn(schedulerMain)
                .subscribeOn(schedulerIo)
                .subscribeBy(
                        onNext = {
                            view?.onCarsLoaded(it)
                        },
                        onError = {
                            view?.onFetchError(it)
                        }).addTo(compositeDisposable)
    }


}

