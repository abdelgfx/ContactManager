package fr.univ.valenciennes.abouagg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class AjouterContact extends Activity {

    //static  DBHelper ma_bdd;
    final static int OK = 1;
    static File f;
    static Uri selectedImage = null;
    private EditText nom, numero, email, adresse, fb, insta, skype;
    private ImageButton valider, annuler, monImage;

    /**
     * @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     * super.onActivityResult(requestCode, resultCode, data);
     * <p>
     * if(requestCode == OK && resultCode == RESULT_OK && data != null) {
     * selectedImage=data.getData();
     * String[]imagePath={MediaStore.Images.Media.DATA};
     * <p>
     * Cursor cursor = getContentResolver().query(
     * selectedImage,imagePath,null,null,null
     * );
     * cursor.moveToFirst();
     * <p>
     * int c = cursor.getColumnIndex(imagePath[0]);
     * String path = cursor.getString(c);
     * cursor.close();
     * <p>
     * f = new File(path);
     * <p>
     * monImage.setImageBitmap(
     * BitmapFactory.decodeFile(path)
     * );
     * }
     * }
     **/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        // ma_bdd = MainActivity.ma_bdd;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_contact);

        valider = findViewById(R.id.ok);
        annuler = findViewById(R.id.back);
        monImage = findViewById(R.id.imgSet);

        nom = findViewById(R.id.setNom);
        numero = findViewById(R.id.setNum);
        email = findViewById(R.id.setEmail);
        adresse = findViewById(R.id.setAdresse);
        fb = findViewById(R.id.setFb);
        insta = findViewById(R.id.setInsta);
        skype = findViewById(R.id.setSkype);
/**
 monImage.setOnClickListener(new View.OnClickListener() {
@Override public void onClick(View view) {
Intent i = new Intent(Intent.ACTION_PICK,
android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
startActivityForResult(i,OK);
}

});
 **/


        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nom.getText().toString().trim().length() == 0 || numero.getText().toString().trim().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AjouterContact.this);
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
                    if ((numero.getText().toString().trim().length() < 10 || numero.getText().toString().trim().length() > 14) || numero.getText().charAt(0) != '0') {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AjouterContact.this);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(AjouterContact.this);
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

                        Intent intent = new Intent(view.getContext(), MainActivity.class);

                        MainActivity.ma_bdd.ajouterContact(new Contact(nom.getText().toString(),
                                numero.getText().toString(),
                                email.getText().toString(),
                                adresse.getText().toString(), "img",
                                fb.getText().toString(),
                                insta.getText().toString(),
                                skype.getText().toString()
                        ));

                        Toast.makeText(view.getContext(), "Contact Sauvegardé !", Toast.LENGTH_LONG).show();

                        MainActivity.OK = 1;
                        //MainActivity.DONE = 1;
                        startActivity(intent);
                    }
                }
            }

        });

        annuler = findViewById(R.id.back);

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(view.getContext(), "L'ajout Est Annulé !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
