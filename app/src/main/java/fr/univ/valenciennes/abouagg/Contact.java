package fr.univ.valenciennes.abouagg;

public class Contact {
    public String cNom;
    int _id;
    private String cNumero, cEmail, cAdresse, cImage, cFb, cInsta, cSkype;


    public Contact(String nom, String numero, String email, String adresse, String image, String cFb, String cInsta, String cSkype) {
        this.cNom = nom;
        this.cNumero = numero;
        this.cEmail = email;
        this.cAdresse = adresse;
        this.cImage = image;
        this.cFb = cFb;
        this.cInsta = cInsta;
        this.cSkype = cSkype;
    }

    public String getcNom() {
        return cNom;
    }

    public void setcNom(String cNom) {
        this.cNom = cNom;
    }

    public String getcNumero() {
        return cNumero;
    }

    public void setcNumero(String cNumero) {
        this.cNumero = cNumero;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcAdresse() {
        return cAdresse;
    }

    public void setcAdresse(String cAdresse) {
        this.cAdresse = cAdresse;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public String getcFb() {
        return cFb;
    }

    public void setcFb(String cFb) {
        this.cFb = cFb;
    }

    public String getcInsta() {
        return cInsta;
    }

    public void setcInsta(String cInsta) {
        this.cInsta = cInsta;
    }

    public String getcSkype() {
        return cSkype;
    }

    public void setcSkype(String cSkype) {
        this.cSkype = cSkype;
    }
}
