package fr.univ.valenciennes.abouagg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    static DBHelper ma_bdd;
    static int OK = 0, DONE = 0;
    SharedPreferences sharedPreferences;
    ArrayList<Contact> mesContacts = new ArrayList<Contact>();
    private ImageButton appel;
    private int cpt = 0;
    private ImageButton ajouter, rechercher;
    private ListView maListView;
    private ContactAdapter monAdapter;
    private ImageButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        monAdapter.notifyDataSetChanged();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ma_bdd = new DBHelper(this, null, null, 1);

        //ma_bdd.ajouterContact(new Contact("Abdessamade","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
        /**ma_bdd.ajouterContact(new Contact("Abduu","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
         ma_bdd.ajouterContact(new Contact("Abduu","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
         ma_bdd.ajouterContact(new Contact("Abduu","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
         ma_bdd.ajouterContact(new Contact("Abduu","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
         ma_bdd.ajouterContact(new Contact("Abduu","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));
         ma_bdd.ajouterContact(new Contact("Abduu","0755060213","samadox@gmail.com","100 Rue Lazare Bernard","img"));**/

        //Cursor cursor = ma_bdd.allContacts();
        mesContacts.addAll(ma_bdd.allContacts());
        //mesContacts= new ArrayList<>();

        //mesContacts.add(new Contact("Contact 3","+10075000","contact@gmail.com","100 Rue Lazare Bernard","img"));


        // if(cursor.moveToFirst() && DONE ==1) {
        /**  ma_bdd.openDataBase();
         while (cursor.moveToFirst()) {
         String nom = cursor.getString(1);
         String num = cursor.getString(2);
         String email = cursor.getString(3);
         String adresse = cursor.getString(4);
         String image = cursor.getString(5);

         mesContacts.add(new Contact(nom, num, email, adresse, image));
         }
         cursor.close();
         ma_bdd.close();**/
        // }


        maListView = findViewById(R.id.maList);
        monAdapter = new ContactAdapter(getApplicationContext(), R.layout.contact, mesContacts);

        if (maListView != null) {
            maListView.setAdapter(monAdapter);
        }

        maListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Toast.makeText(MainActivity.this,"Vous Avez selectionné : "+mesContacts.get(i).getcNom(), Toast.LENGTH_SHORT).show();
                Log.e("huhu", mesContacts.get(i).cNom);

                Intent intent = new Intent(view.getContext(), InfosContact.class);
                intent.putExtra("nom", mesContacts.get(i).getcNom());
                intent.putExtra("numero", mesContacts.get(i).getcNumero());
                intent.putExtra("email", mesContacts.get(i).getcEmail());
                intent.putExtra("adresse", mesContacts.get(i).getcAdresse());
                intent.putExtra("image", mesContacts.get(i).getcImage());
                intent.putExtra("fb", mesContacts.get(i).getcFb());
                intent.putExtra("insta", mesContacts.get(i).getcInsta());
                intent.putExtra("skype", mesContacts.get(i).getcSkype());

                startActivity(intent);
            }
        });

        ajouter = findViewById(R.id.ajouter);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AjouterContact.class);
                startActivity(i);
            }
        });

        if (OK == 1) {

            final Bundle extras = getIntent().getExtras();

            if (extras != null) {

                //mesContacts.add(new Contact(extras.get("nom").toString(), extras.get("numero").toString(), extras.get("email").toString(), extras.get("adresse").toString(), "img"));
                monAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Contact Sauvegardé !", Toast.LENGTH_SHORT).show();
            }
            OK = 0;
        }

        rechercher = findViewById(R.id.rechercherB);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RechercherContact.class);
                startActivity(i);
            }
        });


    }

    public Context getMainContext() {
        return this.getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
