package mm.com.example.foodapp.project.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.activity_main.*
import mm.com.example.foodapp.R

import mm.com.example.foodapp.project.Adapter.CategoryAdapter
import mm.com.example.foodapp.project.Adapter.PopularAdapter
import mm.com.example.foodapp.project.Domain.CategoryDomain
import mm.com.example.foodapp.project.Domain.FoodDomain


class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewCategoryList:RecyclerView
    lateinit var recyclerViewPopularList:RecyclerView
    lateinit var adapter:CategoryAdapter
    lateinit var popularAdapter:PopularAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewCategory()
        recyclerViewPopular()
        bottomNavigation()

    }
//    object : OnClickListener() {
//            fun onClick(v: View?) {
//                startActivity(Intent(this@MainActivity, CartListActivity::class.java))
//            }
//        }
//object : OnClickListener() {
//    fun onClick(v: View?) {
//        startActivity(Intent(this@MainActivity, MainActivity::class.java))
//    }
//}
    private fun bottomNavigation() {
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.card_btn)
        val homeBtn = findViewById<LinearLayout>(R.id.home_btn)
        floatingActionButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, CardListActivity::class.java))
        })
        homeBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
        })
    }
    private fun recyclerViewCategory() {
        var linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    rv_category.layoutManager = linearLayoutManager

        var categoryList:ArrayList<CategoryDomain>  =  ArrayList()
        categoryList.add(CategoryDomain("Pizza","cat_1"))
        categoryList.add(CategoryDomain("Burget","cat_2"))
        categoryList.add(CategoryDomain("Hotdog","cat_3"))
        categoryList.add(CategoryDomain("Drink","cat_4"))
        categoryList.add(CategoryDomain("Dount","cat_5"))

        adapter = CategoryAdapter(this,categoryList)
        rv_category.adapter = adapter
    }

    private fun recyclerViewPopular() {
        var linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rv_popular.layoutManager = linearLayoutManager

        var foodList:ArrayList<FoodDomain>  =  ArrayList()
        foodList.add(FoodDomain("Pepperoni pizza","pizza1","slices pepperoni , mozzarella cheese,fresh oregano,ground black pepper, pizza sauce",9.76))
        foodList.add(FoodDomain("Cheese Burger","burger","beef, Gouda Cheese , Special sauce, Lettuce, tomato",8.79))
        foodList.add(FoodDomain("Vegetable pizza","pizza2","Olive oil, vegetable oil, pitted kalamate, cheery tomatoes, fresh oregano, basil",8.5))


        popularAdapter = PopularAdapter(this,foodList)
        rv_popular.adapter = popularAdapter
    }

}