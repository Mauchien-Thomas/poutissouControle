package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long restaurant_id;
    @Column(length = 255, nullable = false)
    public String nom;

    @Column(length = 255, nullable = false)
    public String type_cuisine;

    @Column(length = 255, nullable = false)
    public String adresse;

    public Restaurant(String nom, String type_cuisine, String adresse) {
        this.nom = nom;
        this.type_cuisine = type_cuisine;
        this.adresse = adresse;
    }

    public Restaurant() {

    }

    public Restaurant(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType_cuisine() {
        return type_cuisine;
    }

    public void setType_cuisine(String type_cuisine) {
        this.type_cuisine = type_cuisine;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
