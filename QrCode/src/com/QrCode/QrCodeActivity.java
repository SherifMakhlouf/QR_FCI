package com.QrCode;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class QrCodeActivity extends Activity {
    /** Called when the activity is first created. */
	//Intent intent = new Intent(android.content.Intent.ACTION_SEND);
	String contents = "";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button scanButton = (Button) findViewById(R.id.scane);
	    Button sendButton = (Button) findViewById(R.id.send);
	    
    	
	    scanButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 	
			        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				    startActivityForResult(intent, 0);
			}
		});
	    
	    sendButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 EditText address =(EditText) findViewById(R.id.address);
				 EditText title =(EditText) findViewById(R.id.title);
				 
				Intent intent = new Intent(Intent.ACTION_SEND);
				String[] emailAddress = {address.getText().toString()};
				intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
				intent.putExtra(Intent.EXTRA_SUBJECT, title.getText().toString());
				intent.setType("plain/text");
				intent.putExtra(Intent.EXTRA_TEXT, contents);
				startActivity(intent);
			}
		});
	    
    }
    
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onRestoreInstanceState(savedInstanceState);
    	contents = savedInstanceState.getString("contents");
    }
  
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	// TODO Auto-generated method stub
    	outState.putString("contents", contents);
    	super.onSaveInstanceState(outState);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //  The Intents Fairy has delivered us some data!
                contents = intent.getStringExtra("SCAN_RESULT");
               
               
              
		    	
			    
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
        
        
    }
    
   
}