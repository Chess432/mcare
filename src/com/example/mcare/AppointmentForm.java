package com.example.mcare;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class AppointmentForm extends Activity implements OnClickListener {
	Button ok;
	EditText date, time;
	int mYear,mMonth,mDate,hourInt,minuteInt;
	DBhelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appointmentform);

		initialise();

		// Button clear=(Button)findViewById(R.id.button2);

		ok.setOnClickListener(this);
		date.setOnClickListener(this);
		time.setOnClickListener(this);
		// clear.setOnClickListener(this);
	}

	private void initialise() {
		// TODO Auto-generated method stub
		ok = (Button) findViewById(R.id.button1);
		date = (EditText) findViewById(R.id.edAppointDate);
		time = (EditText) findViewById(R.id.edAppointTime);
		helper = new DBhelper(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button1:
			if((date.getText().toString().equals("")) || (time.getText().toString().equals(""))){
				Toast.makeText(this, "please provide all fields", Toast.LENGTH_LONG).show();
			}else{
				boolean value = helper.confirmAppointment(date.getText().toString(),time.getText().toString());
			}

			break;
			
		case R.id.edAppointDate:
			final Calendar c1 = Calendar.getInstance();
			mYear = c1.get(Calendar.YEAR);
			mMonth = c1.get(Calendar.MONTH);
			mDate = c1.get(Calendar.DAY_OF_MONTH);
			// Launch Date Picker Dialog
			DatePickerDialog dpd1 = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view1, int year1,
								int monthOfYear1, int dayOfMonth1) {
							// Display selected date in text box
							// TODO Auto-generated method stub
							date.setText(dayOfMonth1 + "-"
									+ (monthOfYear1 + 1) + "-" + year1);
						}
					}, mYear, mMonth, mDate);
			dpd1.show();

			break;

		case R.id.edAppointTime:
			
			final Calendar cal = Calendar.getInstance();
	    	   hourInt = cal.get(Calendar.HOUR_OF_DAY);
	    	   minuteInt = cal.get(Calendar.MINUTE);
	    	   TimePickerDialog timePicker = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					if((hourOfDay<9)&&(minute<9)){
						time.setText("0"+hourOfDay+":"+"0"+minute);
					}else if((hourOfDay<9)&&(minute>9)){
						time.setText("0"+hourOfDay+":"+minute);
					}else if((hourOfDay>9)&&(minute<9)){
						time.setText(hourOfDay+":"+"0"+minute);
					}
					else
					{
						time.setText(hourOfDay+":"+minute);
					}
				}
			},hourInt,minuteInt, true);
	    	   timePicker.show();

			break;
		}
	}
}