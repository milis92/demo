package io.milis.sixt.core.dagger.mapkeys

import dagger.MapKey
import io.milis.sixt.core.common.mvp.MvpPresenter
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PresenterClass(val value: KClass<out MvpPresenter<*>>)
