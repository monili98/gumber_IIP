package edu.ktu.guessthenumber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ResultsDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "results.db"; //joje gali buti daug lentuciu

    private static String TABLE_NAME = "resultsList"; //rezultatu lentuke

    private final static String KEY_ID = "id"; //id stulpelis
    private final static String KEY_NAME = "name";
    private final static String KEY_DIFFICULTY = "difficulty";
    private final static String KEY_AGE = "age";
    private final static String KEY_GUESSEDNUMBER = "guessedNumber";
    private final static String KEY_TURNSCOUNT = "turnsCount";

    public ResultsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_DIFFICULTY + " TEXT," +
                KEY_AGE + " INTEGER," +
                KEY_GUESSEDNUMBER + " INTEGER," +
                KEY_TURNSCOUNT + " INTEGER" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    //prideti duomenu(Naudojam GameOverActivity'je kai laimima)
    public long addData(PersonData data)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, data.getName());
        cv.put(KEY_DIFFICULTY, data.getDifficulty());
        cv.put(KEY_AGE, data.getAge());
        cv.put(KEY_GUESSEDNUMBER, data.getGuessedNumber());
        cv.put(KEY_TURNSCOUNT, data.getTurnsCount());

        long id = db.insert(TABLE_NAME, null, cv);
        db.close();
        return id;
    }

    //paimti duomenis
    public PersonData getData(int id)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;

        cursor = db.query(TABLE_NAME, new String[] { KEY_ID, KEY_NAME, KEY_DIFFICULTY, KEY_AGE, KEY_GUESSEDNUMBER, KEY_TURNSCOUNT}, KEY_ID + "=?", new String[] { Integer.toString(id) }, null, null, null);

        PersonData data = new PersonData();
        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                data.setID(cursor.getInt(0));
                data.setName(cursor.getString(1));
                data.setDifficulty(cursor.getString(2));
                data.setAge(cursor.getInt(3));
                data.setGuessedNumber(cursor.getInt(4));
                data.setTurnsCount(cursor.getInt(5));
            }
        }

        cursor.close();
        db.close();

        return data;
    }

    public ArrayList<PersonData> getAllData() //paimame visa lentele is duombazes
    {
        ArrayList<PersonData> data = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase(); //nuskaityti nuo duombazes

        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_TURNSCOUNT + " ASC"; //paima is lenteles(rezultatu) ir surikiuoja didejancia tvarka pagal spejimu kieki

        Cursor cursor = db.rawQuery(query, null); //vykdo query eilute(uzklausa)

        if(cursor != null) //ar lentele nera tuscia
        {
            if(cursor.moveToFirst()) //jeigu pavyksta pradeti nuo lenteles pradzios
            {
                do {
                    PersonData data1 = new PersonData(); //sukuria nauja persondata kintamaji(su tusciu konstruktoriumi)
                    data1.setID(cursor.getInt(0)); //po viena pasetina reiksmes pagal stulpei
                    data1.setName(cursor.getString(1));
                    data1.setDifficulty(cursor.getString(2));
                    data1.setAge(cursor.getInt(3));
                    data1.setGuessedNumber(cursor.getInt(4));
                    data1.setTurnsCount(cursor.getInt(5));
                    data.add(data1); //pridedam viena persondata objekta i arraylista virsuj sukurta
                } while(cursor.moveToNext()); //kol yra sekanti eilute duombazeje
            }
        }

        cursor.close();
        db.close();

        return data; //grazina visa sarasa - arraylista
    }
}
