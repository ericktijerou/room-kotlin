package erick.demo.roomdemo.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Product(@PrimaryKey(autoGenerate = true) var uid: Int,
                   @ColumnInfo(name = "name") var name: String,
                   @ColumnInfo(name = "image_url") var imageUrl: String,
                   @ColumnInfo(name = "price") var price: Int)