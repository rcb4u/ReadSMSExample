package com.rahul.android.apps;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import com.rahul.android.apps.data.SMSData;

public class MainActivity extends Activity {
	public ListView listView;
	List<SMSData> smsList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			listView=(ListView)findViewById(R.id.listview);
		    smsList = new ArrayList<SMSData>();
			getInboxMsg();
		// Set smsList in the ListAdapter
		if(smsList!=null) {
			listView.setAdapter(new ListAdapter(this, smsList));
		/*setListAdapter(new ListAdapter(this, smsList));*/
		}
	}

	public List<SMSData> getInboxMsg(){
		Uri uri = Uri.parse("content://sms/inbox");
		Cursor c= getContentResolver().query(uri, null, null ,null,"date DESC");
		startManagingCursor(c);

		// Read the sms data and store it in the list
		if(c.moveToFirst()) {
			for(int i=0; i < c.getCount(); i++) {
				SMSData sms = new SMSData();
				sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
				sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());

				sms.setTime(c.getString(c.getColumnIndexOrThrow("date")).toString());
				smsList.add(sms);

				c.moveToNext();
			}
		}
		c.close();
	return smsList;
	}

	
}
