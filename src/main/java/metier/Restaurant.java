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
/*
    @OneToMany(targetEntity = Plat.class, mappedBy = "restaurant")
    public List<Plat> liste_plats = new ArrayList<>();

 */

    public Restaurant(String nom, String type_cuisine, String adresse) {
        this.nom = nom;
        this.type_cuisine = type_cuisine;
        this.adresse = adresse;
    }

    public Restaurant() {

    }
}
