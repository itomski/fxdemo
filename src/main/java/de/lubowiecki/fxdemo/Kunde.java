package de.lubowiecki.fxdemo;

import java.io.Serializable;

public class Kunde implements Serializable {

    private String vorname;

    private String nachname;

    private String email;

    public Kunde(String vorname, String nachname, String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(vorname);
        sb.append(' ')
            .append(nachname)
            .append(", ")
            .append(email)
            .append("\n");
        return sb.toString();
    }
}
