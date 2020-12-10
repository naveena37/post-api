package nz.co.post.v1.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.glassfish.jersey.server.JSONP;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

/**
 * Api model of a PostResponse.
 */
@ApiModel(value = "PostResponse", description = "The post response.")
public class AggregatedPostResponse
{
  @ApiModelProperty(required = true, value = "The userId")
  @NotBlank
  private final String userId;

  @ApiModelProperty(required = true, value = "The list of posts associated with the userId")
  private final List<AggregatedPost> aggregatedPosts;

  @JsonCreator
  public AggregatedPostResponse(
      @JsonProperty("userId") String userId,
      @JsonProperty("posts") List<AggregatedPost> aggregatedPosts)
  {
    this.userId = userId;
    this.aggregatedPosts = aggregatedPosts;
  }

  private AggregatedPostResponse(PostResponseBuilder builder)
  {
    this.userId = builder.userId;
    this.aggregatedPosts = builder.aggregatedPosts;
  }

  /**
   * @return userId
   **/
  public String getUserId()
  {
    return userId;
  }

  /**
   * Get all posts.
   *
   * @return posts
   **/
  public List<AggregatedPost> getPosts()
  {
    return aggregatedPosts;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    AggregatedPostResponse that = (AggregatedPostResponse) o;

    return Objects.equals(userId, that.userId)
        && Objects.equals(aggregatedPosts, that.aggregatedPosts);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(userId, aggregatedPosts);
  }

  @Override
  public String toString()
  {
    return MoreObjects.toStringHelper(this)
        .add("userId", userId)
        .add("posts", aggregatedPosts)
        .toString();
  }

  /**
   * Builds a PostResponse object.
   *
   */
  public static final class PostResponseBuilder
  {
    private String userId;

    private List<AggregatedPost> aggregatedPosts;

    private PostResponseBuilder()
    {

    }

    public static PostResponseBuilder create()
    {
      return new PostResponseBuilder();
    }

    public PostResponseBuilder withUserId(String userId)
    {
      this.userId = userId;
      return this;
    }

    public PostResponseBuilder withPosts(List<AggregatedPost> aggregatedPosts)
    {
      this.aggregatedPosts = aggregatedPosts;
      return this;
    }

    public AggregatedPostResponse build()
    {
      return new AggregatedPostResponse(this);
    }
  }
}
