package ressource;

import metier.Plat;
import metier.Restaurant;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Transactional(REQUIRED)
public class PlatRessource {

    public Plat addPlat(@Valid Plat plat) {
        plat.persist();
        return plat;
    }
}
