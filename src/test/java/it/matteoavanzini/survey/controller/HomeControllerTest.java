package it.matteoavanzini.survey.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.matteoavanzini.survey.SurveyApplication;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SurveyApplication.class)
public class HomeControllerTest {

    @Autowired 
    private WebApplicationContext ctx;

    private MockMvc mvc;

    @Before  
    public void setUp() {  
        mvc = MockMvcBuilders
                .webAppContextSetup(this.ctx)
                .build();
    }

    @Test
    public void homePageShouldSayHello() throws Exception {
        mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("home"));
    }


    @Test
    public void testRouteHome() throws Exception {
       mvc.perform(get("/home"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Benvenuto")));
    }

}