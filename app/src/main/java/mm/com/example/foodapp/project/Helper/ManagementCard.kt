package mm.com.example.foodapp.project.Helper


import android.content.Context
import android.widget.Toast
import mm.com.example.foodapp.project.Domain.FoodDomain
import mm.com.example.foodapp.project.Interface.ChangeNumberItemsListener


class ManagementCart(private val context: Context) {
    private val tinyDB: TinyDB

    init {
        tinyDB = TinyDB(context)
    }

    fun insertFood(item:FoodDomain){

      var  listFood = getlistCard()
      var   existAlready:Boolean = false
        var n = 0
        for (i in listFood.indices){
            if(listFood[i]!!.title == item.title)
            {
                existAlready = true
                n = i
                break
            }
        }

        if(existAlready){
            listFood[n].numberInCard = item.numberInCard
        }else{
            listFood.add(item)
        }

        tinyDB.putListObject("CardList",listFood)
        Toast.makeText(context,"Added To Your Card",Toast.LENGTH_SHORT).show()


    }

    fun getlistCard():ArrayList<FoodDomain>{
        return tinyDB.getListObject("CardList")
    }


    fun plusNumberFood(listFood:ArrayList<FoodDomain>, position:Int, changeNumberItemsListener: ChangeNumberItemsListener){
        listFood[position].numberInCard = listFood[position].numberInCard + 1
        tinyDB.putListObject("CardList",listFood)
        changeNumberItemsListener.changed()
    }

    fun minusFood(listFood:ArrayList<FoodDomain>, position:Int, changeNumberItemsListener: ChangeNumberItemsListener){
        if(listFood[position].numberInCard == 1)
        {
            listFood.removeAt(position)
        }else{
            listFood[position].numberInCard = listFood[position].numberInCard - 1

        }
        tinyDB.putListObject("CardList",listFood)
        changeNumberItemsListener.changed()
    }
    fun getTotalFee(): Double? {
        val listFood2: ArrayList<FoodDomain> = getlistCard()
        var fee = 0.0
        for (i in listFood2.indices) {
            fee = fee + listFood2[i].fee * listFood2[i].numberInCard
        }
        return fee
    }


}