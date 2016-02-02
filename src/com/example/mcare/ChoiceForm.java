package com.example.mcare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChoiceForm extends Activity implements OnClickListener{
	Button seekadv,bookapp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choiceform);
		 seekadv=(Button)findViewById(R.id.button1);
         bookapp=(Button)findViewById(R.id.bExit);
     seekadv.setOnClickListener(this);
     bookapp.setOnClickListener(this);
     
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
			Intent seek=new Intent(ChoiceForm.this,AdviceForm.class);
			startActivity(seek);
		break;
		
		case R.id.bExit:
			Intent book=new Intent(ChoiceForm.this,AppointmentForm.class);
			startActivity(book);
			break;
		}
		
	}
	
}
