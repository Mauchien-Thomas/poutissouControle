package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Restaurant extends PanacheEntity {

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
}
