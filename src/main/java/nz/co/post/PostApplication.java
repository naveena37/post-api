package nz.co.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.util.Duration;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import nz.co.post.health.PostHealthCheck;
import nz.co.post.service.impl.PostServiceImpl;
import nz.co.post.v1.api.resource.PostResource;
import org.glassfish.jersey.logging.LoggingFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import java.util.logging.Level;

/**
 * The main entry point into this Dropwizard application.
 */
public class PostApplication
    extends Application<ServiceConfig>
{
  private static final Logger LOG = LoggerFactory.getLogger(PostApplication.class);

  public static void main(String[] args)
      throws Exception
  {
    new PostApplication().run(args);
  }

  @Override
  public String getName()
  {
    return "PostApplication";
  }

  @Override
  public void initialize(Bootstrap<ServiceConfig> bootstrap)
  {
    bootstrap.setObjectMapper(new ObjectMapperProvider().provide());
    // Configuration bundles
    bootstrap.setConfigurationSourceProvider(
        new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
    // Swagger web bundle.
    bootstrap.addBundle(
            new SwaggerBundle<ServiceConfig>() {
              @Override
              protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                      ServiceConfig configuration) {
                return configuration.getSwaggerBundleConfiguration();
              }
            });
  }

  @Override
  public void run(ServiceConfig configuration, Environment environment)
      throws Exception
  {
    objectMapper(environment);

    JerseyClientConfiguration clientConfiguration = new JerseyClientConfiguration();
    clientConfiguration.setTimeout(Duration.seconds(5));
    clientConfiguration.setConnectionTimeout(Duration.seconds(5));

    final Client client = new JerseyClientBuilder(environment)
            .using(clientConfiguration)
            .build("JerseyClient");

    environment.jersey().register(client);
    environment.jersey().register(new PostResource(new PostServiceImpl(client, configuration)));
    environment.jersey().register(new ServiceBinder(configuration, environment));

    environment.healthChecks().register("PostHealth", new PostHealthCheck(client));

    applicationStartupMessage(environment);

    if (configuration.isRequestResponseLoggingEnabled())
    {
      logging(environment);
    }
  }

  private void objectMapper(Environment environment)
  {
    ObjectMapper objectMapper = environment.getObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
  }

  /**
   * @param environment -
   */
  private void applicationStartupMessage(Environment environment)
  {
    environment.lifecycle().addServerLifecycleListener(server -> LOG.info("Application is up and running."));
  }

  /*
   * Logs every request and response in the application log, including headers and status codes.
   */
  private void logging(Environment environment)
  {
    // Log the http request and response
    LoggingFeature loggingFeature = new LoggingFeature(
        java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
        // LoggingFeature uses JUL. This is ok, Dropwizard includes jul-to-slf4j bridge.
        Level.INFO,
        LoggingFeature.Verbosity.PAYLOAD_TEXT,
        // Character limit for response body output, after which "..more..." is printed.
        // Configured to be large enough to output entire response.
        15000);

    environment.jersey().register(loggingFeature);
  }
}
