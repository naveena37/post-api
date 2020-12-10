package nz.co.post.v1.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * Api model of a UserResponse.
 */
@ApiModel(value = "UserResponse", description = "The user response.")
public class UserResponse
{
  @ApiModelProperty(required = true, value = "The list of users")
  private final List<User> users;

  @JsonCreator
  public UserResponse(@JsonProperty("users") List<User> users)
  {
    this.users = users;
  }

  private UserResponse(UserResponseBuilder builder)
  {
    this.users = builder.users;
  }

  /**
   * Get all users.
   *
   * @return users
   **/
  public List<User> getUsers()
  {
    return users;
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
    UserResponse that = (UserResponse) o;

    return Objects.equals(users, that.users);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(users);
  }

  @Override
  public String toString()
  {
    return MoreObjects.toStringHelper(this)
        .add("users", users)
        .toString();
  }

  /**
   * Builds a UserResponse object.
   *
   */
  public static final class UserResponseBuilder
  {
    private List<User> users;

    private UserResponseBuilder()
    {

    }

    public static UserResponseBuilder create()
    {
      return new UserResponseBuilder();
    }

    public UserResponseBuilder withUsers(List<User> users)
    {
      this.users = users;
      return this;
    }

    public UserResponse build()
    {
      return new UserResponse(this);
    }
  }
}
