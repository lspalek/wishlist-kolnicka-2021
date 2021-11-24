package dk.cngroup.kolnicka;

import static org.assertj.core.api.BDDAssertions.then;
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

import dk.cngroup.kolnicka.repository.WishRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class WishListIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Wish wish;

    @Autowired
    private WishRepository repository;

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

        whenWishCreated();

        thenCheckLastCreatedWish();
    }

    private void givenWish() {
        wish = Wish.builder()
                .priority(1)
                .description("New test for creating wish")
                .link("http://www.spring.io")
                .build();
    }

    private void whenWishCreated() throws Exception {
        String givenWishPayload = objectMapper.writeValueAsString(wish);

        mockMvc.perform(post("/wishes").content(givenWishPayload))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private void thenCheckLastCreatedWish() {
        Wish newWish = repository.findTopByOrderByIdDesc();
        then(newWish.getDescription()).isEqualTo("New test for creating wish");
        then(newWish.getLink()).isEqualTo("http://www.spring.io");
        then(newWish.getPriority()).isEqualTo(1);
        then(newWish.getId()).isNotNull();
    }

}