package co.mathgenie.LocalDatabasePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import co.mathgenie.AllActivities.CalculationActivity;
import co.mathgenie.DataModelsPackage.LocalDatabaseModel;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mytable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SELECTED_ANSWER = "selected_answer";
    private static final String COLUMN_CORRECT_ANSWER = "correct_answer";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_DATE_TIME_IN_MILLS = "datetime_in_mills";
    private static final String COLUMN_RATING = "rating";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_SELECTED_ANSWER + " TEXT," +
                    COLUMN_CORRECT_ANSWER + " TEXT," +
                    COLUMN_QUESTION + " TEXT," +
                    COLUMN_DATE_TIME_IN_MILLS + " INTEGER," +
                    COLUMN_RATING + " INTEGER DEFAULT 0," +
                    "UNIQUE (" + COLUMN_SELECTED_ANSWER + ", " + COLUMN_CORRECT_ANSWER + ", " + COLUMN_QUESTION + ", " + COLUMN_DATE_TIME_IN_MILLS + ")" +
                    ")";


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // handle database upgrade
    }

    public void insertData(Context context, LocalDatabaseModel localDatabaseModel) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SELECTED_ANSWER, localDatabaseModel.getSelectedAnswer());
        values.put(COLUMN_CORRECT_ANSWER, localDatabaseModel.getCorrectAnswer());
        values.put(COLUMN_QUESTION, localDatabaseModel.getQuestion());
        values.put(COLUMN_DATE_TIME_IN_MILLS, localDatabaseModel.getDateTimeInMills());
        values.put(COLUMN_RATING, localDatabaseModel.getRating());

        long rowId = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        if (rowId != -1) {
            // Data was inserted or updated successfully
            Log.i("TAG","inserted");
        } else {
            // Failed to insert or update data
            Log.i("TAG","Not inserted");
        }

        db.close();
    }


/*
    public List<LocalDatabaseModel> getAllQuestionAnswers() {
        List<LocalDatabaseModel> questionAnswers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                MyDatabaseHelper.TABLE_NAME,
                null,
                null,
                null,
                COLUMN_ID,
                null,
                MyDatabaseHelper.COLUMN_DATE_TIME_IN_MILLS + " ASC"
        );
        if (cursor.moveToFirst()) {
            do {
                long retrievedTime = cursor.getLong(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_DATE_TIME_IN_MILLS));
                String question = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_QUESTION));
                String selectedAnswer = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_SELECTED_ANSWER));
                String correctAnswer = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_CORRECT_ANSWER));
                LocalDatabaseModel questionAnswer = new LocalDatabaseModel(selectedAnswer,correctAnswer,question,retrievedTime);
                questionAnswers.add(questionAnswer);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionAnswers;
    }
*/


    public ArrayList<ArrayList<LocalDatabaseModel>> readDataFromDB() {
        ArrayList<ArrayList<LocalDatabaseModel>> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_NAME, null);
        ArrayList<LocalDatabaseModel> itemData = new ArrayList<>();
        int count = 0;
        if (cursor.moveToFirst()) {
            do {
                long retrievedTime = cursor.getLong(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_DATE_TIME_IN_MILLS));
                String question = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_QUESTION));
                String selectedAnswer = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_SELECTED_ANSWER));
                String correctAnswer = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_CORRECT_ANSWER));
                int rating = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_RATING)));
                LocalDatabaseModel data = new LocalDatabaseModel(selectedAnswer, correctAnswer, question, retrievedTime, rating);
                itemData.add(data);
                count++;
                if (count == 5) {
                    dataList.add(itemData);
                    itemData = new ArrayList<>();
                    count = 0;
                }
            } while (cursor.moveToNext());
            if (count > 0) {
                dataList.add(itemData);
            }
        }
        cursor.close();
        return dataList;
    }


}
