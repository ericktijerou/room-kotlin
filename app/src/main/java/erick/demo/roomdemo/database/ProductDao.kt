package erick.demo.roomdemo.database

import android.arch.persistence.room.*
import erick.demo.roomdemo.database.entity.Product


@Dao
interface ProductDao {

    @get:Query("SELECT * FROM product")
    val all: List<Product>

    @Query("SELECT * FROM product WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Product

    @Insert
    fun insertAll(products: List<Product>)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)
}