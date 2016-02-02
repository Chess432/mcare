package com.example.mcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AdviceFeedback extends Activity implements OnClickListener {

	TextView output;
	Button exit;
	AdviceForm advice;
	StringBuffer buffer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advicefeedback);
		exit = (Button) findViewById(R.id.button1);
		output = (TextView) findViewById(R.id.textView2);

		advice = new AdviceForm();

		buffer = new StringBuffer();
		buffer.append(AdviceForm.getHead()+"\n");
		buffer.append(AdviceForm.getFever()+"\n");
		buffer.append(AdviceForm.getStomach()+"\n");
		buffer.append(AdviceForm.getDisability()+"\n");
		buffer.append(AdviceForm.getHiv()+"\n");
		buffer.append(AdviceForm.getDiarrhoea());
		showAdvice("Medical advice", buffer.toString());

		exit.setOnClickListener(this);
	}

	private void showAdvice(String title, String Message) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(Message);
		builder.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
