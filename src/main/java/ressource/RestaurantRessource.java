package ressource;

import metier.Restaurant;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/restaurants")
public class RestaurantRessource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Restaurant> restaurant = Restaurant.listAll();
        return Response.ok(restaurant).build();
    }

    @GET
    @Path("nom/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByNom(@PathParam("nom") String nom){
       List<Restaurant> restaurant = Restaurant.list("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.nom = ?1 ORDER BY nom "+
               "DESC", nom);
       return Response.ok(restaurant).build();
    }

    @GET
    @Path("type/{type_cuisine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTypeCuisine(@PathParam("type_cuisine") String type_cuisine){
        List<Restaurant> restaurant = Restaurant.list("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.type_cuisine = ?1 ORDER BY type_cuisine "+
                "DESC", type_cuisine);
        return Response.ok(restaurant).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Restaurant restaurant){
        restaurant.persist(restaurant);
        if(restaurant.isPersistent()){
            return Response.created(URI.create("/restaurants"+restaurant.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
