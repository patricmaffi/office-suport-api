package org.pmf.services.office.service;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.charset.Charset;

@SpringBootTest
@ActiveProfiles("test")
class GenerateSwaggerTest {

    @Autowired
    WebApplicationContext context;

    @Test
    void generateSwaggerTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
                .andDo((result) -> {
                    FileUtils.writeStringToFile(new File("_swagger/swagger.json"), result.getResponse().getContentAsString(), Charset.defaultCharset());
                });

        Assertions.assertNotNull(mockMvc);
    }
}
