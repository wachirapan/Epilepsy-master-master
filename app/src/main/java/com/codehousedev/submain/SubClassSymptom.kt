package com.codehousedev.submain

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.codehousedev.data.SymptomsData
import com.codehousedev.epilepsy.*
import com.codehousedev.sqlite.DeleteSQLite

class SubClassSymptom(var mcontext : Context, var mlist : List<SymptomsData>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v : View = View.inflate(mcontext,R.layout.sub_main_symptom,null)
        var headdetail : TextView = v.findViewById(R.id.detailcontent)
        var datedetail : TextView = v.findViewById(R.id.detaildate)
        var searchdetail : Button = v.findViewById(R.id.searchdetail)
        searchdetail.setOnClickListener({
            var searchdetail = Intent(mcontext,SymptomDetail::class.java)
            searchdetail.putExtra("Symptom_id",mlist[position].symptoms_id.toString())
            mcontext.startActivity(searchdetail)

        })
        var deletedetail : Button = v.findViewById(R.id.deletedetail)
        deletedetail.setOnClickListener({
            var delsymptom = DeleteSQLite(mcontext)
            delsymptom.delsymptoms(mlist[position].symptoms_id.toString())
            var refresh = Intent(mcontext,MainSymptoms::class.java)
            mcontext.startActivity(refresh)
        })
        var meditsymptom : Button = v.findViewById(R.id.editsymptom)
        meditsymptom.setOnClickListener({
            var maineditsymptom = Intent(mcontext,MainEditSymptom::class.java)
            maineditsymptom.putExtra("id",mlist[position].symptoms_id.toString())
            mcontext.startActivity(maineditsymptom)
        })
        headdetail.setText(mlist[position].symptoms_cause.toString())
        datedetail.setText(mlist[position].symptoms_time.toString())
        v.setTag(mlist[position].symptoms_id.toString())
        return v
    }

    override fun getItem(position: Int): Any {
        return mlist[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mlist.size
    }
}