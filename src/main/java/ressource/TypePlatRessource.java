package ressource;

import metier.TypePlat;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(REQUIRED)
public class TypePlatRessource {

    public List<TypePlat> getAll(){
        List<TypePlat> typePlats = new ArrayList<>();
        return typePlats = TypePlat.list("SELECT typeplat FROM TypePlat typeplat");
    }
}
