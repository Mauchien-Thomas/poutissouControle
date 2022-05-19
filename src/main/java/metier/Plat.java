package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plat extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long plat_id;

    @ManyToOne
    @JoinColumn(name = "type_plat_id")
    public TypePlat type_plat;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant restaurant;



   @Column(name="nom_plat")
   public String nom;

   @Column(name="prix_plat")
   public Long prix;


    public Plat() {
    }

    public Plat(TypePlat type_plat, Restaurant restaurant, String nom, Long prix) {
        this.type_plat = type_plat;
        this.restaurant = restaurant;
        this.nom = nom;
        this.prix = prix;
    }

    public Long getPlat_id() {
        return plat_id;
    }

    public void setPlat_id(Long plat_id) {
        this.plat_id = plat_id;
    }

    public TypePlat getType_plat() {
        return type_plat;
    }

    public void setType_plat(TypePlat type_plat) {
        this.type_plat = type_plat;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getPrix() {
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }
}
