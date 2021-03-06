package io.milis.sixt.home.ui.all_cars

import android.view.LayoutInflater
import dagger.BindsInstance
import io.milis.sixt.core.dagger.BaseActivityComponent
import io.milis.sixt.core.dagger.CoreComponent
import dagger.Component
import io.milis.sixt.App
import io.milis.sixt.core.Core
import io.milis.sixt.core.dagger.scopes.ApplicationScope
import io.milis.sixt.core.dagger.scopes.FeatureScope

@FeatureScope
@Component(
        modules = [
            AllCarsModule::class
        ],
        dependencies =
        [
            CoreComponent::class
        ])
internal abstract class AllCarsComponent : BaseActivityComponent<AllCarsActivity> {

    @Component.Factory
    interface Factory {
        fun create(@ApplicationScope coreComponent: CoreComponent, @BindsInstance layoutInflater: LayoutInflater): AllCarsComponent
    }
}

fun AllCarsActivity.inject() {
    DaggerAllCarsComponent.factory()
            .create(Core.component, layoutInflater)
            .inject(this)
}