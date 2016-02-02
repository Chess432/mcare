package com.example.mcare;



import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	int patientnumber = 0;
public static EditText patientno ;
Button register,submit;
DBhelper helper;
String key;
ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        submit=(Button)findViewById(R.id.button1);
        register=(Button)findViewById(R.id.bExit);
         helper = new DBhelper(this);
       patientno =(EditText) findViewById(R.id.edAppointTime);
       list = new ArrayList<String>();
      
       submit.setOnClickListener(this);
       register.setOnClickListener(this);
      
        
    }
    /* Called when the user touches the button */
    public void sendMessage(View view) {
        // Do something in response to button click
    }

    //@SuppressLint("ShowToast") 
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.bExit:
			Intent n=new Intent(MainActivity.this,RegistrationForm.class);
	   		startActivity(n);
			break;
		case R.id.button1:
			String s1=patientno.getText().toString();
			 patientnumber=Integer.parseInt(s1);
			  
			 try{
			 Cursor res = helper.getKeyData();
			// StringBuffer buffer = new StringBuffer();
			 while(res.moveToNext()){
				// for(int i=0;i<=res.getCount();i++)
				list.add(res.getString(0));
				//buffer.append(res.getString(0));
								
			 }
			 
				
				for(int i=0;i<list.size();i++){
			 key = (list.get(i));}
			 
			 if(patientno.getText().toString().equals(key)){
				 
					Intent intentProfile=new Intent(MainActivity.this,PatientsProfile.class);
					
			   		startActivity(intentProfile);
			   		
				}
				else{
					Toast.makeText(MainActivity.this, "you entered wrong number", 1).show();
				}
			 }catch(CursorIndexOutOfBoundsException e){
				 Toast.makeText(this, "error is "+e.toString(), Toast.LENGTH_LONG).show();
				 
			 }catch(IndexOutOfBoundsException exp){
				 Toast.makeText(this, "error is "+exp.toString(), Toast.LENGTH_LONG).show();
			 }
			 
			
			 
			
			break;
		}
	}
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registration, menu);
        return true;
    }
    
}
