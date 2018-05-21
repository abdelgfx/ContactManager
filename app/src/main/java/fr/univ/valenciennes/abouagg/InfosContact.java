package fr.univ.valenciennes.abouagg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InfosContact extends Activity {

    TextView nom, tel, adresse, mail, mailRien, adresseRien, fb, insta, skype, fbRien, instaRien, skypeRien;
    ImageView image;
    ImageButton appeler, message, gps, email, annuler, edit;
    Button pageFb, pageInsta, pageSkype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_contact);
        final Bundle extras = getIntent().getExtras();

        edit = findViewById(R.id.editInfo);

        mailRien = findViewById(R.id.mailNothing);
        adresseRien = findViewById(R.id.adresseNothing);
        nom = findViewById(R.id.nomRes);
        tel = findViewById(R.id.numRes);
        mail = findViewById(R.id.mailRes);
        adresse = findViewById(R.id.adresseRes);
        gps = findViewById(R.id.gps);

        fb = findViewById(R.id.fbRes);
        fbRien = findViewById(R.id.fbNothing);
        insta = findViewById(R.id.instaRes);
        instaRien = findViewById(R.id.instaNothing);
        skype = findViewById(R.id.skypeRes);
        skypeRien = findViewById(R.id.skypeNothing);
        image = findViewById(R.id.imgRes);

        pageFb = findViewById(R.id.btnFb);
        pageInsta = findViewById(R.id.btnInsta);
        pageSkype = findViewById(R.id.btnSkype);

        if (extras != null) {

            nom.setText(extras.get("nom").toString());
            tel.setText(extras.get("numero").toString());
            if (extras.get("email").toString().trim().length() == 0) {
                mailRien.setText("Pas d'email !");
            } else {
                mail.setText(extras.get("email").toString());
            }

            if (extras.get("adresse").toString().trim().length() == 0) {
                adresseRien.setText("Pas d'adresse !");
            } else {
                adresse.setText(extras.get("adresse").toString());
            }

            if (extras.get("fb").toString().trim().length() == 0) {
                fbRien.setText("Pas de Facebook !");
            } else {
                fb.setText(extras.get("fb").toString());
            }

            if (extras.get("insta").toString().trim().length() == 0) {
                instaRien.setText("Pas d'Instagram !");
            } else {
                insta.setText(extras.get("insta").toString());
            }

            if (extras.get("skype").toString().trim().length() == 0) {
                skypeRien.setText("Pas de Skype !");
            } else {
                skype.setText(extras.get("skype").toString());
            }

            int im = this.getResources().getIdentifier(extras.get("image").toString(), "drawable", this.getPackageName());
            image.setImageResource(im);


        }

        appeler = findViewById(R.id.appl);
        message = findViewById(R.id.msg);
        email = findViewById(R.id.send_email);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditerContact.class);

                intent.putExtra("nomEdit", nom.getText().toString());
                intent.putExtra("numeroEdit", tel.getText().toString());
                intent.putExtra("emailEdit", mail.getText().toString());
                intent.putExtra("adresseEdit", adresse.getText().toString());
                intent.putExtra("fbEdit", fb.getText().toString());
                intent.putExtra("instaEdit", insta.getText().toString());
                intent.putExtra("skypeEdit", skype.getText().toString());


                startActivity(intent);
            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //com.google.android.apps.maps
                if (appExiste("com.google.android.apps.maps", view.getContext())) {
                    if (adresse.getText().toString().trim().length() == 0) {
                        Toast.makeText(InfosContact.this, "Adresse Introuvable !", Toast.LENGTH_LONG).show();
                    } else {
                        String mapGps = "google.navigation:q=" + adresse.getText().toString();
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(mapGps));
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(InfosContact.this, "Installez Google Maps Pour Continuer", Toast.LENGTH_LONG).show();
                }
            }
        });

        appeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appelIntent = new Intent(Intent.ACTION_CALL);
                appelIntent.setData(Uri.parse("tel:" + extras.get("numero").toString()));
                startActivity(appelIntent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent messageIntent = new Intent(Intent.ACTION_VIEW);
                messageIntent.setData(Uri.parse("sms:" + extras.get("numero").toString()));
                startActivity(messageIntent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mail.getText().toString().trim().length() == 0 || !mail.getText().toString().contains("@")) {
                    Toast.makeText(InfosContact.this, "Erreur ! Email Introuvable.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    if (!appExiste("com.google.android.gm", view.getContext())) {
                        Toast.makeText(InfosContact.this, "Vous N'avez Aucune App Pour Envoyer Le Mail !", Toast.LENGTH_LONG).show();
                    } else {
                        emailIntent.setData(Uri.parse("mailto:" + extras.get("email").toString()));
                        startActivity(emailIntent);
                    }
                }
            }
        });

        pageFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (insta.getText().toString().trim().length() > 0) {
                    Intent fbIntent = new Intent(Intent.ACTION_VIEW);
                    fbIntent.setData(Uri.parse("https://www.facebook.com/search/top/?q=" + fb.getText().toString()));
                    startActivity(fbIntent);
                } else {
                    Toast.makeText(InfosContact.this, "Ce Contact N'a Pas De Compte Facebook", Toast.LENGTH_LONG).show();
                }
            }
        });

        pageInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (insta.getText().toString().trim().length() > 0) {
                    Intent instaIntent = new Intent(Intent.ACTION_VIEW);
                    if (appExiste("com.instagram.android", view.getContext())) {
                        instaIntent.setData(Uri.parse("http://www.instagram.com/_u/" + insta.getText().toString()));
                        instaIntent.setPackage("com.instagram.android");
                        startActivity(instaIntent);
                    } else {

                        Toast.makeText(InfosContact.this, "Installez Instagram Pour Continuer !", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(InfosContact.this, "Ce Contact N'a Pas De Compte Instagram", Toast.LENGTH_LONG).show();
                }
            }
        });

        pageSkype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (skype.getText().toString().trim().length() > 0) {
                    Intent skypeIntent = new Intent(Intent.ACTION_VIEW);
                    if (appExiste("com.skype.raider", view.getContext())) {
                        skypeIntent.setData(Uri.parse("skype:" + skype.getText().toString()));
                        startActivity(skypeIntent);

                    } else {
                        Toast.makeText(InfosContact.this, "Installez Skype Pour Continuer !", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(InfosContact.this, "Ce Contact N'a Pas De Compte Skype", Toast.LENGTH_LONG).show();
                }


            }
        });

        annuler = findViewById(R.id.baack);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
            }
        });


    }

    public boolean appExiste(String packageName, Context context) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
