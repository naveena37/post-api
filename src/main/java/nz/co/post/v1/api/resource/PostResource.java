package nz.co.post.v1.api.resource;

import io.swagger.annotations.*;
import nz.co.post.service.PostService;
import nz.co.post.v1.api.model.AggregatedPostResponse;
import nz.co.post.v1.api.model.UserResponse;
import org.glassfish.jersey.internal.Errors;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Dropwizard resource class for PostApplication.
 * <p>
 * See http://www.dropwizard.io/1.0.2/docs/manual/core.html#man-core-resources
 * <p>
 * Exposes the message's restful api.
 */
@SwaggerDefinition(info = @Info(title = "Post API.", version = "1"))
@Api
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1")
public class PostResource
{
  private final PostService postService;

  @Inject
  public PostResource(PostService postService) {
    this.postService = postService;
  }

  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Get all users", notes = "Returns all users.", response = UserResponse.class, tags = { "Users" })
  @ApiResponses({
          @ApiResponse(code = 200, message = "The users associated.", response = UserResponse.class),
          @ApiResponse(code = 422, message = "Unprocessable entity.", response = Errors.class) })
  @GET
  @Path("/users")
  public Response getUsers()
  {
    UserResponse userResponse = postService.getUsers();

    return Response.ok(userResponse).build();
  }

  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Get Posts and Comments", notes = "Returns the posts and comments for a user.", response = AggregatedPostResponse.class, tags = { "Posts" })
  @ApiResponses({
      @ApiResponse(code = 200, message = "The posts and comments associated with the userId.", response = AggregatedPostResponse.class),
      @ApiResponse(code = 404, message = "The specified userId could not be found.", response = Errors.class),
      @ApiResponse(code = 422, message = "Unprocessable entity.", response = Errors.class) })
  @GET
  @Path("/posts")
  public Response getPostsAndComments(
      @ApiParam(name = "userId", value = "User Id to retrieve the posts and comments.", required = true) @QueryParam("userId") @NotNull @Valid String userId)
  {
    AggregatedPostResponse aggregatedPostResponse = postService.getPostsAndComments(userId);

    return Response.ok(aggregatedPostResponse).build();
  }

}
