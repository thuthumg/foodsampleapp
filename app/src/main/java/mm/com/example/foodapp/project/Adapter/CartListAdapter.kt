package mm.com.example.foodapp.project.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mm.com.example.foodapp.R
import mm.com.example.foodapp.project.Domain.FoodDomain

import mm.com.example.foodapp.project.Helper.ManagementCart
import mm.com.example.foodapp.project.Interface.ChangeNumberItemsListener
import java.lang.String
import kotlin.Int

class CartListAdapter(
    FoodDomains: ArrayList<FoodDomain>,
    context: Context?,
    changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    var foodDomains: ArrayList<FoodDomain>? = FoodDomains
    var managementCart: ManagementCart? = null
    var changeNumberItemsListener: ChangeNumberItemsListener? = changeNumberItemsListener

    init {
        managementCart = ManagementCart(context!!)
    }


   /* fun CartListAdapter(
        FoodDomains: ArrayList<FoodDomain>,
        context: Context?,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        this.foodDomains = FoodDomains
        managementCart = ManagementCart(context!!)
        this.changeNumberItemsListener = changeNumberItemsListener
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_card_list_adapter, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(foodDomains!![position].title)
        holder.feeEachItem.setText(foodDomains!![position].fee.toString())
        holder.totalEachItem.setText(String.valueOf(Math.round(foodDomains!![position].numberInCard * foodDomains!![position].fee * 100.0) / 100.0))
        holder.num.setText(String.valueOf(foodDomains!![position].numberInCard))
        val drawableResourceId: Int = holder.itemView.getContext().getResources().getIdentifier(
            foodDomains!![position].pic, "drawable", holder.itemView.getContext().getPackageName()
        )
        Glide.with(holder.itemView.getContext())
            .load(drawableResourceId)
            .into(holder.pic)
        holder.plusItem.setOnClickListener(View.OnClickListener {
            managementCart!!.plusNumberFood(
                foodDomains!!,
                position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener!!.changed()
                    }
                })
        })
        holder.minusItem.setOnClickListener(View.OnClickListener {
            managementCart?.minusFood(
                foodDomains!!,
                position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener!!.changed()
                    }
                })
        })
    }


    override fun getItemCount(): Int {
        return foodDomains!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var feeEachItem: TextView
        var pic: ImageView
        var plusItem: ImageView
        var minusItem: ImageView
        var totalEachItem: TextView
        var num: TextView

        init {
            title = itemView.findViewById(R.id.title2Txt)
            feeEachItem = itemView.findViewById(R.id.feeEachItem)
            pic = itemView.findViewById(R.id.picCard)
            totalEachItem = itemView.findViewById(R.id.totalEachItem)
            num = itemView.findViewById(R.id.numberItemTxt)
            plusItem = itemView.findViewById(R.id.plusCardBtn)
            minusItem = itemView.findViewById(R.id.minusCardBtn)
        }
    }
}