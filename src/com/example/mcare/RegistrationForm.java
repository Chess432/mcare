package com.example.mcare;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

@SuppressLint("ShowToast") public class RegistrationForm extends Activity implements OnClickListener {

	String nam, gend;
	EditText name, age, gender, dateinput,sname;
	DatePickerDialog date, today;
	Date dat;
	Integer ag;
	SimpleDateFormat dateformatter;
	Calendar cal;
	int mYear=1970, mDate=15, mMonth=6;
	int endYear,endMonth,endDay;
	int resYear,resMonth,resDay;
	Button b, c,bAge;
	DBhelper helper;
	Spinner spGender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationform);
		b = (Button) findViewById(R.id.button1);
		c = (Button) findViewById(R.id.bExit);
		
		name = (EditText) findViewById(R.id.edAppointTime);
		sname = (EditText) findViewById(R.id.edSecondName);
		dateinput = (EditText) findViewById(R.id.editText3);
		helper = new DBhelper(this);
		spGender = (Spinner) findViewById(R.id.spGender);

		dateformatter = new SimpleDateFormat("dd-mm-yyyy", Locale.US);
		age = (EditText) findViewById(R.id.editText4);
         //ageCalculator();

		b.setOnClickListener(this);
		c.setOnClickListener(this);
		dateinput.setOnClickListener(this);
	}

	private String currentDate() {
		// TODO Auto-generated method stub
		final Calendar c1 = Calendar.getInstance();
		endYear = c1.get(Calendar.YEAR);
		endMonth = c1.get(Calendar.MONTH);
		endMonth++;
		endDay = c1.get(Calendar.DAY_OF_MONTH);
		return endDay+":"+endMonth+":"+endYear;
		
	}
	
	public void calculateYear(){
		resYear=endYear-mYear;
	}
	public void calculateMonth(){
		if(endMonth>=mMonth){
			resMonth = endMonth-mMonth;
		}else{
			resMonth=endMonth-mMonth;
			resMonth=12+resMonth;
			resYear--;
		}
	}
	
	public void calculateDay(){
		if(endDay>=mDate){
			resDay = endDay-mDate;
		}else{
			resDay = endDay - mDate;
			resDay = 30+resDay;
			//resMonth--;
		}
	}
	
	public String getResult(){
		return resDay+":"+resMonth+":"+resYear;
	}


	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.editText3:
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
							dateinput.setText(dayOfMonth1 + "-"
									+ (monthOfYear1 + 1) + "-" + year1);
						}
					}, mYear, mMonth, mDate);
			dpd1.show();

			break;
			
		case R.id.button1:
			if((name.getText().toString().equals("")) || (sname.getText().toString().equals("")) ||
					(age.getText().toString().equals("")) || 
					 (dateinput.getText().toString().equals(""))){
				Toast.makeText(this, "please fill all details", Toast.LENGTH_LONG).show();
			}else if(!isValidFname(name.getText().toString())){
				Toast.makeText(this, "wrong first name format",Toast.LENGTH_LONG).show();
			}else if(!isValidFname(sname.getText().toString())){
				Toast.makeText(this, "wrong second name format",Toast.LENGTH_LONG).show();
			}else if(!isValidAge(age.getText().toString())){
				Toast.makeText(this, "wrong age format",Toast.LENGTH_LONG).show();
			}else{
			 boolean value =helper.insertData(name.getText().toString(),sname.getText().toString(),spGender.getSelectedItem().toString(),
					 dateinput.getText().toString(),age.getText().toString());
			 if(value == true){
				 AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
				 alertBox.setMessage("submitted successfully");
				 alertBox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
						Intent p=new Intent(RegistrationForm.this,PatientsProfile.class);
						 startActivity(p);
					}
				});
				 alertBox.show();
				// Toast.makeText(this, "submitted successfully", 1).show();
			 }else
				 Toast.makeText(this, "not submitted successfully", 1).show();
			}
			break;

		case R.id.bExit:
			name.setText("");
			//gender.setText("");
			age.setText("");
			dateinput.setText("");
			break;
			
		
		}
		
	}

	

	private boolean isValidAge(String age1) {
		// TODO Auto-generated method stub
		String EMAIL_PATTERN = "^[0-9]{1}$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(age1);
		return matcher.matches();
	}


	private boolean isValidFname(String fname1) {
		// TODO Auto-generated method stub
		String EMAIL_PATTERN = "^[A-Za-z]{3,15}$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(fname1);
		return matcher.matches();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
