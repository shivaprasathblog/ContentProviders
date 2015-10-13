package com.example.contentproviders;
import java.util.ArrayList;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity 
{

	/**Add this Permission in Manifest
	 * <uses-permission android:name="android.permission.READ_CONTACTS"/>
	 */
	
	/**
	 * In Andoid OS each application runs in its own particular sandbox , so it is not possible 
	 * share data from one app to other
	 * Content provider is used to access data from other application **/
	
	/**Special About Content Provider 
	 * If we want to our app to share data it's own data to other app we 
	 * can write our own Content Provider
	 * **/
	
	ArrayList<String> ad= new ArrayList<String>();
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView)findViewById(R.id.listView1);
	
		ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,ad);
		lv.setAdapter(adp);
		
		/**Cursor class is used to iterate over a set of data **/
		/**Content Resolver is used to give a url and querry it **/
		/**URI - ContactsContract.Contacts**/
		
		//Cursor contacts= getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder)
		/**Here i'm querring Contacts , but Content Resolver can be used to querry about 
		 * bookmarks, browser history , call log , etc for each the URI changes**/
		Cursor contacts= getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null,null,null);
		
		//To iterate data which comes to contacts
		while (contacts.moveToNext()) 
		{
			//To get Column Index of Contact Name
			int nameIndex= contacts.getColumnIndex(PhoneLookup.DISPLAY_NAME);
			//Getting the Contact Name from the Column Index
			String contactName= contacts.getString(nameIndex);
			
			//Adding the contactname to Array List
			ad.add(contactName);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
