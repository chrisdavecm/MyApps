package com.davidsonecm.alfrescoimageuploader.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Columns;

import com.davidsonecm.alfrescoimageuploader.domain.Server;

public class ServersDataSource {

	private SQLiteDatabase database;
	private AIUSQLiteOpenHelper dbHelper;
	private String[] allColumns = { AIUSQLiteOpenHelper.COLUMN_ID,
			AIUSQLiteOpenHelper.COLUMN_NAME,AIUSQLiteOpenHelper.COLUMN_ADDR,
			AIUSQLiteOpenHelper.COLUMN_PORT};

	public ServersDataSource(Context context) {
		dbHelper = new AIUSQLiteOpenHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public Server createServer(Server server) {
	    ContentValues values = new ContentValues();
	    values.put(AIUSQLiteOpenHelper.COLUMN_NAME, server.getName());
	    values.put(AIUSQLiteOpenHelper.COLUMN_ADDR, server.getAddress());
	    values.put(AIUSQLiteOpenHelper.COLUMN_PORT, server.getPort());
	    
	    long insertId = database.insert(AIUSQLiteOpenHelper.TABLE_SERVERS, null,
	        values);
	    
	    Cursor cursor = database.query(AIUSQLiteOpenHelper.TABLE_SERVERS,
	        allColumns, AIUSQLiteOpenHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Server newServer = cursorToServer(cursor);
	    cursor.close();
	    return newServer;
	  }

	private Server cursorToServer(Cursor cursor) {
		Server server = new Server();
	    server.setId(cursor.getLong(0));
	    server.setName(cursor.getString(1));
	    server.setAddress(cursor.getString(2));
	    server.setPort(cursor.getString(3));
	    return server;
	}

	public List<Server> getServers() {
		List<Server> servers = new ArrayList<Server>();

	    Cursor cursor = database.query(AIUSQLiteOpenHelper.TABLE_SERVERS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Server server = cursorToServer(cursor);
	    	servers.add(server);
	        cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return servers;
	}

	public Server getServer(long id) {
		List<Server> servers = new ArrayList<Server>();

	    Cursor cursor = database.query(AIUSQLiteOpenHelper.TABLE_SERVERS,
	        allColumns, AIUSQLiteOpenHelper.COLUMN_ID + "=?", 
	        new String[] { String.valueOf(id) }, null, null, null);

	    Server server = null;
	    if(cursor.moveToFirst()){
	    	server = cursorToServer(cursor);
		    servers.add(server);
	    }	    

	    // Make sure to close the cursor
	    cursor.close();
	    return server;
	}
}
