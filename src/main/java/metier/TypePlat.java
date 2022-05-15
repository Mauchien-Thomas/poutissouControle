package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TypePlat extends PanacheEntityBase implements Serializable {

    @Id
    private Long type_plat_id;

    @Column(length = 255, nullable = false)
    public String type_plat;


}
