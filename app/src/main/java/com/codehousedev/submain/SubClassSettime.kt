package com.codehousedev.submain

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.codehousedev.data.SettimingDrug
import com.codehousedev.epilepsy.*
import com.codehousedev.sqlite.DeleteSQLite

class SubClassSettime(var mcontext : Context,var mlist : List<SettimingDrug>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v : View = View.inflate(mcontext,R.layout.sub_main_settime,null)
        var txtdetailname : TextView = v.findViewById(R.id.txtdetailname)
        var txtcategor : TextView = v.findViewById(R.id.txtcategor)
        var txtduration : TextView = v.findViewById(R.id.txtduration)
        var txtamount : TextView = v.findViewById(R.id.txtamount)

        txtdetailname.setText(mlist[position].settime_namedrug.toString())
        txtcategor.setText(mlist[position].settime_category.toString())
        txtduration.setText("เวลา : "+mlist[position].settime_durationhour.toString()+":"+mlist[position].settime_durationminute.toString())
        txtamount.setText("จำนวน : "+mlist[position].settime_amount.toString()+" /เม็ด")
        var delsittiming : TextView = v.findViewById(R.id.delsittiming)
        delsittiming.setOnClickListener({
            var delsettiming = DeleteSQLite(mcontext)
            delsettiming.delsettiming(mlist[position].settime_id.toString())


            SetTime.listValue.remove(mlist[position].settime_fulltime.toString())


            var refresh = Intent(mcontext,MainSettime::class.java)
            mcontext.startActivity(refresh)
        })
//        var btnedit : Button = v.findViewById(R.id.btnedit)
//        btnedit.setOnClickListener({
//            var sendedit = Intent(mcontext,MainEditTiming::class.java)
//            sendedit.putExtra("id",mlist[position].settime_id.toString())
//            sendedit.putExtra("hour",mlist[position].settime_durationhour.toString())
//            sendedit.putExtra("minute",mlist[position].settime_durationminute.toString())
//            sendedit.putExtra("category",mlist[position].settime_category.toString())
//            sendedit.putExtra("namedrug",mlist[position].settime_namedrug.toString())
//            sendedit.putExtra("total",mlist[position].settime_amount.toString())
//            mcontext.startActivity(sendedit)
//        })
        v.setTag(mlist[position].settime_id.toString())
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