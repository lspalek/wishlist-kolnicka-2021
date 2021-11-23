package dk.cngroup.kolnicka;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class WishListIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Wish wish;

    private ResultActions resultActions;

    @Test
    public void shouldCreateNewWish() throws Exception {

        mockMvc.perform(get("/new-wish"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", notNullValue()))
                .andExpect(jsonPath("description", equalTo("White t-shirt")))
                .andExpect(jsonPath("priority", equalTo(3)));

    }

    @Test
    public void shouldCreateWish() throws Exception {
        givenWish();
        String givenWishPayload = objectMapper.writeValueAsString(wish);

        mockMvc.perform(post("/wishes").content(givenWishPayload))
                .andDo(print())
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("id", notNullValue()))
                .andExpect(jsonPath("description", equalTo("New test for creating wish")))
                .andExpect(jsonPath("link", equalTo("http://www.spring.io")))
                .andExpect(jsonPath("priority", equalTo(3)))
        ;

    }

    private void givenWish() {
        wish = Wish.builder()
                .priority(1)
                .description("New test for creating wish")
                .link("http://www.spring.io")
                .build();
    }

}