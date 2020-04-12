package com.example.finalwc.ui.settings

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.finalwc.ui.home.Location

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    override fun onCreate(db: SQLiteDatabase){
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME" + "($TRAIN_ID INTEGER PRIMARY KEY, $TRAIN_CURR_LOC TEXT, $PREV_STATION_ID TEXT, $NEXT_STATION_ID TEXT, $ARRIVAL_ETA INTEGER)"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db:SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        //called when need upgrade
    }

    fun addTrain(train: TrainObject): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TRAIN_ID, train.train_id)
        values.put(TRAIN_CURR_LOC, train.train_curr_loc)
        values.put(PREV_STATION_ID, train.prev_station_id)
        values.put(NEXT_STATION_ID, train.next_station_id)
        values.put(ARRIVAL_ETA, train.arrival_eta)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun getAllTrain(): String{
        var allTrain: String = ""
        val db = readableDatabase
        val selectAllQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectAllQuery, null)
        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    var train_id = cursor.getString(cursor.getColumnIndex(TRAIN_ID))
                    var train_curr_loc = cursor.getString(cursor.getColumnIndex(TRAIN_CURR_LOC))
                    var prev_station_id = cursor.getString(cursor.getColumnIndex(PREV_STATION_ID))
                    var next_station_id = cursor.getString(cursor.getColumnIndex(NEXT_STATION_ID))
                    var arrival_eta = cursor.getString(cursor.getColumnIndex(ARRIVAL_ETA))

                    allTrain = "$allTrain \n $train_id, $train_curr_loc, $prev_station_id, $next_station_id, $arrival_eta"
                }while(cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allTrain
    }

    fun deleteFromTable(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
        db.execSQL("VACUUM")
        db.close()
    }

    companion object{
        private val DB_NAME = "FinalWC_DB"
        private val DB_VERSION = 1
        private val TABLE_NAME = "train"
        private val TRAIN_ID = "train_id"
        private val TRAIN_CURR_LOC = "train_curr_loc"
        private val PREV_STATION_ID = "prev_station_id"
        private val NEXT_STATION_ID = "next_station_id"
        private val ARRIVAL_ETA = "arrival_eta"
    }
}