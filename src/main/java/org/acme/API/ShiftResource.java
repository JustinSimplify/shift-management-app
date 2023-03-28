package org.acme.API;

import io.quarkus.security.Authenticated;
import org.acme.Entity.Shift;
import org.acme.Entity.ShiftCreateDTO;
import org.acme.Entity.ShiftDTO;
import org.acme.Service.ShiftService;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/shifts")
@SecurityRequirement(name = "Keycloak")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShiftResource {

    @Inject
    ShiftService shiftService;

    @GET
    public Response listAll() {
        List<ShiftDTO> shifts = shiftService.listAll();
        return Response.ok(shifts).build();
    }

    @GET
    @Path("/{id}")
    public Response getShift(@PathParam("id") UUID id) {
        ShiftDTO shiftDTO = shiftService.findById(id);
        if (shiftDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(shiftDTO).build();
    }

    @POST
    public Response createShift(ShiftCreateDTO shiftCreateDTO) {
        Shift shift = shiftService.createShift(shiftCreateDTO);
        return Response.status(Response.Status.CREATED).entity(shift).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateShift(@PathParam("id") UUID id, ShiftDTO shiftDTO) {
        ShiftDTO shiftDTOres = shiftService.updateShift(id, shiftDTO);
        if (shiftDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(shiftDTOres).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteShift(@PathParam("id") UUID id) {
        ShiftDTO shiftDTO = shiftService.findById(id);
        if (shiftDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        shiftService.deleteShift(id);
        return Response.noContent().build();
    }
}
