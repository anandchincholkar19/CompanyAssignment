package com.example.myapplication.view.orderlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Customers
import com.example.myapplication.utils.Const
import com.example.myapplication.view.liseteners.OnOrderItemClickListener
import kotlinx.android.synthetic.main.order_item.view.*

class OrderAdapter(
    private val customers: ArrayList<Customers>,
    private val onOrderItemClickListener: OnOrderItemClickListener
) :
    RecyclerView.Adapter<OrderAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(customer: Customers) {
            itemView.apply {
                tv_schedule_value.text = customer.scheduleDate
                tv_status_value.text = customer.status
                tv_start_value.text = customer.scheduleStartTime
                tv_end_value.text = customer.scheduleEndTime
                if (customer.status  == Const.ORDER_STATUS_ACTIVE) {
                    tv_status_value.setTextColor(resources.getColor(R.color.red,null))
                    img_view_dot.background = resources.getDrawable(R.drawable.dot_red,null)
                } else {
                    tv_status_value.setTextColor(resources.getColor(R.color.green,null))
                    img_view_dot.background = resources.getDrawable(R.drawable.dot_green,null)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        )

    override fun getItemCount(): Int = customers.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(customers[position])
        holder.itemView.setOnClickListener {
            onOrderItemClickListener.onOrderItemClick(
                customers[position].customer, position, customers[position].status, customers[position].imageUrl)
        }
    }

    fun addUsers(customers: List<Customers>) {
        this.customers.apply {
            clear()
            addAll(customers)
        }
    }
}
