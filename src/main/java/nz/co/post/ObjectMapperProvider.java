package nz.co.post;

import org.glassfish.hk2.api.Factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.dropwizard.jackson.Jackson;

/**
 * Provide the default {@link ObjectMapper} for this API.
 */
public class ObjectMapperProvider
    implements
    Factory<ObjectMapper>
{
  private static final ObjectMapper OBJECT_MAPPER = Jackson.newObjectMapper()
      .setSerializationInclusion(JsonInclude.Include.NON_NULL)
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

  @Override
  public ObjectMapper provide()
  {
    return OBJECT_MAPPER;
  }

  @Override
  public void dispose(ObjectMapper objectMapper)
  {
    // Nothing to see here.
  }
}
