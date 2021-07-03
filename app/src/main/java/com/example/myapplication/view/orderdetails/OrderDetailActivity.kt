package com.example.myapplication.view.orderdetails

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Customer
import com.example.myapplication.utils.Const
import com.example.myapplication.view.OrderBaseActivity
import kotlinx.android.synthetic.main.activity_order_detail.*

class OrderDetailActivity : OrderBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        supportActionBar.let {
            title = resources.getString(R.string.order_detail)
        }
        setCustomerData()
    }

    private fun setCustomerData() {
        intent.run {
            val customer = this.getSerializableExtra(Const.Customer) as Customer
            val orderStatus = this.getStringExtra(Const.OrderStatus) as String
            val imageUrl = this.getStringExtra(Const.ImageUrl) as String
            customer.run {
                tv_name.text =  firstName.plus(" ").plus(customer.lastName)
                tv_address.text = address.plus(customer.city).plus(customer.state)
                tv_contact.text =phoneNumber
                Glide.with(this@OrderDetailActivity).load(imageUrl).into(imageView)
                if (orderStatus == Const.ORDER_STATUS_ACTIVE){
                    btn_finish.visibility = View.VISIBLE
                } else {
                    btn_finish.visibility = View.GONE
                }
                btn_finish.setOnClickListener {
                    customer.isOrderFinished = true
                    showMessage(resources.getString(R.string.message))
                    super.onBackPressed()
                }
            }
        }
    }
}