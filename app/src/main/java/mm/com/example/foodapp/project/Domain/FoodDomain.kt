package mm.com.example.foodapp.project.Domain

import java.io.Serializable

class FoodDomain(title:String,pic:String,
description:String,fee:Double) : Serializable{
    var numberInCard : Int = 0
    var title:String = title
        get(){ return field}
        set(value) { field = value }

    var pic:String = pic
        get(){ return field }
        set(value){ field = value}

    var description:String = description
        get(){ return field}
        set(value) { field = value }

    var fee:Double = fee
        get(){ return field }
        set(value){ field = value}

   /* var numberInCard:Int = numberInCard
        get(){ return field }
        set(value){ field = value}*/
}