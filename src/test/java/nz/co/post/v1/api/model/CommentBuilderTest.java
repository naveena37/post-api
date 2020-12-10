package nz.co.post.v1.api.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link Comment.CommentBuilder}.
 */
public class CommentBuilderTest
{
  @Test
  public void testBuildComment()
  {
    String id = "1";
    String postId = "1";
    String name = "asterix";
    String email = "asterix@gmail.com";
    String body = "vsxcghDFHSdfusJGDJSdjsBDJKSKAhkadgkadghajgfad";

    Comment comment = Comment.CommentBuilder.create()
        .withId(id)
        .withPostId(postId)
        .withName(name)
        .withEmail(email)
        .withBody(body)
        .build();

    assertThat(comment.getId()).isEqualTo(id);
    assertThat(comment.getPostId()).isEqualTo(postId);
    assertThat(comment.getName()).isEqualTo(name);
    assertThat(comment.getEmail()).isEqualTo(email);
    assertThat(comment.getBody()).isEqualTo(body);
  }
}
