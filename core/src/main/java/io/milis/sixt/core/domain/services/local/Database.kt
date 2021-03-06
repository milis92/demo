package io.milis.sixt.core.domain.services.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.milis.sixt.core.domain.services.daos.CarLocalService
import io.milis.sixt.core.domain.services.entities.Car

@Database(
        version = Migrations.version,
        entities = [
            Car::class
        ],
        views = [],
        exportSchema = false
)
internal abstract class Database : RoomDatabase() {

    abstract fun cars(): CarLocalService

}