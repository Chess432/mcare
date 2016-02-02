package com.example.mcare;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class AdviceForm extends Activity implements OnClickListener{

	EditText symptoms,other;
	CheckBox headache,stomach,fever,diarrhoea,hiv,disability;
	
	Button ok,clear;
	DBhelper helper;
	String valueHead,valueStomach,valueDisability,valueFever,valueDiarrhoea,valueHiv;
	static String headMed;
	static String ferverMed;
	static String stomachMed;
	static String disMed;
	static String diarMed;
	static String hivMed;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adviceform);
		helper = new DBhelper(this);
		initialise();
		 
        
        ok.setOnClickListener(this);
        clear.setOnClickListener(this);
        
	}

	private void initialise() {
		// TODO Auto-generated method stub
		ok=(Button)findViewById(R.id.button1);
        clear=(Button)findViewById(R.id.bExit);
        symptoms=(EditText)findViewById(R.id.edAppointTime);
        other=(EditText)findViewById(R.id.edSecondName);
        headache = (CheckBox) findViewById(R.id.cbHeadache);
        stomach = (CheckBox) findViewById(R.id.cbStomach);
        fever = (CheckBox) findViewById(R.id.cbFever);
        diarrhoea = (CheckBox) findViewById(R.id.cbDiarrhoea);
        hiv = (CheckBox) findViewById(R.id.cbHiv);
        disability = (CheckBox) findViewById(R.id.cbDisability);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	public static String getHead(){
		
		return headMed;
	}
	
public static String getFever(){
		
		return ferverMed;
	}

public static String getStomach(){
	
	return stomachMed;
}

public static String getDisability(){
	
	return disMed;
}

public static String getDiarrhoea(){
	
	return diarMed;
}

public static String getHiv(){
	
	return hivMed;
}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.button1:
			//String value = disability.getText().toString();
			
			if(disability.isChecked()){
				 valueDisability = disability.getText().toString();
				 disMed = "Disability: great caution";
				 
			}else{
				disMed = "";
			 }
			 if(headache.isChecked()){
				 valueHead = headache.getText().toString();				 
				  headMed = "Headache: panadol syrup 5ml 3 times a day";
			}else{
				headMed = "";
			}
			 if(fever.isChecked()){
				 valueFever = fever.getText().toString();
				  ferverMed = "Fever: Less clothing"+"\n"+"if symptoms persist book appointment";
			} else{
				ferverMed="";
			}
			 if(stomach.isChecked()){
				 valueStomach = stomach.getText().toString();
				  stomachMed = "Stomachache: tumbocid"+"\n"+"if symptoms persist book appointment";
			}else{
				stomachMed="";
			}
			 if(diarrhoea.isChecked()){
				 valueDiarrhoea = diarrhoea.getText().toString();
				 diarMed="Diarrhoea: take capsules";
			}else{
				diarMed="";
			}
			 if(hiv.isChecked()){
				 valueHiv = hiv.getText().toString();
				 hivMed="HIV: take ARVs";
			}else{
				hivMed="";
			}
			//Toast.makeText(this, "Value is "+value, Toast.LENGTH_LONG).show();
			boolean result = helper.insertAdvice(valueHead+" "+valueFever+" "+
					valueStomach+" "+ valueDiarrhoea, valueHiv+" "+
					valueDisability);
			if(result == true){
				 AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
				 alertBox.setMessage("submitted successfully");
				 alertBox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
						Intent i =new Intent(AdviceForm.this,AdviceFeedback.class);
						startActivity(i);
					}
				});
				 alertBox.show();
			
			}
			else{
				Toast.makeText(getApplicationContext(), "not accessble", Toast.LENGTH_LONG).show();
			}
			break;
case R.id.bExit:
	symptoms.setText("");
    other.setText("");
			break;
		}
	}
	
}