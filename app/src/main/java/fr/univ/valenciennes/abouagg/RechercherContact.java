package fr.univ.valenciennes.abouagg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RechercherContact extends Activity {
    static boolean TROUVE = true;
    private Button search, cancel;
    private ListView maListView;
    private EditText monContact;
    private ArrayList<Contact> res = new ArrayList<Contact>();
    private ContactAdapter monAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercher_contact);

        search = findViewById(R.id.search);
        monContact = findViewById(R.id.contactSearch);
        maListView = findViewById(R.id.listRecherche);
        cancel = findViewById(R.id.cancelSearch);

        //res.add(new Contact("Abdessamade","0755060213","abdelgfx2@gmail.com","100 Rue Lazare Bernard","img"));


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res = new ArrayList<Contact>();
                res.addAll(MainActivity.ma_bdd.rechercherContact(monContact.getText().toString()));

                //if(res.size()!=0) {
                //res.addAll(MainActivity.ma_bdd.rechercherContact(monContact.getText().toString()));

                if (res.size() > 0) {
                    monAdapter = new ContactAdapter(getApplicationContext(), R.layout.contact, res);
                    if (maListView != null) {
                        maListView.setAdapter(monAdapter);
                    }
                    monAdapter.notifyDataSetChanged();
                    // res = new ArrayList<Contact>();
                } else {
                    res = new ArrayList<Contact>();
                    monAdapter = new ContactAdapter(getApplicationContext(), R.layout.contact, res);
                    if (maListView != null) {
                        maListView.setAdapter(monAdapter);
                    }
                    monAdapter.notifyDataSetChanged();
                    Toast.makeText(RechercherContact.this, "Aucun Contact Trouvé ..!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        maListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), InfosContact.class);

                intent.putExtra("nom", res.get(i).getcNom());
                intent.putExtra("numero", res.get(i).getcNumero());
                intent.putExtra("email", res.get(i).getcEmail());
                intent.putExtra("adresse", res.get(i).getcAdresse());
                intent.putExtra("image", res.get(i).getcImage());
                intent.putExtra("fb", res.get(i).getcFb());
                intent.putExtra("insta", res.get(i).getcInsta());
                intent.putExtra("skype", res.get(i).getcSkype());

                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
