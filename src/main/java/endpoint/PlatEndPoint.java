package endpoint;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import metier.Plat;
import metier.Restaurant;
import metier.TypePlat;
import ressource.PlatRessource;
import ressource.RestaurantRessource;
import ressource.TypePlatRessource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Path("/plats")
public class PlatEndPoint {

    @Inject
    @Location("platRestaurant.html")
    private Template platTPL;

    @Inject
    @Location("ajout_plat.html")
    private Template platAjout;

    @Inject
    private PlatRessource platRessource;

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPlat(@PathParam("id") Long id){
        List<Plat> plats = Plat.list("SELECT plat FROM Plat plat WHERE plat.restaurant.restaurant_id = ?1 GROUP BY plat.type_plat.type_plat_id", id);
        Restaurant restaurant = new Restaurant();

        for(Plat plat:plats){
            restaurant.setRestaurant_id(plat.getRestaurant().restaurant_id);
            restaurant.setNom(plat.getRestaurant().nom);
            restaurant.setType_cuisine(plat.getRestaurant().type_cuisine);
            restaurant.setAdresse(plat.getRestaurant().adresse);
        }

        return platTPL.data("plats", plats, "restaurant", restaurant);
    }

    @GET
    @Path("ajout_plat/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance ajout(@PathParam("id") Long id){
        TypePlatRessource typePlatRessource = new TypePlatRessource();
        List<TypePlat> typePlats = new ArrayList<>();
        typePlats = typePlatRessource.getAll();

        return platAjout.data("typePlats",typePlats,"id",id);
    }

    @GET
    @Transactional
    @Path("supprimer/{id}")
    public void deleteById(@PathParam("id") Long id) {
        System.out.println(id);
        Plat.delete("DELETE plat FROM Plat plat WHERE plat.plat_id = ?1", id);

    }
    @POST
    @Path("creation/envoi")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public TemplateInstance create(
            @FormParam("nom") String nom, @FormParam("prix") Long prix,
            @FormParam("type_plat") Long typePlat, @FormParam("id_restaurant")Long id_restaurant){

        Restaurant restaurant = new Restaurant(id_restaurant);
        TypePlat typePlats = new TypePlat(typePlat);
        Plat plat = new Plat(typePlats, restaurant, nom, prix);
        platRessource.addPlat(plat);
        return getPlat(id_restaurant);
    }


}
