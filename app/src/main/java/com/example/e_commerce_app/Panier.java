package com.example.e_commerce_app;

public class Panier {
    private String nom_cours;
    private String fichier;
    private String image;
    private float prix;

    public Panier(){}

    public Panier(String nom_cours, float prix, String image){
        this.image = image;
        this.nom_cours = nom_cours;
        this.prix = prix;
    }

    public void setNom_cours(String nom_cours){
        this.nom_cours = nom_cours;
    }
    public String getNom_cours(){
        return this.nom_cours;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
