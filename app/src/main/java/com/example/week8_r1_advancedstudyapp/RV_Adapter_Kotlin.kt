package com.example.week8_r1_advancedstudyapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.week8_r1_advancedstudyapp.fragments.KotlinFragment
import kotlinx.android.synthetic.main.item_row_k.view.*


class RV_Adapter_Kotlin (val frag1: KotlinFragment, private val listOf:ArrayList<Study>): RecyclerView.Adapter<RV_Adapter_Kotlin.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(

                R.layout.item_row_k,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val titel = listOf[position].titelK
        val des = listOf[position].descK
        val id = listOf[position].idK

        holder.itemView.apply {
            text_ViewT.text = titel
            text_ViewD.text = des

            //-----------------------------AlertDialog-------------------------------------------
            idetIcon.setOnClickListener {


                var alt = AlertDialog.Builder(context)
                alt.setTitle("Update Material ")
                val mLayout  = LinearLayout(context)
                val mEtTitel = EditText(context)
                val mEtDes = EditText(context)

                mEtTitel.setSingleLine()
                mEtDes.setSingleLine()

                mLayout.orientation = LinearLayout.VERTICAL
                mLayout.addView(mEtTitel)
                mLayout.addView(mEtDes)
                mLayout.setPadding(50, 40, 50, 10)

                mEtTitel.setText(titel)
                mEtDes.setText(des)
                // Positive button text and action
                alt.setPositiveButton("Save", DialogInterface.OnClickListener { _, _ ->
                    var inputName = mEtTitel.text.toString()
                    var inputT1 = mEtDes.text.toString()
                    frag1.dbhr.update(id,"Kotlin",inputName,inputT1)
                    frag1.updateList()
                })


                // negative button text and action
                alt.setNegativeButton("Cansel", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()

                })

                val alt1: AlertDialog = alt.create()
                alt1.setCanceledOnTouchOutside(false)
                alt1.setView(mLayout)
                alt1.show()
            }
            //------------------------------------------------------------------------------------
            deletIcon.setOnClickListener {
                frag1.dbhr.delete(id)
                frag1.updateList()
            }

        }
    }

    override fun getItemCount() = listOf.size
}
