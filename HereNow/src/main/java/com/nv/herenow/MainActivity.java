package com.nv.herenow;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    private final static String LOGGING_TAG = "NVLOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
