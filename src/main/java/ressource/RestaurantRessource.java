package ressource;


import metier.Restaurant;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
@ApplicationScoped
@Transactional(REQUIRED)
public class RestaurantRessource {



    public Restaurant addRestaurant(@Valid Restaurant restaurant) {
        restaurant.persist();
        return restaurant;
    }



  /*
    public void updateRestaurant(Restaurant restaurant) {
        entityManager.merge(restaurant);
    }


    public void deleteRestaurant(Long id) {
        Restaurant restaurant = getRestaurant(id);
        entityManager.remove(restaurant);
    }

    */

}
