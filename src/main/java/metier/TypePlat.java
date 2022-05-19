package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TypePlat extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long type_plat_id;

    @Column(length = 255, nullable = false)
    public String type_plat;

    public TypePlat() {
    }

    public TypePlat(Long type_plat_id) {
        this.type_plat_id = type_plat_id;
    }
}
