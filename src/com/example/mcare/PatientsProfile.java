package com.example.mcare;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.mcare.MainActivity.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;



public class PatientsProfile extends Activity implements OnClickListener{

	EditText name,gender,date,age,number,sname;
	int ag,mYear,mMonth,mDate;
	Date dob;
	Button ok,edit;
	DBhelper helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patientsprofile);
		 ok=(Button)findViewById(R.id.button1);
         edit=(Button)findViewById(R.id.bExit);
        name=(EditText)findViewById(R.id.edAppointTime);
        sname=(EditText)findViewById(R.id.edSecName);
        number=(EditText)findViewById(R.id.edPatientNumber);
        date=(EditText)findViewById(R.id.edDateOfBirth);
        gender=(EditText)findViewById(R.id.editText3);
        
        
       
       age=(EditText)findViewById(R.id.editText4);
       helper = new DBhelper(this);
       
      // number.setEnabled(false);
       
       ok.setOnClickListener(this);
       edit.setOnClickListener(this);
       date.setOnClickListener(this);
       //number.setEditableFactory(false);
      Cursor cursor = helper.getKeyData();
		if (cursor.getCount() == 0){
			showMessage("Error","no data found");
		}while(cursor.moveToNext()){
			number.setText(cursor.getString(0));
			name.setText(cursor.getString(1));
			sname.setText(cursor.getString(2));
			date.setText(cursor.getString(4));
			gender.setText(cursor.getString(3));
			age.setText(cursor.getString(5));
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.button1:
			
			
			
			Intent ok=new Intent(PatientsProfile.this,ChoiceForm.class);
			startActivity(ok);
			break;
			
		case R.id.edDateOfBirth:
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
			
		case R.id.bExit:
			if((name.getText().toString().equals("")) || (sname.getText().toString().equals("")) ||
					(age.getText().toString().equals("")) || 
					(gender.getText().toString().equals("")) || (date.getText().toString().equals(""))	){
				Toast.makeText(this, "please fill all details", Toast.LENGTH_LONG).show();
			}else if(!isValidFname(name.getText().toString())){
				Toast.makeText(this, "wrong first name format",Toast.LENGTH_LONG).show();
			}else if(!isValidFname(sname.getText().toString())){
				Toast.makeText(this, "wrong second name format",Toast.LENGTH_LONG).show();
			}else if(!isValidAge(age.getText().toString())){
				Toast.makeText(this, "wrong age format",Toast.LENGTH_LONG).show();
			}
			else {
				boolean value = helper.updateDetails(number.getText().toString(),name.getText().toString(),sname.getText().toString(),
						date.getText().toString(),gender.getText().toString(),age.getText().toString());
				if(value == true){
					AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
					 alertBox.setMessage("submitted successfully");
					 alertBox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
							arg0.dismiss();
						}
					});
					 alertBox.show();
				}
			}
			
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


	private void showMessage(String title, String message) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
		
	}
	

}
