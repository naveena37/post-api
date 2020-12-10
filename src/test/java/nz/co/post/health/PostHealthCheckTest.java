package nz.co.post.health;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link PostHealthCheck}.
 */
public class PostHealthCheckTest
{
  /**
   * Run with Mockito.
   */
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @InjectMocks
  private PostHealthCheck healthCheck;

  @Mock
  private Client client;

  @Mock
  private Response response;

  @Before
  public void setUp() throws Exception {
    WebTarget webTarget = mock(WebTarget.class);
    Invocation.Builder builder = mock(Invocation.Builder.class);
    when(client.target(anyString())).thenReturn(webTarget);
    when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
    when(builder.get()).thenReturn(response);
  }

  @Test
  public void givenJsonPlaceHolderIsAvailableThenResultIsHealthy()
      throws Exception
  {
    when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
    assertThat(healthCheck.check().isHealthy()).isTrue();
  }

  @Test
  public void givenJsonPlaceHolderIsNotAvailableThenResultIsNotHealthy()
      throws Exception
  {
    when(response.getStatus()).thenReturn(Response.Status.BAD_REQUEST.getStatusCode());
    assertThat(healthCheck.check().isHealthy()).isFalse();
  }

}
