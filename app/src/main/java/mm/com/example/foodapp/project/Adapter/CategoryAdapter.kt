package mm.com.example.foodapp.project.Adapter

import android.content.Context
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
import mm.com.example.foodapp.project.Domain.CategoryDomain

class CategoryAdapter(private var context:Context,var categoryDomain:ArrayList<CategoryDomain>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate =
            LayoutInflater.from(context).inflate(R.layout.viewholder_card, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
      return categoryDomain.size
    }


    class ViewHolder(itemView: View?
    ) : RecyclerView.ViewHolder(itemView!!)
    {
        var categoryName:TextView = itemView!!.findViewById(R.id.categoryName)
        var categoryPic:ImageView = itemView!!.findViewById(R.id.categoryPic)
        var mainLayout:ConstraintLayout = itemView!!.findViewById(R.id.mainLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = categoryDomain.get(position).title
        var picUrl:String = ""
        when(position)
        {
            0 -> {
                picUrl="cat_1"
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.context,R.drawable.category_background1))

            }
            1 -> {
                picUrl="cat_2"
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.context,R.drawable.category_background2))

            }
            2 -> {
                picUrl="cat_3"
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.context,R.drawable.category_background3))

            }
            3 -> {
                picUrl="cat_4"
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.context,R.drawable.category_background4))

            }
            4 -> {
                picUrl="cat_5"
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.context,R.drawable.category_background5))

            }


        }

       var  drawableResourceId: Int = holder.itemView.context.resources.getIdentifier(picUrl, "drawable",holder.itemView.context.packageName)


        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.categoryPic)

    }
}