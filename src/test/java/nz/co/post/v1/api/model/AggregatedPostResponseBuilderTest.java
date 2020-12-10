package nz.co.post.v1.api.model;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link AggregatedPostResponse.PostResponseBuilder}.
 */
public class AggregatedPostResponseBuilderTest
{
  @Test
  public void testBuildAggregatedPostResponse()
  {
    String userId = "1";
    List posts = Collections.EMPTY_LIST;
    AggregatedPostResponse response = AggregatedPostResponse.PostResponseBuilder
        .create()
        .withUserId("1")
        .withPosts(posts)
        .build();

    assertThat(response.getUserId()).isEqualTo(userId);
    assertThat(response.getPosts()).isEqualTo(posts);
  }

}
