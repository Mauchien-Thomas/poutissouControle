package metier;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plat extends PanacheEntity {

    @OneToMany
    @JoinTable(name = "TypePlat", joinColumns = { @JoinColumn(name = "type_plat") })
    private List<TypePlat> typePlat = new ArrayList<>();
}
