package nz.co.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import io.dropwizard.jackson.AnnotationSensitivePropertyNamingStrategy;
import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import io.dropwizard.jackson.FuzzyEnumModule;

/**
 * Test for {@link ObjectMapperProvider}.
 */
public class ObjectMapperProviderTest
{
  private ObjectMapperProvider toTest = new ObjectMapperProvider();

  @Test
  public void givenObjectMapperProviderWhenProvidedThenObjectMapperIsReturned()
  {
    ObjectMapper actual = toTest.provide();
    // Config set by dropwizard
    assertThat(actual.getRegisteredModuleIds()).contains(GuavaModule.class.getCanonicalName());
    assertThat(actual.getRegisteredModuleIds()).contains(JodaModule.class.getCanonicalName());
    assertThat(actual.getRegisteredModuleIds()).contains(AfterburnerModule.class.getCanonicalName());
    assertThat(actual.getRegisteredModuleIds()).contains(FuzzyEnumModule.class.getCanonicalName());
    assertThat(actual.getRegisteredModuleIds()).contains(ParameterNamesModule.class.getCanonicalName());
    assertThat(actual.getRegisteredModuleIds()).contains(Jdk8Module.class.getCanonicalName());
    assertThat(actual.getRegisteredModuleIds()).contains(JavaTimeModule.class.getCanonicalName());
    assertThat(actual.getPropertyNamingStrategy()).isExactlyInstanceOf(AnnotationSensitivePropertyNamingStrategy.class);
    assertThat(actual.getSubtypeResolver()).isExactlyInstanceOf(DiscoverableSubtypeResolver.class);
    // Config set by the API
    assertThat(actual.getSerializationConfig().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)).isFalse();
    assertThat(actual.getDeserializationConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)).isTrue();
    assertThat(actual.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)).isFalse();
    // Can't check for ObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
  }

  @Test
  public void givenObjectMapperProviderWhenDisposedThenNothingIsDone()
  {
    ObjectMapper objectMapper = mock(ObjectMapper.class);

    toTest.dispose(objectMapper);

    verifyZeroInteractions(objectMapper);
  }
}
