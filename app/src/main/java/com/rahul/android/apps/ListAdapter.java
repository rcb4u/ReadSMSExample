package com.rahul.android.apps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rahul.android.apps.data.SMSData;
public class ListAdapter extends BaseAdapter{
	// List context
    private final Context context;
    // List values
    private final List<SMSData> smsList;
    private long elapsedDays;
    private long elapsedHours;
    private long elapsedMinutes;
    private long elapsedSeconds;

    public ListAdapter(Context context, List<SMSData> smsList) {
		this.context = context;
		this.smsList = smsList;
	}
	@Override
	public int getCount() {
		return smsList.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
		public static class ViewHolder{
			TextView senderNumber;
			TextView Date;
		}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
			if(convertView==null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.smslistlayout, parent, false);
                viewHolder = new ViewHolder();
			viewHolder.senderNumber = (TextView) convertView.findViewById(R.id.smsNumberText);
			viewHolder.Date=(TextView)convertView.findViewById(R.id.smsText);
			convertView.setTag(viewHolder);
		}else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}try{
			//smsList.get(position).getTime()
            Calendar mcalendar = Calendar.getInstance();
            int mtodaysYear = mcalendar.get(Calendar.YEAR);
            int mtodaysMonth = mcalendar.get(Calendar.MONTH)+1;
            int mtodaysDay = mcalendar.get(Calendar.DAY_OF_MONTH);
            int todayshr=mcalendar.get(Calendar.HOUR);
            int todaysminute=mcalendar.get(Calendar.MINUTE);
            int todaysseconds=mcalendar.get(Calendar.SECOND);
            final String todaysDate= mtodaysDay+"/"+mtodaysMonth+"/"+mtodaysYear+" "+todayshr+":"+todaysminute+":"+todaysseconds;

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(smsList.get(position).getTime()));

            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH)+1;
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            int hr=calendar.get(Calendar.HOUR);
            int minute=calendar.get(Calendar.MINUTE);
            int seconds=calendar.get(Calendar.SECOND);
            final String msgsDate= mDay+"/"+mMonth+"/"+mYear+" "+hr+":"+minute+":"+seconds;

            Date msgDate= null; Date Todaydate=null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            try {
                Todaydate=sdf.parse(todaysDate);
                msgDate = sdf.parse(msgsDate);
                printDifference(msgDate,Todaydate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String elapsedfda= String.valueOf(elapsedDays+"Days"+elapsedHours+"Hr");
                viewHolder.Date.setText(elapsedfda);
                viewHolder.senderNumber.setText(smsList.get(position).getNumber());
			viewHolder.senderNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,smsList.get(position).getBody(), Toast.LENGTH_LONG).show();
                }
            });
		}catch (Exception e){
			e.printStackTrace();
		}
        return convertView;
	}
    public void printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

         elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;
        elapsedSeconds = different / secondsInMilli;
        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
    }
}
