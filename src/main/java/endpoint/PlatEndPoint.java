package endpoint;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import metier.Plat;
import metier.Restaurant;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Path("/plats")
public class PlatEndPoint {

    @Inject
    @Location("platRestaurant.html")
    private Template platTPL;


    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPlat(@PathParam("id") Long id){
        List<Plat> plats = Plat.list("SELECT plat FROM Plat plat WHERE plat.restaurant.restaurant_id = ?1 GROUP BY plat.type_plat.type_plat_id", id);
        for(Plat plat:plats){
            System.out.println(plat.getRestaurant().nom);
        }
        return platTPL.data("plats", plats);
    }
}
