package nz.co.post.v1.api.resource;

import nz.co.post.service.PostService;
import nz.co.post.v1.api.model.AggregatedPostResponse;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Test for {@link PostResource}.
 */
public class PostResourceTest
{
  /**
   * Run with Mockito.
   */
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @InjectMocks
  private PostResource postResource;

  @Mock
  private PostService postService;

  @Test
  public void testGetPostsAndComments()
  {
    String userId = "1";
    AggregatedPostResponse aggregatedPostResponse = mock(AggregatedPostResponse.class);
    when(postService.getPostsAndComments(anyString())).thenReturn(aggregatedPostResponse);

    Response response = postResource.getPostsAndComments(userId);

    assertThat(response.getStatus()).isEqualTo(200);
    assertThat(response.getEntity()).isEqualTo(aggregatedPostResponse);
    verify(postService).getPostsAndComments(userId);
  }

}
