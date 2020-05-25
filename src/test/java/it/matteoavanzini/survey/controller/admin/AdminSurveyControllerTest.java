package it.matteoavanzini.survey.controller.admin;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
public class AdminSurveyControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Test
    public void testSaveSurvey() throws Exception {
        RequestBuilder req = post("/admin/survey/save")
                             .param("title", "Test")
                             .param("id", "1")
                             .param("description", "Lore Ipsum ...")
                             .param("question[]", "1L")
                             .param("question[]", "2L")
                            // .with(user("admin")
                            //         .password("admin")
                            //         .roles("ADMIN"))
                            .with(csrf());
        mvc.perform(req)
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/admin/survey/list"));
    }

    @Test
    public void testListSurvey() throws Exception {
        RequestBuilder req = get("/admin/survey/list");
        mvc.perform(req)
            .andExpect(status().isOk())
            .andExpect(view().name("survey/list"));
    }

    @Test
    @WithUserDetails("user")
    public void testListSurveyUnauthorized() throws Exception {
        RequestBuilder req = get("/admin/survey/list");
        mvc.perform(req)
            .andExpect(status().is4xxClientError());
    }
}