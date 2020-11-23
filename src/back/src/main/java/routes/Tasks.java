package routes;

import dao.DODDAO;
import dao.TaskDAO;
import domain.DOD;
import domain.Task;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("projects/{projectId}/tasks/")
public class Tasks {
    @Inject TaskDAO taskDAO;
    @Inject DODDAO dodDAO;

    @GET
    @Produces("application/json")
    public Response getAllForProject(@PathParam("projectId") int projectId) {
        try {
            return Response.status(200).entity(taskDAO.getAllForProject(projectId)).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("us/{usId}")
    @Produces
    public Response getAllForUs(@PathParam("projectId") int projectId, @PathParam("usId") int usId) {
        try {
            return Response.status(200).entity(taskDAO.getAllForUserStory(projectId, usId)).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{taskId}/parent")
    @Produces("application/json")
    public Response getParentTasks(@PathParam("projectId") int projectId, @PathParam("taskId") int taskId) {
        try {
            Task task = taskDAO.getById(projectId, taskId);
            return Response.status(200).entity(taskDAO.getParentTasks(task)).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{taskId}/children")
    @Produces("application/json")
    public Response getChildrenTasks(@PathParam("projectId") int projectId, @PathParam("taskId") int taskId) {
        try {
            Task task = taskDAO.getById(projectId, taskId);
            return Response.status(200).entity(taskDAO.getChildrenTasks(task)).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{taskId}/DOD")
    @Produces("application/json")
    public Response getDOD(@PathParam("projectId") int projectId, @PathParam("taskId") int taskId) {
        try {
            return Response.status(200).entity(dodDAO.getAllForTask(projectId, taskId)).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{taskId}/DOD")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateDOD(@PathParam("projectId") int projectId, @PathParam("taskId") int taskId, DOD dod) {
        dod = new DOD(projectId, taskId, dod.description, dod.state, dod.id);
        try {
            dodDAO.update(dod);
            return Response.status(200).entity(dod).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insert(@PathParam("projectId") int projectId, Task task) {
        task = new Task(projectId, task.usId, task.title, task.duration, task.status);

        try {
            return Response.status(200).entity(taskDAO.insert(task)).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{taskId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("projectId") int projectId, @PathParam("taskId") int taskId, Task task) {
        task = new Task(projectId, task.usId, task.title, task.duration, task.status, taskId);

        try {
            taskDAO.update(task);
            return Response.status(200).entity(task).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{taskId}")
    public Response delete(@PathParam("projectId") int projectId, @PathParam("taskId") int taskId) {
        try {
            taskDAO.delete(taskDAO.getById(projectId, taskId));
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
