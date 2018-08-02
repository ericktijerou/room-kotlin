package erick.demo.roomdemo.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import erick.demo.roomdemo.database.entity.Product
import android.arch.persistence.room.Database
import erick.demo.roomdemo.App


@Database(entities = arrayOf(Product::class), version = 2)
@TypeConverters(DateTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE product " + " ADD COLUMN price INTEGER")

                // enable flag to force update products
                App.get()!!.setForceUpdate(true)
            }
        }
    }
}