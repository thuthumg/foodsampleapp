package mm.com.example.foodapp.project.Activity


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_card_list.*
import kotlinx.android.synthetic.main.activity_card_list_second.*
import kotlinx.android.synthetic.main.activity_main.*
import mm.com.example.foodapp.R
import mm.com.example.foodapp.project.Adapter.CartListAdapter
import mm.com.example.foodapp.project.Helper.ManagementCart
import mm.com.example.foodapp.project.Interface.ChangeNumberItemsListener


class CardListActivity : AppCompatActivity() {
    private var adapter: RecyclerView.Adapter<*>? = null
    private var recyclerViewList: RecyclerView? = null
    private var managementCart: ManagementCart? = null
   private var tax = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list_second)

        managementCart = ManagementCart(this)

       // initView()
        initList()
        calculateCard()
        bottomNavigation()
    }

    /*object : View.OnClickListener {
            fun onClick(v: View?) {
                startActivity(Intent(this@CardListActivity, CardListActivity::class.java))
            }
        }object : DialogInterface.OnClickListener() {
            fun onClick(v: View?) {
                startActivity(Intent(this@CardListActivity, MainActivity::class.java))
            }
        }*/
    private fun bottomNavigation() {
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.card_btn)
        val homeBtn = findViewById<LinearLayout>(R.id.homeBtn)
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this@CardListActivity, CardListActivity::class.java))
        }
        homeBtn.setOnClickListener {
            startActivity(Intent(this@CardListActivity, MainActivity::class.java))
        }
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewList?.layoutManager = linearLayoutManager
        adapter = CartListAdapter(
            managementCart!!.getlistCard(),this,
            object : ChangeNumberItemsListener {
                override fun changed() {
                    calculateCard()
                }
            })
        recyclerViewList?.adapter = adapter
        if (managementCart!!.getlistCard().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE)
            scrollView4.setVisibility(View.GONE)
        } else {
            emptyTxt.setVisibility(View.GONE)
            scrollView4.setVisibility(View.VISIBLE)
        }
    }
    private fun calculateCard() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round(managementCart!!.getTotalFee()!! * percentTax * 100.0) / 100.0
        val total = Math.round((managementCart!!.getTotalFee()!! + tax + delivery) * 100.0) / 100.0
        val itemTotal = Math.round(managementCart!!.getTotalFee()!! * 100.0) / 100.0
        totalFeeTxt.setText("$$itemTotal")
        taxTxt.setText("$$tax")
        deliveryTxt.setText("$$delivery")
        totalTxt.setText("$$total")
    }

    /*private fun initView() {
        recyclerViewList = findViewById<View>(R.id.recyclerview)
        totalFeeTxt = findViewById<View>(R.id.totalFeeTxt)
        taxTxt = findViewById<View>(R.id.taxTxt)
        deliveryTxt = findViewById<View>(R.id.deliveryTxt)
        totalTxt = findViewById<View>(R.id.totalTxt)
        emptyTxt = findViewById<View>(R.id.emptyTxt)
        scrollView = findViewById<View>(R.id.scrollView4)
    }*/
}