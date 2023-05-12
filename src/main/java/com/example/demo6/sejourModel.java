package com.example.demo6;





public class sejourModel {
    public String lieux;
    public String adresse;
    public int prix;
    public int nbperso;
    public int id_sejour ;

    public String titre;


    public int chambre;

    public int lit;

    public int bain;
    public int id_reservation;
    public String depart;

  public String image;

    public String statut;


    public String description;



    public sejourModel(String lieux, String adresse, int prix, int nbperso) {
        this.lieux =lieux;
        this.adresse = adresse;
        this.prix = prix;
        this.nbperso = nbperso;
    }


    public sejourModel(int id_sejour, String lieux, String adresse, int prix, int nbperso, int chambre, int lit, int bain, String depart,  String description,String titre,String image) {
        this.id_sejour = id_sejour;
        this.lieux = lieux;
        this.adresse = adresse;
        this.prix = prix;
        this.nbperso = nbperso;
        this.chambre = chambre;
        this.lit = lit;
        this.bain = bain;
        this.depart = depart;
        this.description = description;
        this.titre=titre;
        this.image=image;
    }


    public int getId_reservation() {
        return id_reservation;
    }

    public String getStatut() {
        return statut;
    }

    public sejourModel(Integer id_sejour,String titre, String statut, Integer id_reservation ) {
        this.id_sejour = id_sejour;
        this.titre=titre;
        this.statut=statut;
        this.id_reservation=id_reservation;

    }



    public String getLieux() {
        return lieux;
    }

    public void setLieux(String company) {
        this.lieux = company;
    }

    public String getAdresse() {
        return adresse;
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public int getId_sejour() {
        return id_sejour;
    }

    public void setId_sejour(int id_sejour) {
        this.id_sejour = id_sejour;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbperso(int nbperso) {
        this.nbperso = nbperso;
    }

    public int getLit() {
        return lit;
    }

    public void setLit(int lit) {
        this.lit = lit;
    }

    public int getBain() {
        return bain;
    }

    public void setBain(int bain) {
        this.bain = bain;
    }


    public int getNbperso() {
        return nbperso;
    }

    public void setNbperso(Integer nbperso) {
        this.nbperso = nbperso;
    }

    public String getDepart() {
        return depart;
    }

    public void setdepart(String depart) {
        this.depart = depart ;
    }




    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public int getChambre() {
        return chambre;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setChambre(int chambre) {
        this.chambre = chambre;
    }


}