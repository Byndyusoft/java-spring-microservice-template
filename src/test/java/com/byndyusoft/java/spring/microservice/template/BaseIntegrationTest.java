package com.byndyusoft.java.spring.microservice.template;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.byndyusoft.java.spring.microservice.template.config.I18nConfig;
import com.byndyusoft.java.spring.microservice.template.repository.AddressRepository;
import java.util.Optional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@Import({I18nConfig.class})
public abstract class BaseIntegrationTest {

  @Autowired
  protected MockMvc mockMvc;
  @Autowired(required = false)
  protected AddressRepository addressRepository;

  @BeforeEach
  void setUp() {
    Optional.ofNullable(addressRepository).ifPresent(AddressRepository::deleteAll);
  }

  @SneakyThrows
  protected ResultActions getAddress(final String addressId,
                                     final HttpStatus expectedStatus,
                                     final String expectedResponse) {
    return getCommons(MockMvcRequestBuilders.get("/addresses/{addressId}", addressId),
        expectedStatus, expectedResponse);
  }

  @SneakyThrows
  protected ResultActions createAddress(final String requestBody,
                                        final HttpStatus expectedStatus,
                                        final String expectedResponse) {
    return postCommons(MockMvcRequestBuilders.post("/addresses"),
        requestBody, expectedStatus, expectedResponse);
  }


  private ResultActions getCommons(final MockHttpServletRequestBuilder get,
                                   final HttpStatus expectedStatus,
                                   final String expectedResponse) throws Exception {
    return commonRequest(get, expectedStatus, expectedResponse);
  }

  private ResultActions postCommons(final MockHttpServletRequestBuilder post,
                                    final String requestBody,
                                    final HttpStatus expectedStatus,
                                    final String expectedResponse) throws Exception {
    post.contentType(MediaType.APPLICATION_JSON).content(requestBody);
    return commonRequest(post, expectedStatus, expectedResponse);
  }

  private ResultActions commonRequest(final MockHttpServletRequestBuilder request,
                                      final HttpStatus expectedStatus,
                                      final String expectedResponse) throws Exception {
    //when
    return mockMvc.perform(request)

        //then
        .andDo(
            print()
        ).andExpect(
            status().is(expectedStatus.value())
        ).andExpect(
            content().json(expectedResponse, true)
        );
  }
}
