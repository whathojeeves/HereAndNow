package com.nv.herenow;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private final static String LOGGING_TAG = "NVLOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_main, null);

        // Find the ScrollView
        if (v != null) {
            ScrollView sv = (ScrollView) v.findViewById(R.id.scrollView);
        }

        // Create a LinearLayout element
        LinearLayout ll = null;
        if (v != null) {
            ll = (LinearLayout)v.findViewById(R.id.linear_scrollView);
        }

//        List<RadioButton> buttonList = new ArrayList<RadioButton>();
        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

        //setContentView(R.layout.activity_main);

        final ContentResolver resolver = this.getContentResolver();
        final Uri uri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        final String idKey = MediaStore.Audio.Playlists._ID;
        final String nameKey = MediaStore.Audio.Playlists.NAME;
        final String[] columns = { idKey, nameKey };
        final Cursor playLists = resolver.query(uri, columns, null, null, null);
        if (playLists == null) {
            return;
        }

        // Log a list of the playlists.
        String playListName = null;
        for (boolean hasItem = playLists.moveToFirst(); hasItem; hasItem = playLists.moveToNext()) {
            playListName = playLists.getString(playLists.getColumnIndex(nameKey));
            Log.i(LOGGING_TAG, playListName);
            RadioButton newButton = new RadioButton(this);
            rg.addView(newButton); //the RadioButtons are added to the radioGroup instead of the layout
            newButton.setText(playListName);
        }

        if (ll != null) {
            ll.addView(rg);//you add the whole RadioGroup to the layout
        }
        setContentView(v);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
