package com.tabian.saveanddisplaysql;

public class Produkt {
    String name;
    int anzahl;
    double preis;
    int id;

    public Produkt(String name, int anzahl, double preis, int id) {
        this.name = name;
        this.anzahl = anzahl;
        this.preis = preis;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "name='" + name + '\'' +
                ", anzahl=" + anzahl +
                ", preis=" + preis +
                ", id=" + id +
                '}';
    }
}
