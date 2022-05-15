package endpoint;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import metier.Restaurant;
import ressource.RestaurantRessource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Path("/restaurants")
public class RestaurantEndPoint {
    @Inject
    @Location("restaurant.html")
    private Template restaurantTPL;

    @Inject
    @Location("ajout_restaurant.html")
    private Template restaurantAjout;

    @Inject
    private RestaurantRessource restaurantRessource;
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAll(){
        List<Restaurant> restaurant = Restaurant.listAll();
        return restaurantTPL.data("restaurants", restaurant);
    }


    @GET
    @Path("nom/{nom}")
    public Response getByNom(@PathParam("nom") String nom){
        List<Restaurant> restaurant = Restaurant.list("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.nom = ?1 ORDER BY nom "+
                "DESC", nom);
        return Response.ok(restaurant).build();
    }

    @GET
    @Path("type/{type_cuisine}")
    public Response getByTypeCuisine(@PathParam("type_cuisine") String type_cuisine){
        List<Restaurant> restaurant = Restaurant.list("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.type_cuisine = ?1 ORDER BY type_cuisine "+
                "DESC", type_cuisine);
        return Response.ok(restaurant).build();
    }

    @GET
    @Path("creation")
    public TemplateInstance ajout(){
        return restaurantAjout.instance();
    }


    @POST
    @Path("creation/envoi")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response create(@FormParam("nom") String nom, @FormParam("type_cuisine") String typeCuisine, @FormParam("adresse") String adresse){

        Restaurant restaurant = new Restaurant(nom,typeCuisine,adresse);

        List<Restaurant> doublon = Restaurant.list("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.nom = ?1 AND restaurant.adresse = ?2"
                ,restaurant.nom, restaurant.adresse);
        if(doublon.size() == 0){
            restaurant.persist(restaurant);
            if(restaurant.isPersistent()){
               restaurantRessource.addRestaurant(restaurant);
                restaurantTPL.instance();
                return Response.created(URI.create("/restaurants"+restaurant.id)).build();
            }
        }
        restaurantTPL.instance();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
