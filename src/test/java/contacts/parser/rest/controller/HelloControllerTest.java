package contacts.parser.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import contacts.parser.rest.model.Contact;
import contacts.parser.rest.repository.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {
    public static final String TESTED_URL = "/hello/contacts";
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void init(@Autowired ContactRepository contactRepository) {
        contactRepository.save(new Contact("Bob"));
        contactRepository.save(new Contact("Alice"));
        contactRepository.save(new Contact("John"));
        contactRepository.save(new Contact("Peter"));
        contactRepository.save(new Contact("Parker"));
    }

    @Test
    public void twoValidRequests_Ok() throws Exception {
        String firstExpectedJson = "[{\"id\":1,\"name\":\"Bob\"},"
                + "{\"id\":3,\"name\":\"John\"}]";
        String firstResult = mockMvc.perform(get(TESTED_URL)
                .param("nameFilter", "^.*[aei].*$")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(firstExpectedJson, firstResult);

        String secondExpectedJson = "[{\"id\":1,\"name\":\"Bob\"},{\"id\":3,\"name\":\"John\"},"
                + "{\"id\":4,\"name\":\"Peter\"},{\"id\":5,\"name\":\"Parker\"}]";
        String secondResult = mockMvc.perform(get(TESTED_URL)
                .param("nameFilter", "^A.*$")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(secondExpectedJson, secondResult);
    }

    @Test
    public void returnAllRequest_Ok() throws Exception {
        String firstExpectedJson = "[{\"id\":1,\"name\":\"Bob\"},{\"id\":2,\"name\":\"Alice\"},"
                + "{\"id\":3,\"name\":\"John\"},{\"id\":4,\"name\":\"Peter\"},"
                + "{\"id\":5,\"name\":\"Parker\"}]";
        String firstResult = mockMvc.perform(get(TESTED_URL)
                .param("nameFilter", "returnAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(firstExpectedJson, firstResult);
    }

    @Test
    public void validParameterNullValue_ThrowsException() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {mockMvc.perform(get(TESTED_URL)
                .param("nameFilter", null));});
    }

    @Test
    public void invalidParameter_NotOk() throws Exception {
        String expected = "Required String parameter 'nameFilter' is not present";
        String result = mockMvc.perform(get(TESTED_URL)
                .param("invalidParameter", "dummyStringValue")
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResolvedException().getMessage();
        Assertions.assertEquals(expected, result);
    }
}
