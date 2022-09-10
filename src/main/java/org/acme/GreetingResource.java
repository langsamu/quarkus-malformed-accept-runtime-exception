package org.acme;

import static javax.ws.rs.core.MediaType.*;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
class GreetingResource {

    /* FAIL */

    @GET
    @Path("multipleResponseNonNullOtherMimes")
    @Produces({APPLICATION_SVG_XML, APPLICATION_XML})
    public Response multipleResponseNonNullOtherMimes() {
        return Response.ok().entity("").build();
    }

    @GET
    @Path("multipleResponseNonNull")
    @Produces({TEXT_PLAIN, TEXT_HTML})
    public Response multipleResponseNonNull() {
        return Response.ok().entity("").build();
    }

    @GET
    @Path("multipleStringNonNull")
    @Produces({TEXT_PLAIN, TEXT_HTML})
    public String multipleStringNonNull() {
        return "";
    }

    @GET
    @Path("multipleUniNonNull")
    @Produces({TEXT_PLAIN, TEXT_HTML})
    public Uni<String> multipleUniNonNull() {
        return Uni.createFrom().item("");
    }

    /* SUCCEED */

    @GET
    @Path("singleResponseNonNull")
    @Produces({TEXT_PLAIN})
    public Response singleResponseNonNull() {
        return Response.ok().entity("").build();
    }

    @GET
    @Path("multipleResponseNull")
    @Produces({TEXT_PLAIN, TEXT_HTML})
    public Response multipleResponseNull() {
        return null;
    }

    @GET
    @Path("multipleResponseNoEntity")
    @Produces({TEXT_PLAIN, TEXT_HTML})
    public Response multipleResponseNoEntity() {
        return Response.ok().build();
    }

    @GET
    @Path("multipleStringNull")
    @Produces({TEXT_PLAIN, TEXT_HTML})
    public String multipleStringNull() {
        return null;
    }

    @GET
    @Path("multipleUniNullItem")
    @Produces({TEXT_PLAIN, TEXT_HTML})
    public Uni<String> multipleUniNullItem() {
        return Uni.createFrom().nullItem();
    }

    @GET
    @Path("multipleUniNull")
    @Produces({TEXT_PLAIN, TEXT_HTML})
    public Uni<String> multipleUniNull() {
        return null;
    }

    @GET
    @Path("singleStringNonNull")
    @Produces({TEXT_PLAIN})
    public String singleStringNonNull() {
        return "";
    }

    @GET
    @Path("singleStringNull")
    @Produces({TEXT_PLAIN})
    public String singleStringNull() {
        return null;
    }

    @GET
    @Path("singleUniNonNull")
    @Produces({TEXT_PLAIN})
    public Uni<String> singleUniNonNull() {
        return Uni.createFrom().item("");
    }

    @GET
    @Path("singleUniNull")
    @Produces({TEXT_PLAIN})
    public Uni<String> singleUniNull() {
        return null;
    }
}
