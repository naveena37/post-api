package nz.co.post.v1.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Objects;

/**
 * Api model of a User.
 */
@ApiModel(value = "User", description = "The user model.")
public class User
{
  @ApiModelProperty(required = true, value = "The id")
  @NotBlank
  private final String id;

  @ApiModelProperty(value = "The name")
  private final String name;

  @ApiModelProperty(value = "The email")
  private final String email;

  @JsonCreator
  public User(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("email") String email)
  {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  /**
   * Builder constructor.
   *
   * @param builder the UserBuilder to build with.
   */
  private User(UserBuilder builder)
  {
    this.id = builder.id;
    this.name = builder.name;
    this.email = builder.email;
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
    User that = (User) o;

    return Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(
        id,
        name,
        email);
  }

  @Override
  public String toString()
  {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("email", email)
        .toString();
  }

  /**
   * Builds a User object.
   *
   */
  public static final class UserBuilder
  {

    private String id;

    private String name;

    private String email;

    private UserBuilder()
    {

    }

    public static UserBuilder create()
    {
      return new UserBuilder();
    }

    public UserBuilder withId(String id)
    {
      this.id = id;
      return this;
    }

    public UserBuilder withName(String name)
    {
      this.name = name;
      return this;
    }

    public UserBuilder withEmail(String email)
    {
      this.email = email;
      return this;
    }

    public User build()
    {
      return new User(this);
    }

  }
}
