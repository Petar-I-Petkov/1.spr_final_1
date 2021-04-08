package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.service.TestService;
import com.petkov.spr_final_1.utils.ValidationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestRestController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class TestAddRestControllerTest {

//    @TestConfiguration
//    static class TestAddRestControllerTestContextConfiguration {
//        @Bean
//        public TestService testService() {
//            return new TestService() {
//                @Override
//                public ActiveTestViewModel getActiveTestById(String id) {
//                    return null;
//                }
//
//                @Override
//                public List<TestThumbnailViewModel> getAllUpcomingTestsView() {
//                    return null;
//                }
//            };
//        }
//    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ValidationUtil validationUtil;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void test() throws Exception {


        mockMvc
                .perform(post("/tests/api")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "1")
                        .param("dueDate", "1")
                        .param("questionIds","1"))
                .andExpect(status().isUnprocessableEntity());
    }
}
