package com.codehousedev.epilepsy;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codehousedev.data.SymptomsData;
import com.codehousedev.sqlite.QuerySQLite;
import com.codehousedev.submain.SubClassReporter;

import java.util.ArrayList;

public class ReportSearch extends Activity {
    ArrayList<SymptomsData> mlist ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_search);

       String before = getIntent().getStringExtra("before");
       String after = getIntent().getStringExtra("after");
        Toast.makeText(getApplicationContext(),before + " "+ after,Toast.LENGTH_LONG).show();

        ListView txtdate = (ListView) findViewById(R.id.txtdate);
        QuerySQLite query = new QuerySQLite(this);
        Cursor cur = query.queryreportdate(before,after);

        mlist = new ArrayList<>();
        while (cur.moveToNext()){
            Integer symptomid = cur.getInt(0);
            String headdetail = cur.getString(1);
            String datedetail = cur.getString(9);
            mlist.add(new SymptomsData(symptomid, headdetail,datedetail));
        }
        SubClassReporter sub = new SubClassReporter(this,mlist);
        txtdate.setAdapter(sub);
    }
}
