package com.mycompany.hateoas.appeals.dmisra2.netbeans;

import activities.CreateAppealActivity;
import activities.CreateGradeActivity;
import activities.FollowUpActivity;
import activities.InvalidAppealException;
import activities.NoSuchAppealException;
import activities.ReadAppealActivity;
import activities.SubmitAppealActivity;
import activities.UpdateAppealActivity;
import activities.UpdateGradeActivity;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.lang.StringUtils;
import representation.AppealRepresentation;
import representation.GradebookUri;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("appeal")
public class AppealResource {
    
    private @Context UriInfo uriInfo;
    
    public AppealResource()
    {
    }
    
    public AppealResource(UriInfo uriInfo) 
    {
        this.uriInfo = uriInfo;  
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/vnd-cse564-appeals+xml")
    public Response getIt()
    {
        Response response;
        
        try
        {
            AppealRepresentation responseRepresentation = new ReadAppealActivity().retrieveByUri(new GradebookUri(uriInfo.getRequestUri()));
            response = Response.status(Response.Status.OK).entity(responseRepresentation).build();
        }
        catch(NoSuchAppealException e)
        { 
           response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        catch(Exception e)
        {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    
    @POST
    @Path("/creategrade")
    @Consumes("application/vnd-cse564-appeals+xml")
    @Produces("application/vnd-cse564-appeals+xml")
    public Response postIt(String appealRepresentation)
    {
        Response response;
        
        try
        {
            AppealRepresentation responseRepresentation = new CreateGradeActivity().create(AppealRepresentation.fromXmlString(appealRepresentation).getAppeal(), new GradebookUri(uriInfo.getRequestUri()));
            response = Response.status(Response.Status.OK).entity(responseRepresentation).build();
        }
        catch(RuntimeException e)
        { 
           response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        catch(Exception e)
        {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    
    @PUT
    @Path("/updategrade/{id}")
    @Consumes("application/vnd-cse564-appeals+xml")
    @Produces("application/vnd-cse564-appeals+xml")
    public Response putGrade(String appealRepresentation)
    {
        Response response;
        
        try
        {
            AppealRepresentation responseRepresentation = new UpdateGradeActivity().updateGrade(AppealRepresentation.fromXmlString(appealRepresentation).getAppeal(), new GradebookUri(uriInfo.getRequestUri()));
            response = Response.status(Response.Status.OK).entity(responseRepresentation).build();
        }
        catch(RuntimeException e)
        { 
           response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        catch(Exception e)
        {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    
    @PUT
    @Path("/updateappeal/{id}")
    @Consumes("application/vnd-cse564-appeals+xml")
    @Produces("application/vnd-cse564-appeals+xml")
    public Response putAppeal(String appealRepresentation)
    {
        Response response;
        
        try
        {
            AppealRepresentation responseRepresentation = new UpdateAppealActivity().updateAppeal(AppealRepresentation.fromXmlString(appealRepresentation).getAppeal(), new GradebookUri(uriInfo.getRequestUri()));
            response = Response.status(Response.Status.OK).entity(responseRepresentation).build();
        }
        catch(RuntimeException e)
        { 
           response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        catch(Exception e)
        {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    
    @PUT
    @Path("/deleteappeal/{id}")
    @Consumes("application/vnd-cse564-appeals+xml")
    @Produces("application/vnd-cse564-appeals+xml")
    public Response deleteAppeal(String appealRepresentation)
    {
        Response response;
        
        try
        {
            AppealRepresentation responseRepresentation = new UpdateAppealActivity().deleteAppeal(AppealRepresentation.fromXmlString(appealRepresentation).getAppeal(), new GradebookUri(uriInfo.getRequestUri()));
            response = Response.status(Response.Status.OK).entity(responseRepresentation).build();
        }
        catch(RuntimeException e)
        { 
           response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        catch(Exception e)
        {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    
    @PUT
    @Path("/submitappeal/{id}")
    @Consumes("application/vnd-cse564-appeals+xml")
    @Produces("application/vnd-cse564-appeals+xml")
    public Response submitGrade(String appealRepresentation)
    {
        Response response;
        
        try
        {
            AppealRepresentation responseRepresentation = new SubmitAppealActivity().submitAppeal(AppealRepresentation.fromXmlString(appealRepresentation).getAppeal(), new GradebookUri(uriInfo.getRequestUri()));
            response = Response.status(Response.Status.OK).entity(responseRepresentation).build();
        }
        catch(RuntimeException e)
        { 
           response = Response.status(Response.Status.BAD_REQUEST).build();
        }
        catch(Exception e)
        {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

    @PUT
    @Path("/followup/{id}")
    @Consumes("application/vnd-cse564-appeals+xml")
    @Produces("application/vnd-cse564-appeals+xml")
    public Response followUp(String appealRepresentation)
    {
        Response response;
        
        try
        {
            AppealRepresentation responseRepresentation = new FollowUpActivity().followUp(new GradebookUri(uriInfo.getRequestUri()));
            response = Response.status(Response.Status.OK).entity(responseRepresentation).build();
        }
        catch(RuntimeException e)
        { 
           response = Response.status(Response.Status.NOT_FOUND).build();
        }
        catch(Exception e)
        {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
}
