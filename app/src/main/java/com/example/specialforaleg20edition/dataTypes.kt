package com.example.specialforaleg20edition

data class userData ( var userName: String, var email: String, var password: String, var userPreference: MutableList<foodCategories> )

data class ingredients ( var id: String, var name : String, var price: Double )

data class ingredientWithWeight ( var amount: Double, var ingredient: String ){
    override fun toString(): String{
        return " - ${amount} $ingredient"
    }
}

data class foodCategories ( var id: String, var name: String)

data class dishUse ( var id: String, var name: String )

data class dish ( var id: String, var name: String, var description: String, var time: Double, var plan: String,
                  var Ingredients: MutableList<ingredientWithWeight>?, public var foodCategoryTag: MutableList<foodCategories>?,
                  var dishUseTag: MutableList<dishUse>? ){
    constructor() : this("", "", "", 0.0,"", null, null, null) {}
}

data class meals ( var breakfast: MutableList<dish>, var brunch: MutableList<dish>, var lunch: MutableList<dish>, var dinner: MutableList<dish> )

data class dieta ( var foodCategoryTag: MutableList<foodCategories>,
                   var monday: MutableList<meals>, var tuesday: MutableList<meals>,
                   var wednesday: MutableList<meals>, var thursday: MutableList<meals>,
                   var friday: MutableList<meals>, var saturday: MutableList<meals>, var sunday: MutableList<meals>)


fun fromHash(hashArr: List<HashMap<String, Any>>): MutableList<ingredientWithWeight>{
    var res: MutableList<ingredientWithWeight> = mutableListOf<ingredientWithWeight>();

    hashArr.forEach {
        res.add(ingredientWithWeight( it["amount"].toString().toDouble(), it["tag"].toString() ))
    }

    return res
}