package fr.univ.valenciennes.abouagg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;


public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION_BDD = 2;
    private static final String NOM_BDD = "Contacts.db";
    private static final String TABLE = "mesContacts";
    private static final String ID = "_idContact";
    private static final String NOM = "nomContact";
    private static final String NUMERO = "numContact";
    private static final String EMAIL = "emailContact";
    private static final String ADRESSE = "adresseContact";
    private static final String IMAGE = "imgContact";
    private static final String FACEBOOK = "fbContact";
    private static final String INSTAGRAM = "instaContact";
    private static final String SKYPE = "skypeContact";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NOM_BDD, factory, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                NOM + " TEXT ," + NUMERO + " TEXT ," + EMAIL + " TEXT ," + ADRESSE + " TEXT ," + IMAGE + " TEXT, " + FACEBOOK + " TEXT, " +
                INSTAGRAM + " TEXT , " + SKYPE + " TEXT );";


        /**
         mesContacts.add(new Contact("Abdessamade","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
         mesContacts.add(new Contact("Abu","0666146996","abu@gmail.com","100 Rue Lazare Bernard","img"));
         mesContacts.add(new Contact("Abdessamade","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
         mesContacts.add(new Contact("Abu","0666146996","abu@gmail.com","100 Rue Lazare Bernard","img"));
         mesContacts.add(new Contact("Abdel GFX","075000","abdelgfx2@gmail.com","100 Rue Lazare Bernard","img"));
         mesContacts.add(new Contact("Contact 3","+10075000","contact@gmail.com","100 Rue Lazare Bernard","img"));
         mesContacts.add(new Contact("Contact 3","+10075000","contact@gmail.com","100 Rue Lazare Bernard","img"));
         mesContacts.add(new Contact("GFX","+33075000","abdelgfx2@live.com","100 Rue Lazare Bernard","img"));
         mesContacts.add(new Contact("GFX","+33075000","abdelgfx2@live.com","100 Rue Lazare Bernard","img"));
         **/


        sqLiteDatabase.execSQL(query);
        //ajouterContact(new Contact("Abdessamade","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
        /**sqLiteDatabase.execSQL("INSERT INTO "+TABLE+" VALUES ('Abdessamade','0755060213','samadox@gmail.com','100 Rue Lazare Bernard','img')");
         sqLiteDatabase.execSQL("INSERT INTO "+TABLE+" VALUES ('Abuu','0755060213','abdel@gmail.com','657 Rue Desandrouins','img')");**/

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }

    public void ajouterContact(Contact contact) {

        ContentValues infos = new ContentValues();

        infos.put(NOM, contact.getcNom());
        infos.put(NUMERO, contact.getcNumero());
        infos.put(EMAIL, contact.getcEmail());
        infos.put(ADRESSE, contact.getcAdresse());
        infos.put(IMAGE, contact.getcImage());
        infos.put(FACEBOOK, contact.getcFb());
        infos.put(INSTAGRAM, contact.getcInsta());
        infos.put(SKYPE, contact.getcSkype());

        SQLiteDatabase bdd = this.getWritableDatabase();

        bdd.insert(TABLE, null, infos);

    }


    public void remove(Contact c) {
        /** SQLiteDatabase bdd = getWritableDatabase();
         Cursor cc = bdd.rawQuery("SELECT "+ID+" FROM "+TABLE+" WHERE "+NOM+" LIKE '"+c.getcNom()+"' AND "+NUMERO+" LIKE '"+c.getcNumero()+"'", null);
         cc.close();
         long id =  cc.getInt(cc.getColumnIndex(ID));

         int string = (int) id;
         **/
        SQLiteDatabase bdd = this.getWritableDatabase();
        bdd.delete(TABLE, NOM + " = '" + c.getcNom() + "' AND " + NUMERO + " = '" + c.getcNumero() + "'", null);
        bdd.execSQL("DELETE FROM " + TABLE + " WHERE " + NOM + " LIKE '" + c.getcNom() + "' AND " + NUMERO + " LIKE '" + c.getcNumero() + "' AND " + EMAIL + " LIKE '" + EMAIL + "'");
        bdd.close();
    }

    /**
     * public Contact getContact(String num,String nom) {
     * SQLiteDatabase bdd = getReadableDatabase();
     * <p>
     * Cursor c =  bdd.rawQuery("SELECT * FROM "+TABLE+" WHERE "+NUMERO+"='"+num.trim()+"' AND "+NOM+"='"+nom.trim()+"'",null);
     * <p>
     * c.moveToFirst();
     * <p>
     * c.close();
     * <p>
     * return new Contact(c.getString(c.getColumnIndex(NOM)),
     * c.getString(c.getColumnIndex(NUMERO)),
     * c.getString(c.getColumnIndex(EMAIL)),
     * c.getString(c.getColumnIndex(ADRESSE)),
     * c.getString(c.getColumnIndex(IMAGE)));
     * }
     **/


    public void mettreAjourContact(Contact oldContact, Contact contact) {
        SQLiteDatabase bdd = this.getWritableDatabase();
        bdd.execSQL("UPDATE " + TABLE + " SET " + NOM + "='" + contact.getcNom() + "', " + NUMERO + "='" + contact.getcNumero() + "', "
                + EMAIL + "='" + contact.getcEmail() + "', " + ADRESSE + "='" + contact.getcAdresse() + "', " + IMAGE + "='" + contact.getcImage() + "'," + FACEBOOK + "='" + contact.getcFb() + "', " + INSTAGRAM + "='" + contact.getcInsta() +
                "', " + SKYPE + "='" + contact.getcSkype() + "' WHERE " + NUMERO + "='" + oldContact.getcNumero() +
                "' AND " + NOM + "='" + oldContact.getcNom() + "'");
    }

    public ArrayList<Contact> rechercherContact(String nom) {
        SQLiteDatabase bdd = getReadableDatabase();
        ArrayList<Contact> list = new ArrayList<>();
        Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE + " WHERE " + NOM + " LIKE '" + nom + "%'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(new Contact(c.getString(c.getColumnIndex(NOM)),
                    c.getString(c.getColumnIndex(NUMERO)),
                    c.getString(c.getColumnIndex(EMAIL)),
                    c.getString(c.getColumnIndex(ADRESSE)),
                    c.getString(c.getColumnIndex(IMAGE)),
                    c.getString(c.getColumnIndex(FACEBOOK)),
                    c.getString(c.getColumnIndex(INSTAGRAM)),
                    c.getString(c.getColumnIndex(SKYPE))
            ));
            c.moveToNext();
        }
        c.close();
        //if(list==null) {RechercherContact.TROUVE=false;} else {RechercherContact.TROUVE=true;}
        return list;
    }

    public int getID(Contact c) {
        SQLiteDatabase bdd = getReadableDatabase();
        Cursor cc = bdd.rawQuery("SELECT " + ID + "  FROM " + TABLE + " WHERE " + NOM + " LIKE '" + c.getcNom() + "' AND " + NUMERO + " LIKE '" + c.getcNumero() + "'", null);
        cc.close();
        return cc.getInt(cc.getColumnIndex(ID));

    }

    public ArrayList<Contact> trierMesContacts(ArrayList<Contact> anciensContacts) {
        SQLiteDatabase bdd = getReadableDatabase();
        //if(list==null) {RechercherContact.TROUVE=false;} else {RechercherContact.TROUVE=true;}

        ArrayList<Contact> nouveauxContacts = new ArrayList<>();
        ArrayList<String> nomsDesContacts = new ArrayList<>();
        for (int i = 0; i < anciensContacts.size(); i++) {
            nomsDesContacts.add(anciensContacts.get(i).getcNom());
        }

        Collections.sort(nomsDesContacts);
        Cursor c;
        for (int i = 0; i < nomsDesContacts.size(); i++) {
            c = bdd.rawQuery("SELECT * FROM " + TABLE + " WHERE " + NOM +
                    " = '" + nomsDesContacts.get(i) + "'", null);
            c.moveToFirst();

            nouveauxContacts.add(new Contact(c.getString(c.getColumnIndex(NOM)),
                    c.getString(c.getColumnIndex(NUMERO)),
                    c.getString(c.getColumnIndex(EMAIL)),
                    c.getString(c.getColumnIndex(ADRESSE)),
                    c.getString(c.getColumnIndex(IMAGE)),
                    c.getString(c.getColumnIndex(FACEBOOK)),
                    c.getString(c.getColumnIndex(INSTAGRAM)),
                    c.getString(c.getColumnIndex(SKYPE))
            ));
            c.close();
        }


        return nouveauxContacts;
    }

    public ArrayList<Contact> allContacts() {

        ArrayList<Contact> list = new ArrayList<>();


        // ArrayList<Contact> maListe = new ArrayList<Contact>();
        SQLiteDatabase bdd = getReadableDatabase();

        Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(new Contact(c.getString(c.getColumnIndex(NOM)),
                    c.getString(c.getColumnIndex(NUMERO)),
                    c.getString(c.getColumnIndex(EMAIL)),
                    c.getString(c.getColumnIndex(ADRESSE)),
                    c.getString(c.getColumnIndex(IMAGE)),
                    c.getString(c.getColumnIndex(FACEBOOK)),
                    c.getString(c.getColumnIndex(INSTAGRAM)),
                    c.getString(c.getColumnIndex(SKYPE))
            ));
            c.moveToNext();
        }

        c.close();

        return trierMesContacts(list);
    }

    public void openDataBase() {
        this.openDataBase();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }


}
