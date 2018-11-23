package com.codehousedev.submain

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.codehousedev.data.SymptomsData

import com.codehousedev.epilepsy.*

class SubClassReporter(var mcontext : Context, var mlist : ArrayList<SymptomsData>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View = View.inflate(mcontext,R.layout.sub_main_reporter,null)

        var mdetailcontent : TextView = view.findViewById(R.id.detailcontent)
        var mdetaildate : TextView = view.findViewById(R.id.detaildate)
        var msearchdetail : Button = view.findViewById(R.id.searchdetail)

        mdetailcontent.setText(mlist[position].symptoms_cause.toString())
        mdetaildate.setText(mlist[position].symptoms_time.toString())
        msearchdetail.setOnClickListener({
            var searchdetail = Intent(mcontext,SymptomDetail::class.java)
            searchdetail.putExtra("Symptom_id",mlist[position].symptoms_id.toString())
            mcontext.startActivity(searchdetail)
        })

        view.setTag(mlist[position].symptoms_id)
        return view
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