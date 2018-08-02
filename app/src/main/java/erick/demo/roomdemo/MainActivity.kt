package erick.demo.roomdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import erick.demo.roomdemo.database.entity.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val llm = GridLayoutManager(this, 2)
        rvProducts.layoutManager = llm

        Thread(Runnable {
            val products = App.get().getDB().productDao().all
            val force = App.get().isForceUpdate()
            if (force || products.isEmpty()) {
                retrieveProducts()
            } else {
                populateProducts(products)
            }
        }).start()
    }

    private fun retrieveProducts() {
        val list = mutableListOf<Product>()
        for (i in 0..9) {
            val product = Product(i+1,
                    getString(R.string.name_format, i.toString()),
                    "https://picsum.photos/500/500?image=$i",
                    if (i == 0) 50 else i * 100)
            list.add(product)
        }
        App.get().getDB().productDao().insertAll(list)
        App.get().setForceUpdate(false)
        populateProducts(list)
    }

    private fun populateProducts(products: List<Product>) {
        runOnUiThread { rvProducts.adapter = ProductAdapter(products) }
    }
}
