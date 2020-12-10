package nz.co.post.v1.api.model;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link AggregatedPost.AggregatedPostBuilder}.
 */
public class AggregatedPostBuilderTest
{
  @Test
  public void testBuildPost()
  {
    String postId = "1";
    String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
    String body = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto";
    List comments = Collections.emptyList();

    AggregatedPost aggregatedPost = AggregatedPost.AggregatedPostBuilder.create()
        .withPostId(postId)
        .withTitle(title)
        .withBody(body)
        .withComments(comments)
        .build();

    assertThat(aggregatedPost.getPostId()).isEqualTo(postId);
    assertThat(aggregatedPost.getTitle()).isEqualTo(title);
    assertThat(aggregatedPost.getBody()).isEqualTo(body);
    assertThat(aggregatedPost.getComments()).isEqualTo(comments);
  }
}
