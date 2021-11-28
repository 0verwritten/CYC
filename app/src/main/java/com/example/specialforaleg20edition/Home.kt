package com.example.specialforaleg20edition

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import androidx.core.view.forEach
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.HashMap
import android.view.WindowManager
import kotlinx.android.synthetic.main.fragment_home.*


class Home : Fragment() {
//    var dishList = MutableList<dish>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnFilter.setOnClickListener {

        }

        var layout = ScrollerLayout;
        FirebaseFirestore.getInstance().collection("dish").get().addOnSuccessListener {
            // foreaches across all the documents and their fields
            it.documents.forEach { iter ->
                var data = iter.getData()!!;
                if (data["name"] != null && data["description"] != null){
                    var elementLayout = LayoutInflater.from(layout.context).inflate(R.layout.activity_dish_template, layout, false);
                    var titleTextField = elementLayout.findViewById<TextView>(R.id.textView2);
                    titleTextField.setText(data["name"].toString())
                    var titleDescription = elementLayout.findViewById<TextView>(R.id.textView3)

                    // only for technical usage
                    var res = "";
                    var rest = "";
                    if (data["Ingredients"] != null){
                        val a = fromHash(data["Ingredients"] as List<HashMap<String, Any>>)
                        a.forEach {
                            res+="$it\n"
                            rest += "${it.ingredient}\n";
                        }
                    }
                    elementLayout.findViewById<TextView>(R.id.ingredients).setText(rest)

                    // max characters amount -> 108-
                    var testingLine = data["description"].toString()
                    titleDescription.setText( testingLine.subSequence(0, if(testingLine.length > 100) 100 else testingLine.length).toString() + if(testingLine.length > 100) "..." else "" )

                    elementLayout.setOnClickListener {
                        Toast.makeText(requireContext(), data["name"].toString(), Toast.LENGTH_SHORT).show()
                        requireActivity().let {
                            val builder = AlertDialog.Builder(it)//, R.style.CustomAlertDialog)
                            val dialogLayout = requireActivity().layoutInflater.inflate(R.layout.super_dialog_layout, null);

                            dialogLayout.findViewById<TextView>(R.id.titleka).setText(data["name"].toString())
                            dialogLayout.findViewById<TextView>(R.id.cookingPlan).setText(data["plan"].toString().replace("\\n", "\n"))
                            println(data["Ingredients"] as List<HashMap<String, Any>>);
                            dialogLayout.findViewById<TextView>(R.id.cookingPlan2).setText( res )
//                            dialogLayout.findViewById<TextView>(R.id.cookingPlan2).setText(data["Ingradients"].toString())
                            dialogLayout.findViewById<TextView>(R.id.timeToCook).setText("Time to cook: ${data["time"]} mins")

                            builder.setView( dialogLayout )
                                .setNegativeButton("Close",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        dialog.cancel()
                                    })

                            var alertDialog = builder.create()
                            alertDialog.show()
                            val pbutton: Button = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                            pbutton.setBackgroundColor(R.color.myOrange)
//                            val layoutParams = WindowManager.LayoutParams()
//                            layoutParams.copyFrom(alertDialog.window!!.getAttributes())
//                            layoutParams.verticalMargin = 1.0F;
////                            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT - 10
////                            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT - 10
//                            layoutParams.height = -1;
//                            alertDialog.window!!.setAttributes(layoutParams)


                        }
                    }


                    layout.addView( elementLayout )
                }
            }
        }

        var searchBarTextInput = TextInputSearcher;
        searchBarTextInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                layout.forEach {
                    if(it.findViewById<TextView>(R.id.textView2)?.text!!.toString()
                            .lowercase(Locale.getDefault())
                            .contains(s.toString().lowercase(Locale.getDefault())) || s == ""){
                        it.visibility = VISIBLE
                    }else{
                        it.visibility = GONE
                    }
                }
            }
        })
    }

}