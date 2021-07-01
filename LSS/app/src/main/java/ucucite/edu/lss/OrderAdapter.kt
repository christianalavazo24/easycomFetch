package ucucite.edu.lss

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(val ordermodels: ArrayList<OrderModels>) :
        RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): OrderAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
                R.layout.example_item,
                parent,
                false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        holder.bindItems(ordermodels[position])
    }

    override fun getItemCount(): Int {
        return ordermodels.size
    }


    //custom

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(messagemodels: OrderModels) {
//

            val user_order = itemView.findViewById(R.id.text_view_1) as TextView
            val users_name = itemView.findViewById(R.id.customer_name) as TextView
            val itemname = itemView.findViewById(R.id.item) as TextView
            val totalamount = itemView.findViewById(R.id.totalpayment) as TextView
            user_order.text = messagemodels.order_no
            users_name.text = messagemodels.name
            itemname.text = messagemodels.concat_item
            totalamount.text = messagemodels.total_amt

//
        }
    }

}