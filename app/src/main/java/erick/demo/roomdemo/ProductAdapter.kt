package erick.demo.roomdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import erick.demo.roomdemo.database.entity.Product
import kotlinx.android.synthetic.main.view_list_item.view.*

class ProductAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_list_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            val price = itemView.context.getString(R.string.price_format, product.price)
            itemView.tvName.text = product.name + "-" + price
            Picasso.get()
                    .load(product.imageUrl)
                    .into(itemView.ivPicture)
        }
    }
}