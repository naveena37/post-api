package nz.co.post.health;

import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;


/**
 * This health check is registered in the dropwizard framework.<br>
 * If the application is running it will return healthy.
 */
public class PostHealthCheck
    extends HealthCheck
{
  private static final Logger LOGGER = LoggerFactory.getLogger(PostHealthCheck.class);
  private final Client client;

  /**
   * Instantiate with the supplied client.
   *
   */
  @Inject
  public PostHealthCheck(final Client client)
  {
    this.client = client;
  }

  @Override
  protected Result check()
      throws Exception
  {
    LOGGER.debug("HealthCheck called..");

    Response response = client.target("https://jsonplaceholder.typicode.com/users")
            .request(MediaType.APPLICATION_JSON)
            .get();

    // Do a smoke run of jsonplaceholder client - if not 200 return unhealthy
    if (response.getStatus() != Response.Status.OK.getStatusCode())
    {
      LOGGER.error("HealthCheck failed - jsonplaceholder downstream service is not available");
      return Result.unhealthy("JsonPlaceHolder service is not available yet");
    }

    LOGGER.debug("HealthCheck - jsonplaceholder downstream service is available...");
    return Result.healthy("Post service initialised");
  }
}
