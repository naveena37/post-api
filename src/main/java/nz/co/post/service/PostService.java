package nz.co.post.service;

import nz.co.post.v1.api.model.AggregatedPostResponse;
import nz.co.post.v1.api.model.UserResponse;

/**
 * The service to perform post aggregate operations.
 */
public interface PostService
{
  /**
   * Get the users.
   *
   * @return the User response.
   */
  UserResponse getUsers();

  /**
   * Get the posts and comments for a user id.
   *
   * @param userId the userId.
   * @return the Post response.
   */
  AggregatedPostResponse getPostsAndComments(String userId);

}
