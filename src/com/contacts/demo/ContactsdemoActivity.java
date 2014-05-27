package com.contacts.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class ContactsdemoActivity extends Activity implements OnClickListener {
	 TextView textView;
     LayoutInflater inflater;
     EditText editText;
     EditText editText1;
     EditText editText2;
     View view;
     View view2;
    /** Called when the activity is first created. */
    @Override
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view=getLayoutInflater().inflate(R.layout.c,null);
        view2=getLayoutInflater().inflate(R.layout.d,null);
        editText=(EditText) view.findViewById(R.id.edit);
        editText1=(EditText) view2.findViewById(R.id.edit1);
        editText2=(EditText) view2.findViewById(R.id.edit2);
    
        findViewById(R.id.findallcontact).setOnClickListener(this);
        findViewById(R.id.deletecontact).setOnClickListener(this);
        findViewById(R.id.updatecontact).setOnClickListener(this);
        findViewById(R.id.byphonetoname).setOnClickListener(this);
        findViewById(R.id.addcontact).setOnClickListener(this);
        textView=(TextView)findViewById(R.id.text);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.findallcontact:
            Intent intent=new Intent(ContactsdemoActivity.this,ListAllContacts.class);
            startActivity(intent);
			break;
		case R.id.deletecontact:

			break;
		case R.id.updatecontact:

			break;
		case R.id.byphonetoname:
          showDialog(1);
			break;
		case R.id.addcontact:
		showDialog(2);
			break;
		default:
			break;
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		super.onResume();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog=null;
		Builder builder=new Builder(ContactsdemoActivity.this);
		switch (id) {
		case 1:
			builder.setTitle("请输入电话号码");
			builder.setView(view);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String name=ContactsUtil.testReadNameByPhone(ContactsdemoActivity.this,editText.getText().toString());
					
					textView.setText(name);
				}
			});
			dialog=builder.create();
			break;
		case 2:
			builder.setTitle("请输入电话号码");
			builder.setView(view2);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					ContactsUtil.testAddContacts(ContactsdemoActivity.this,editText1.getText().toString(),editText2.getText().toString());
				}
			});
			dialog=builder.create();
			break;
		default:
			break;
		}
		// TODO Auto-generated method stub
		return dialog;
	}
	
}