package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TypePlat extends PanacheEntity {

    @Column(length = 255, nullable = false)
    public String type_plat;
}
