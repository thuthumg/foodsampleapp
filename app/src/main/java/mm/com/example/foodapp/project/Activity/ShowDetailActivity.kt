package mm.com.example.foodapp.project.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_show_detail.*
import kotlinx.android.synthetic.main.viewholder_popular.*
import mm.com.example.foodapp.R
import mm.com.example.foodapp.project.Domain.FoodDomain
import mm.com.example.foodapp.project.Helper.ManagementCart

class ShowDetailActivity : AppCompatActivity() {
    lateinit var  foodobj:FoodDomain
    var numberOrder:Int = 1
    lateinit var managementCard:ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        managementCard = ManagementCart(this)
        initView()

    }

    private fun initView() {
        foodobj = getIntent().getSerializableExtra("object") as FoodDomain
        var drawableResourceId:Int = this.resources.getIdentifier(foodobj.pic,"drawable",this.packageName)
        Glide.with(this)
            .load(drawableResourceId)
            .into(img_food_pic)

        tv_detail_title.text = (foodobj.title)
        tv_detail_price.text = "${foodobj.fee}"
        tv_desc.text = foodobj.description
        tv_number_order.text = numberOrder.toString()

        img_plus.setOnClickListener {
            numberOrder += 1
            tv_number_order.text = numberOrder.toString()
        }

        img_minus.setOnClickListener {
            if(numberOrder>1)
            {
                numberOrder -= 1
            }
            tv_number_order.text = numberOrder.toString()
        }

        tv_add_to_card.setOnClickListener {
            foodobj.numberInCard = numberOrder
            managementCard.insertFood(foodobj)
        }
    }
}