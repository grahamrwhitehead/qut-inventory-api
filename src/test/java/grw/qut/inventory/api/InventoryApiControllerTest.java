package grw.qut.inventory.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import grw.qut.inventory.model.InventoryItem;
import grw.qut.inventory.model.Manufacturer;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.time.LocalDate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class InventoryApiControllerTest {

    private static final String BASIC = "Basic ";

    private static final String INVENTORY_GET_URL = "/inventory";
    private static final String INVENTORY_ID_GET_URL = "/inventory/{id}";
    private static final String INVENTORY_POST_URL = "/inventory";

    private static final String STAR_DESTROYER_INV_ITEM_NO = "75252";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void inventoryGetNoAuthorizationHeader() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryGetInvalidUser() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user4:password").getBytes()))
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryGetForbiddenUser() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("forbiddenUser1:password").getBytes()))
            )
            .andExpect(status().isForbidden());
    }

    @Test
    void inventoryGetValidUserInvalidPassword() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:invalidPassword").getBytes()))
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryGetValidUserValidPasswordDefaultSkipAndLimit() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
            )
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":\"75192\",\"name\":\"Millennium Falcon\",\"releaseDate\":\"2017-10-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75252\",\"name\":\"Star Destroyer\",\"releaseDate\":\"2019-09-18\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75159\",\"name\":\"Death Star\",\"releaseDate\":\"2016-09-15\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}}]"));
    }

    @Test
    void inventoryGetValidUserValidPasswordInvalidSkipAndLimit() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
                    .param("skip", "ABC")
                    .param("limit", "XYZ")
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void inventoryGetValidUserValidPasswordWithZeroSkipAndTenLimit() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
                    .param("skip", "0")
                    .param("limit", "10")
            )
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":\"75192\",\"name\":\"Millennium Falcon\",\"releaseDate\":\"2017-10-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75252\",\"name\":\"Star Destroyer\",\"releaseDate\":\"2019-09-18\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75159\",\"name\":\"Death Star\",\"releaseDate\":\"2016-09-15\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75244\",\"name\":\"Tantive IV\",\"releaseDate\":\"2019-05-03\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75181\",\"name\":\"Y-Wing Starfighter\",\"releaseDate\":\"2018-05-04\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75292\",\"name\":\"The Razor Crest\",\"releaseDate\":\"2020-09-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75256\",\"name\":\"Kylo Ren's Shuttle\",\"releaseDate\":\"2019-10-04\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75291\",\"name\":\"Death Star Final Duel\",\"releaseDate\":\"2020-08-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75272\",\"name\":\"Sith TIE Fighter\",\"releaseDate\":\"2020-01-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"SCPH-1000\",\"name\":\"Playstation\",\"releaseDate\":\"1994-12-03\",\"manufacturer\":{\"name\":\"Sony\",\"homePage\":\"https://www.sony.com.au/\",\"phone\":\"07 5453 3645\"}}]"));
    }

    @Test
    void inventoryGetValidUserValidPasswordWithZeroSkipAndThreeLimit() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
                    .param("skip", "0")
                    .param("limit", "3")
            )
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":\"75192\",\"name\":\"Millennium Falcon\",\"releaseDate\":\"2017-10-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75252\",\"name\":\"Star Destroyer\",\"releaseDate\":\"2019-09-18\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75159\",\"name\":\"Death Star\",\"releaseDate\":\"2016-09-15\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}}]"));
    }

    @Test
    void inventoryGetValidUserValidPasswordWithThreeSkipAndThreeLimit() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
                    .param("skip", "3")
                    .param("limit", "3")
            )
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":\"75244\",\"name\":\"Tantive IV\",\"releaseDate\":\"2019-05-03\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75181\",\"name\":\"Y-Wing Starfighter\",\"releaseDate\":\"2018-05-04\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75292\",\"name\":\"The Razor Crest\",\"releaseDate\":\"2020-09-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}}]"));
    }

    @Test
    void inventoryGetValidUserValidPasswordWithSixSkipAndThreeLimit() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_GET_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
                    .param("skip", "6")
                    .param("limit", "3")
            )
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":\"75256\",\"name\":\"Kylo Ren's Shuttle\",\"releaseDate\":\"2019-10-04\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75291\",\"name\":\"Death Star Final Duel\",\"releaseDate\":\"2020-08-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}},{\"id\":\"75272\",\"name\":\"Sith TIE Fighter\",\"releaseDate\":\"2020-01-01\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}}]"));
    }

    @Test
    void inventoryIdGetNoAuthorizationHeader() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_ID_GET_URL, STAR_DESTROYER_INV_ITEM_NO)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryIdGetInvalidUser() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_ID_GET_URL, STAR_DESTROYER_INV_ITEM_NO)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user4:password").getBytes()))
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryIdGetForbiddenUser() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_ID_GET_URL, STAR_DESTROYER_INV_ITEM_NO)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("forbiddenUser1:password").getBytes()))
            )
            .andExpect(status().isForbidden());
    }

    @Test
    void inventoryIdGetValidUserInvalidPassword() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_ID_GET_URL, STAR_DESTROYER_INV_ITEM_NO)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:invalidPassword").getBytes()))
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryIdGetValidUserValidPasswordValidId() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_ID_GET_URL, STAR_DESTROYER_INV_ITEM_NO)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
            )
            .andExpect(status().isOk())
            .andExpect(content().json("{\"id\":\"75252\",\"name\":\"Star Destroyer\",\"releaseDate\":\"2019-09-18\",\"manufacturer\":{\"name\":\"Lego\",\"homePage\":\"https://www.lego.com/en-au\",\"phone\":\"02 1342 4343\"}}"));
    }

    @Test
    void inventoryIdGetValidUserValidPasswordInvalidId() throws Exception {
        mockMvc
            .perform(
                get(INVENTORY_ID_GET_URL, "123XYZ")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
            )
            .andExpect(status().isNotFound())
            .andExpect(content().string(""));
    }

    @Test
    void inventoryPostNoAuthorizationHeader() throws Exception {
        val inventoryItem = new InventoryItem();

        mockMvc
            .perform(
                post(INVENTORY_POST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(inventoryItem))
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryPostInvalidUser() throws Exception {
        mockMvc
            .perform(
                post(INVENTORY_POST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user4:password").getBytes()))
                    .content(objectMapper.writeValueAsString(new InventoryItem()))
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryPostForbiddenUser() throws Exception {
        mockMvc
            .perform(
                post(INVENTORY_POST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("forbiddenUser1:password").getBytes()))
                    .content(objectMapper.writeValueAsString(new InventoryItem()))
            )
            .andExpect(status().isForbidden());
    }

    @Test
    void inventoryPostValidUserInvalidPassword() throws Exception {
        val inventoryItem = new InventoryItem();

        mockMvc
            .perform(
                post(INVENTORY_POST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:invalidPassword").getBytes()))
                    .content(objectMapper.writeValueAsString(inventoryItem))
            )
            .andExpect(status().isUnauthorized());
    }

    @Test
    void inventoryPostValidUserValidInvalidInventoryItem() throws Exception {
        val inventoryItem = new InventoryItem();

        mockMvc
            .perform(
                post(INVENTORY_POST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
                    .content(objectMapper.writeValueAsString(inventoryItem))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void inventoryPostValidUserValidPasswordMatchesExistingRecord() throws Exception {
        val inventoryItem = new InventoryItem()
            .id(STAR_DESTROYER_INV_ITEM_NO)
            .name("Star Destroyer")
            .releaseDate(LocalDate.parse("2019-09-18"))
            .manufacturer(new Manufacturer()
                .name("Lego")
                .homePage("https://www.lego.com/en-au")
                .phone("02 1342 4343"));

        mockMvc
            .perform(
                post(INVENTORY_POST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
                    .content(objectMapper.writeValueAsString(inventoryItem))
            )
            .andExpect(status().isConflict());
    }

    @Test
    void inventoryPostValidUserValidPasswordValidPayload() throws Exception {
        val inventoryItem = new InventoryItem()
            .id("75288")
            .name("AT-AT")
            .releaseDate(LocalDate.parse("2020-09-01"))
            .manufacturer(new Manufacturer()
                                .name("Lego")
                                .homePage("https://www.lego.com/en-au")
                                .phone("02 1342 4343"));

        mockMvc
            .perform(
                post(INVENTORY_POST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, BASIC + Base64Utils.encodeToString(("user1:password").getBytes()))
                    .content(objectMapper.writeValueAsString(inventoryItem))
            )
            .andExpect(status().isCreated());
    }
}
