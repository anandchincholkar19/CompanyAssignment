package com.example.myapplication.view.orderlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.model.Customer
import com.example.myapplication.model.Order
import com.example.myapplication.network.repository.ApiHelper
import com.example.myapplication.network.repository.RepositoryBuilder
import com.example.myapplication.utils.Const
import com.example.myapplication.utils.Status
import com.example.myapplication.view.*
import com.example.myapplication.view.liseteners.OnOrderItemClickListener
import com.example.myapplication.view.orderdetails.OrderDetailActivity
import kotlinx.android.synthetic.main.activity_list_order.*

class OrderListActivity : OrderBaseActivity(), OnOrderItemClickListener {

    private lateinit var listViewModel: OrderListViewModel
    private lateinit var adapter: OrderAdapter
    private lateinit var mOrder: Order
    private var mPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_order)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    override fun onRestart() {
        super.onRestart()
        afterOrderfinished()
    }

    private fun setupViewModel() {
        listViewModel = ViewModelProvider(
            this, OrderViewModelFactory(ApiHelper(RepositoryBuilder.apiService)))
            .get(OrderListViewModel::class.java)
    }

    private fun setupObservers() {
        listViewModel.getOrders().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                       recylerview.visibility = View.VISIBLE
                       progressBar.visibility = View.GONE
                        resource.data?.let {
                            order -> retrieveList(order, false)
                        }
                    }
                    Status.ERROR -> {
                        recylerview.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        showMessage(it.message)
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(order: Order, isComeFromFinsih: Boolean) {
        adapter.apply {
            mOrder = order
            if (isComeFromFinsih) {
                mOrder.customers[mPosition].status = Const.ORDER_STATUS_COMPLETE
            }
            addUsers(mOrder.customers)
            notifyDataSetChanged()
        }
    }

    private fun setupUI() {
        supportActionBar?.run {
           hide()
        }
        recylerview.layoutManager = LinearLayoutManager(this)
        adapter = OrderAdapter(arrayListOf(), this)
        recylerview.addItemDecoration(
            DividerItemDecoration(
                recylerview.context,
                (recylerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        recylerview.adapter = adapter
    }

    override fun onOrderItemClick(customer: Customer, position: Int, orderStatus: String, imageUrl: String) {
        mPosition = position
        val intent = Intent(this, OrderDetailActivity::class.java)
        intent.apply {
            putExtra(Const.Customer, customer)
            putExtra(Const.OrderStatus, orderStatus)
            putExtra(Const.ImageUrl, imageUrl)
            startActivity(this)
        }
    }

     fun afterOrderfinished() {
        mOrder.customers[mPosition].customer.isOrderFinished = true
        retrieveList(mOrder, true)
    }
}
