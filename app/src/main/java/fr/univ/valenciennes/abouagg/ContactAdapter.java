package fr.univ.valenciennes.abouagg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    MainActivity m = new MainActivity();
    int monLayout;
    ArrayList<Contact> mesContacts;
    private TextView nomm, numeroo, email, adresse;
    private ImageButton delete, appel, edit;
    private Context monContext;

    public ContactAdapter(Context monContext, int monLayout, ArrayList<Contact> mesContacts) {
        super(monContext, monLayout, mesContacts);
        this.monContext = monContext;
        this.monLayout = monLayout;
        this.mesContacts = mesContacts;
    }

    @Override
    public View getView(final int position, final View nouvelleView, final ViewGroup parent) {
        View unContact = nouvelleView;
        LayoutInflater i = LayoutInflater.from(monContext);
        unContact = i.inflate(monLayout, parent, false);

        TextView nom = unContact.findViewById(R.id.monNom);
        TextView numero = unContact.findViewById(R.id.monNum);
        ImageView img = unContact.findViewById(R.id.monImg);

        Contact c = mesContacts.get(position);

        nom.setText(c.getcNom());
        numero.setText(c.getcNumero());
        int image = monContext.getResources().getIdentifier(c.getcImage(), "drawable", monContext.getPackageName());
        img.setImageResource(image);

        final View ccc = unContact;

        delete = unContact.findViewById(R.id.supprimer);
        delete.setTag(position);
        final LayoutInflater ii = i;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                //dialogInterface.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                MainActivity.ma_bdd.remove(mesContacts.get(position));
                notifyDataSetChanged();
                mesContacts.remove(position);
                notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Contact Supprimé !", Toast.LENGTH_LONG).show();


            }
        });

        appel = unContact.findViewById(R.id.appel);
        //final Bundle extras = getIntent().getExtras();
        appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup vg = parent;
                Intent appelIntent = new Intent(Intent.ACTION_CALL);
                appelIntent.setData(Uri.parse("tel:" + mesContacts.get(position).getcNumero()));
                vg.getContext().startActivity(appelIntent);
            }
        });

        edit = unContact.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup vg = parent;
                // TO DO

                Intent intent = new Intent(view.getContext(), EditerContact.class);

                intent.putExtra("nomEdit", mesContacts.get(position).getcNom());
                intent.putExtra("numeroEdit", mesContacts.get(position).getcNumero());
                intent.putExtra("emailEdit", mesContacts.get(position).getcEmail());
                intent.putExtra("adresseEdit", mesContacts.get(position).getcAdresse());
                intent.putExtra("fbEdit", mesContacts.get(position).getcFb());
                intent.putExtra("instaEdit", mesContacts.get(position).getcInsta());
                intent.putExtra("skypeEdit", mesContacts.get(position).getcSkype());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                vg.getContext().startActivity(intent);
            }
        });

        return unContact;
    }

    @Nullable
    @Override
    public Contact getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Contact item) {
        return super.getPosition(item);
    }
}
