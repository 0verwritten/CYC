package com.example.specialforaleg20edition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.io.FileNotFoundException

class Add : Fragment() {
    var ingredient: MutableList<ingredientWithWeight> = mutableListOf<ingredientWithWeight>()
    lateinit var arrayAdapterOneOne: ArrayAdapter<String>
    lateinit var arrayAdapterTwoTwo: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = inflater.inflate(R.layout.fragment_add, container, false)

        val typeOfDietTwo = resources.getStringArray(R.array.TypeOfDiet)
        val typeOfDishTwo = resources.getStringArray(R.array.TypeOfDish)

        arrayAdapterOneOne = ArrayAdapter(requireContext(), R.layout.dropdown_item, typeOfDietTwo)
        arrayAdapterTwoTwo = ArrayAdapter(requireContext(), R.layout.dropdown_item, typeOfDishTwo)

        inflater.autoCompleteTextView.setAdapter(arrayAdapterOneOne)
        inflater.autoCompleteTextView2.setAdapter(arrayAdapterTwoTwo)
        return inflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button2.setOnClickListener {
            ingredient.add(ingredientWithWeight(editTextTextPersonName3.text.toString().toDouble(), ingredients.text.toString() ));
            ingredients.setText("");
            editTextTextPersonName3.setText("");
        }

        button3.setOnClickListener {
            var dishElem = dish();
            dishElem.plan = editTextTextPersonName4.text.toString();
            dishElem.name = editTextTextPersonName.text.toString();
            dishElem.Ingredients = ingredient;
            dishElem.foodCategoryTag = mutableListOf(foodCategories( "test", textInputLayout.editText!!.text.toString() ))
            dishElem.dishUseTag = mutableListOf(dishUse( "test", textInputLayout2.editText!!.text.toString() ))
            dishElem.description = editTextTextPersonName5.text.toString()
            FirebaseFirestore.getInstance().collection("dish").add(dishElem).addOnFailureListener {
                Toast.makeText(requireContext(), "this is total faliure", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(requireContext(), "this is total success", Toast.LENGTH_SHORT).show()  }
        }

    }

}