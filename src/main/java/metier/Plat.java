package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plat extends PanacheEntityBase implements Serializable {

    @Id
    private Long plat_id;

    @OneToMany(mappedBy = "type_plat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TypePlat> type_plat = new ArrayList<>();

   @Column(name="nom_plat")
   private String nom;

   @Column(name="prix_plat")
   private Long prix;
}
