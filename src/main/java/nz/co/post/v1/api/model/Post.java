package nz.co.post.v1.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Objects;

/**
 * Api model of a Post.
 */
@ApiModel(value = "Post", description = "The post model.")
public class Post
{
  @ApiModelProperty(required = true, value = "The id")
  @NotBlank
  private final String id;

  @ApiModelProperty(required = true, value = "The user id")
  private final String userId;

  @ApiModelProperty(value = "The title")
  private final String title;

  @ApiModelProperty(value = "The body")
  private final String body;

  @JsonCreator
  public Post(
      @JsonProperty("id") String id,
      @JsonProperty("userId") String userId,
      @JsonProperty("title") String title,
      @JsonProperty("body") String body)
  {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.body = body;
  }

  /**
   * Builder constructor.
   *
   * @param builder the PostBuilder to build with.
   */
  private Post(PostBuilder builder)
  {
    this.id = builder.id;
    this.userId = builder.userId;
    this.title = builder.title;
    this.body = builder.body;
  }

  /**
   * @return index
   * 
   **/
  public String getId()
  {
    return id;
  }

  /**
   * @return userId
   **/
  public String getUserId()
  {
    return userId;
  }

  /**
   * @return title
   **/
  public String getTitle()
  {
    return title;
  }

  /**
   * @return body
   **/
  public String getBody()
  {
    return body;
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
    Post that = (Post) o;

    return Objects.equals(id, that.id)
        && Objects.equals(userId, that.userId)
        && Objects.equals(title, that.title)
        && Objects.equals(body, that.body);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(
        id,
        userId,
        title,
        body);
  }

  @Override
  public String toString()
  {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("userId", userId)
        .add("title", title)
        .add("body", body)
        .toString();
  }

  /**
   * Builds a post object.
   *
   */
  public static final class PostBuilder
  {

    private String id;

    private String userId;

    private String title;

    private String body;

    private PostBuilder()
    {

    }

    public static PostBuilder create()
    {
      return new PostBuilder();
    }

    public PostBuilder withId(String id)
    {
      this.id = id;
      return this;
    }

    public PostBuilder withUserId(String userId)
    {
      this.userId = userId;
      return this;
    }

    public PostBuilder withTitle(String title)
    {
      this.title = title;
      return this;
    }

    public PostBuilder withBody(String body)
    {
      this.body = body;
      return this;
    }

    public Post build()
    {
      return new Post(this);
    }

  }
}
