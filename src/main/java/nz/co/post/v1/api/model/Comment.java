package nz.co.post.v1.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Objects;

/**
 * Api model of a comment.
 */
@ApiModel(value = "Comment", description = "The comment model.")
public class Comment
{
  @ApiModelProperty(required = true, value = "The id")
  @NotBlank
  private final String id;

  @ApiModelProperty(required = true, value = "The post id")
  private final String postId;

  @ApiModelProperty(value = "The name")
  private final String name;

  @ApiModelProperty(value = "The email")
  private final String email;

  @ApiModelProperty(value = "The body")
  private final String body;

  @JsonCreator
  public Comment(
      @JsonProperty("id") String id,
      @JsonProperty("postId") String postId,
      @JsonProperty("name") String name,
      @JsonProperty("email") String email,
      @JsonProperty("body") String body)
  {
    this.id = id;
    this.postId = postId;
    this.name = name;
    this.email = email;
    this.body = body;
  }

  /**
   * Builder constructor.
   *
   * @param builder the PostBuilder to build with.
   */
  private Comment(CommentBuilder builder)
  {
    this.id = builder.id;
    this.postId = builder.postId;
    this.name = builder.name;
    this.email = builder.email;
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
   * @return postId
   **/
  public String getPostId()
  {
    return postId;
  }

  /**
   * @return name
   **/
  public String getName()
  {
    return name;
  }

  /**
   * @return email
   **/
  public String getEmail()
  {
    return email;
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
    Comment that = (Comment) o;

    return Objects.equals(id, that.id)
        && Objects.equals(postId, that.postId)
        && Objects.equals(name, that.name)
        && Objects.equals(email, that.email)
        && Objects.equals(body, that.body);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(
        id,
        postId,
        name,
        email,
        body);
  }

  @Override
  public String toString()
  {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("postId", postId)
        .add("name", name)
        .add("email", email)
        .add("body", body)
        .toString();
  }

  /**
   * Builds a comment object.
   *
   */
  public static final class CommentBuilder
  {

    private String id;

    private String postId;

    private String name;

    private String email;

    private String body;

    private CommentBuilder()
    {

    }

    public static CommentBuilder create()
    {
      return new CommentBuilder();
    }

    public CommentBuilder withId(String id)
    {
      this.id = id;
      return this;
    }

    public CommentBuilder withPostId(String postId)
    {
      this.postId = postId;
      return this;
    }

    public CommentBuilder withName(String name)
    {
      this.name = name;
      return this;
    }

    public CommentBuilder withEmail(String email)
    {
      this.email = email;
      return this;
    }

    public CommentBuilder withBody(String body)
    {
      this.body = body;
      return this;
    }

    public Comment build()
    {
      return new Comment(this);
    }

  }
}
