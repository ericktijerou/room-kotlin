package erick.demo.roomdemo

import android.app.Application
import android.content.SharedPreferences
import erick.demo.roomdemo.database.MyDatabase
import android.arch.persistence.room.Room
import android.content.Context

class App : Application() {

    private var database: MyDatabase? = null

    companion object {
        var INSTANCE: App? = null
        private val DATABASE_NAME = "MyDatabase"
        private val PREFERENCES = "RoomDemo.preferences"
        private val KEY_FORCE_UPDATE = "force_update"

        fun get() : App {
            return INSTANCE!!
        }
    }

    override fun onCreate() {
        super.onCreate()

        // create database
        database = Room.databaseBuilder(applicationContext, MyDatabase::class.java, DATABASE_NAME)
                .addMigrations(MyDatabase.MIGRATION_1_2)
                .build()

        INSTANCE = this
    }

    fun getDB(): MyDatabase {
        return database!!
    }

    fun isForceUpdate(): Boolean {
        return getSP().getBoolean(KEY_FORCE_UPDATE, true)
    }

    fun setForceUpdate(force: Boolean) {
        val edit = getSP().edit()
        edit.putBoolean(KEY_FORCE_UPDATE, force)
        edit.apply()
    }

    private fun getSP(): SharedPreferences {
        return getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
    }
}