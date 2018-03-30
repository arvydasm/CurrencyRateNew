package lt.seb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    private static Logger logger = LoggerFactory.getLogger(ControllerTest.class);

    @Autowired
    private MockMvc mvc;


    @Test
    public void test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/getRatesByDate")
                .contentType(MediaType.APPLICATION_JSON)
                .param("date", "2014-12-11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test(expected = Exception.class)
    public void test2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/getRatesByDate")
                .contentType(MediaType.APPLICATION_JSON)
                .param("date", "2015-01-01")
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void test3() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/getRatesByDate")
                .contentType(MediaType.APPLICATION_JSON)
                .param("date", "2014-11-11")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String string = result.getResponse().getContentAsString();

        logger.info(string);

        Assert.assertNotNull(string);
    }

}
