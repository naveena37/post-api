package nz.co.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Configuration for the application.
 */
public class ServiceConfig
    extends Configuration
{
  private String serviceTLA = "POS";

  @NotNull
  private String environment;

  @NotNull
  private String instance;

  @NotNull
  private String serviceName;

  private boolean requestResponseLoggingEnabled;

  @Valid
  @NotNull
  private SwaggerBundleConfiguration swaggerBundleConfiguration;

  @NotNull
  private String clientUrl;

  public String getServiceTLA()
  {
    return serviceTLA;
  }

  @JsonProperty("serviceName")
  public String getServiceName()
  {
    return serviceName;
  }

  @JsonProperty("environment")
  public String getEnvironment()
  {
    return environment;
  }

  @JsonProperty("instance")
  public String getInstance()
  {
    return instance;
  }

  @JsonProperty("requestResponseLoggingEnabled")
  public boolean isRequestResponseLoggingEnabled()
  {
    return requestResponseLoggingEnabled;
  }

  @JsonProperty("swagger")
  public SwaggerBundleConfiguration getSwaggerBundleConfiguration()
  {
    return swaggerBundleConfiguration;
  }

  @JsonProperty("clientUrl")
  public String getClientUrl()
  {
    return clientUrl;
  }
}
