package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Restaurant extends PanacheEntity {

    @Column(length = 255)
    public String nom;

    @Column(length = 255)
    public String type_cuisine;
/*
    @Column(length = 255)
    public String adresse;

 */

}