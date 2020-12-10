package nz.co.post;

import io.dropwizard.setup.Environment;
import nz.co.post.service.PostService;
import nz.co.post.service.impl.PostServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Class configures hk2 bindings for the client service layer.
 */
public class ServiceBinder
    extends AbstractBinder
{
  private final ServiceConfig configuration;

  private final Environment environment;

  ServiceBinder(ServiceConfig configuration, Environment environment)
  {
    this.configuration = configuration;
    this.environment = environment;
  }

  @Override
  protected void configure()
  {
    bind(configuration).to(ServiceConfig.class);
    bind(environment).to(Environment.class);

    bind(PostServiceImpl.class).to(PostService.class);
  }
}
