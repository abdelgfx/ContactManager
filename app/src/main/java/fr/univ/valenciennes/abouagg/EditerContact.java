package fr.univ.valenciennes.abouagg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditerContact extends Activity {
    private EditText nomm, numeroo, email, adresse, fb, insta, skype;
    private ImageButton finish, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editer_contact);

        final Bundle extras = getIntent().getExtras();

        nomm = findViewById(R.id.editNom);
        numeroo = findViewById(R.id.editNum);
        email = findViewById(R.id.editEmail);
        adresse = findViewById(R.id.editAdresse);
        fb = findViewById(R.id.editFb);
        insta = findViewById(R.id.editInsta);
        skype = findViewById(R.id.editSkype);


        if (extras != null) {

            nomm.setText(extras.get("nomEdit").toString());
            numeroo.setText(extras.get("numeroEdit").toString());
            email.setText(extras.get("emailEdit").toString());
            adresse.setText(extras.get("adresseEdit").toString());
            fb.setText(extras.get("fbEdit").toString());
            insta.setText(extras.get("instaEdit").toString());
            skype.setText(extras.get("skypeEdit").toString());

            // int im = this.getResources().getIdentifier(extras.get("image").toString(),"drawable",this.getPackageName());
            // image.setImageResource(im);

        }

        final Contact ancienContact = new Contact(
                nomm.getText().toString(),
                numeroo.getText().toString(),
                email.getText().toString(),
                adresse.getText().toString(),
                "img",
                fb.getText().toString(),
                insta.getText().toString(),
                skype.getText().toString()
        );


        finish = findViewById(R.id.finichEdit);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nomm.getText().toString().trim().length() == 0 || numeroo.getText().toString().trim().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditerContact.this);
                    builder.setCancelable(false);

                    builder.setTitle("Erreur !");
                    builder.setMessage("Vous devez rentrer au moins le Nom du contact et le Numéro SVP !");
                    builder.setPositiveButton("J'ai compris", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                } else {
                    if ((numeroo.getText().toString().trim().length() < 10 || numeroo.getText().toString().trim().length() > 14) || numeroo.getText().charAt(0) != '0') {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditerContact.this);
                        builder.setCancelable(false);

                        builder.setTitle("Erreur !");
                        builder.setMessage("Vous devez rentrer un numéro de téléphone valide SVP !");
                        builder.setPositiveButton("J'ai compris", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.show();
                    } else if (email.getText().toString().trim().length() > 0 && (!email.getText().toString().contains("@") || !email.getText().toString().contains("."))) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditerContact.this);
                        builder.setCancelable(false);

                        builder.setTitle("Erreur !");
                        builder.setMessage("Vous devez rentrer une adresse email valide SVP !");
                        builder.setPositiveButton("J'ai compris", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.show();
                    } else {

                        Contact nouveauContact = new Contact(
                                nomm.getText().toString(),
                                numeroo.getText().toString(),
                                email.getText().toString(),
                                adresse.getText().toString(),
                                "img",
                                fb.getText().toString(),
                                insta.getText().toString(),
                                skype.getText().toString()
                        );


                        MainActivity.ma_bdd.mettreAjourContact(ancienContact, nouveauContact);

                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(view.getContext(), "Contact Modifié !", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        cancel = findViewById(R.id.cancelEdit);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(view.getContext(), "La Modification Est Annulée !", Toast.LENGTH_LONG).show();
            }
        });


    }
}
