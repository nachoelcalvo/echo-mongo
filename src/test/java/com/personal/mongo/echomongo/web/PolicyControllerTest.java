package com.personal.mongo.echomongo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.domain.Review;
import com.personal.mongo.echomongo.domain.vo.Address;
import com.personal.mongo.echomongo.dto.AddressDTO;
import com.personal.mongo.echomongo.dto.PolicyRequest;
import com.personal.mongo.echomongo.service.impl.PolicyService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PolicyController.class)
public class PolicyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PolicyService policyServiceMock;

    @Test
    @DisplayName("Should get all policies")
    public void shouldGetAllPolicies() throws Exception {

        given(policyServiceMock.getAllPolicies()).willReturn(obtainListTestPolicies());

        mockMvc.perform(get("/policies/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        then(policyServiceMock).should().getAllPolicies();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should get a policy by id")
    public void shouldGetPolicyById() {

        given(policyServiceMock.getById(BDDMockito.any())).willReturn(Optional.of(obtainTestPolicy()));

        mockMvc.perform(get("/policies/ae255a"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is("test_type")))
                .andExpect(jsonPath("$.price", is(100)));

        then(policyServiceMock).should().getById("ae255a");
    }

    @Test
    @DisplayName("Should get policy by city")
    public void shouldGetPolicyByCity() throws Exception {

        given(policyServiceMock.getByCity(BDDMockito.any())).willReturn(obtainListTestPolicies());

        mockMvc.perform(get("/policies/addresses/madrid"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("test_type")))
                .andExpect(jsonPath("$[0].address.city", is("madrid")))
                .andExpect(jsonPath("$[0].price", is(100)));

        then(policyServiceMock).should().getByCity("madrid");
    }

    @Test
    @DisplayName("Should create a policy")
    public void shouldCreatePolicy() throws Exception {

        given(policyServiceMock.createPolicy(BDDMockito.any())).willReturn(obtainTestPolicy());

        mockMvc.perform(post("/policies")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(obtainTestPolicyRequest())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is("test_type")));
    }

    private List<Policy> obtainListTestPolicies() {
        return Arrays.asList(obtainTestPolicy());
    }

    private Policy obtainTestPolicy() {
        return new Policy("test_type", 100, new Address("madrid", "Spain"),
                Arrays.asList(
                        new Review("user", 8, false)
                ));
    }

    private PolicyRequest obtainTestPolicyRequest(){
        return new PolicyRequest("test_type", new AddressDTO("madrid", "spain"), 100);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}