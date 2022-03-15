package mm.com.example.foodapp.project.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.viewholder_card.view.*
import mm.com.example.foodapp.R
import mm.com.example.foodapp.project.Activity.ShowDetailActivity
import mm.com.example.foodapp.project.Domain.CategoryDomain
import mm.com.example.foodapp.project.Domain.FoodDomain

class PopularAdapter(private var context:Context, var foodDomain:ArrayList<FoodDomain>) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate =
            LayoutInflater.from(context).inflate(R.layout.viewholder_popular, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
      return foodDomain.size
    }


    class ViewHolder(itemView: View?
    ) : RecyclerView.ViewHolder(itemView!!)
    {
        var title:TextView = itemView!!.findViewById(R.id.tv_title)
        var fee:TextView = itemView!!.findViewById(R.id.tv_fee)

        var pic:ImageView = itemView!!.findViewById(R.id.img_pic)
        var addBtn:TextView = itemView!!.findViewById(R.id.btn_add)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = foodDomain.get(position).title
        holder.fee.text = foodDomain.get(position).fee.toString()



       var  drawableResourceId: Int = holder.itemView.context.resources.getIdentifier(foodDomain.get(position).pic, "drawable",holder.itemView.context.packageName)


        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.pic)

        holder.addBtn.setOnClickListener {

            var intent:Intent = Intent(holder.itemView.context, ShowDetailActivity::class.java)
            intent.putExtra("object",foodDomain.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }
}