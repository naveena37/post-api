package nz.co.post.v1.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

/**
 * Api model of a Post.
 */
@ApiModel(value = "Post", description = "The post model.")
public class AggregatedPost
{
  @ApiModelProperty(required = true, value = "The id")
  @NotBlank
  private final String postId;

  @ApiModelProperty(value = "The title")
  private final String title;

  @ApiModelProperty(value = "The body")
  private final String body;

  @ApiModelProperty(value = "The list of comments associated with the postId")
  private final List<Comment> comments;

  @JsonCreator
  public AggregatedPost(
      @JsonProperty("postId") String postId,
      @JsonProperty("title") String title,
      @JsonProperty("body") String body,
      @JsonProperty("comments") List<Comment> comments)
  {
    this.postId = postId;
    this.title = title;
    this.body = body;
    this.comments = comments;
  }

  /**
   * Builder constructor.
   *
   * @param builder the PostBuilder to build with.
   */
  private AggregatedPost(AggregatedPostBuilder builder)
  {
    this.postId = builder.postId;
    this.title = builder.title;
    this.body = builder.body;
    this.comments = builder.comments;
  }

  /**
   * @return postId
   * 
   **/
  public String getPostId()
  {
    return postId;
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

  /**
   * Get all comments.
   *
   * @return comments
   **/
  public List<Comment> getComments()
  {
    return comments;
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
    AggregatedPost that = (AggregatedPost) o;

    return Objects.equals(postId, that.postId)
        && Objects.equals(title, that.title)
        && Objects.equals(body, that.body)
        && Objects.equals(comments, that.comments);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(
        postId,
        title,
        body,
        comments);
  }

  @Override
  public String toString()
  {
    return MoreObjects.toStringHelper(this)
        .add("postId", postId)
        .add("title", title)
        .add("body", body)
        .add("comments", comments)
        .toString();
  }

  /**
   * Builds a post object.
   *
   */
  public static final class AggregatedPostBuilder
  {

    private String postId;

    private String title;

    private String body;

    private List<Comment> comments;

    private AggregatedPostBuilder()
    {

    }

    public static AggregatedPostBuilder create()
    {
      return new AggregatedPostBuilder();
    }

    public AggregatedPostBuilder withPostId(String postId)
    {
      this.postId = postId;
      return this;
    }

    public AggregatedPostBuilder withTitle(String title)
    {
      this.title = title;
      return this;
    }

    public AggregatedPostBuilder withBody(String body)
    {
      this.body = body;
      return this;
    }

    public AggregatedPostBuilder withComments(List<Comment> comments)
    {
      this.comments = comments;
      return this;
    }

    public AggregatedPost build()
    {
      return new AggregatedPost(this);
    }

  }
}
