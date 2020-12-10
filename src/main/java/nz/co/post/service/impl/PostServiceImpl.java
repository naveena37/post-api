package nz.co.post.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.co.post.ObjectMapperProvider;
import nz.co.post.ServiceConfig;
import nz.co.post.service.PostService;
import nz.co.post.v1.api.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * An implementation of the {@link PostService}.
 */
public class PostServiceImpl
    implements
        PostService
{
  private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

  private final Client client;

  private final ServiceConfig config;

  private final ObjectMapper mapper = new ObjectMapperProvider().provide();

  /**
   * Instantiate a the service with the supplied client.
   * 
   * @param client the {@link Client} client.
   */
  @Inject
  public PostServiceImpl(final Client client, final ServiceConfig config)
  {
    this.client = Objects.requireNonNull(client, "A valid Client is required");
    this.config = config;
  }

  @Override
  public UserResponse getUsers() {
    Response response = client
            .target(config.getClientUrl())
            .path("users")
            .request(MediaType.APPLICATION_JSON)
            .get();

    List<User> users = null;
    try {
      users = Arrays.asList(mapper.readValue(response.readEntity(String.class), User[].class));
    } catch (JsonProcessingException e) {
      LOGGER.error("Could not process /users json response...");
    }

    return UserResponse.UserResponseBuilder.create().withUsers(users).build();
  }

  @Override
  public AggregatedPostResponse getPostsAndComments(final String userId)
  {
    List<Post> posts = getPostsByUserId(userId);

    List<AggregatedPost> aggregatedPosts = new ArrayList<AggregatedPost>();
    for (Post post : posts) {
      AggregatedPost aggregatedPost = AggregatedPost.AggregatedPostBuilder
              .create()
              .withPostId(post.getId())
              .withTitle(post.getTitle())
              .withBody(post.getBody())
              .withComments(getCommentsByPostId(post.getId()))
              .build();
      aggregatedPosts.add(aggregatedPost);
    }

    return AggregatedPostResponse.PostResponseBuilder
            .create()
            .withUserId(userId)
            .withPosts(aggregatedPosts)
            .build();
  }

  private List<Post> getPostsByUserId(String userId)
  {
    Response response = client
            .target(config.getClientUrl())
            .path("posts")
            .queryParam("userId", userId)
            .request(MediaType.APPLICATION_JSON)
            .get();

    List<Post> posts = null;
    try {
      posts = Arrays.asList(mapper.readValue(response.readEntity(String.class), Post[].class));
    } catch (JsonProcessingException e) {
      LOGGER.error("Could not process /posts json response...");
    }
    return posts;
  }

  private List<Comment> getCommentsByPostId(String postId)
  {
    Response response = client.target(config.getClientUrl())
            .path("comments")
            .queryParam("postId", postId)
            .request(MediaType.APPLICATION_JSON)
            .get();

    List<Comment> comments = null;
    try {
      comments = Arrays.asList(mapper.readValue(response.readEntity(String.class), Comment[].class));
    } catch (JsonProcessingException e) {
      LOGGER.error("Could not process /comments json response...");
    }

    return comments;
  }

}
