package com.java.example.transfers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TransfersApplication.class)
@AutoConfigureMockMvc
class TransfersApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenTransferRequest_whenTransferIsRequested_thenCreated()
            throws Exception {

        mockMvc.perform(post("/transfers")
                .content("{\"sourceAccountNumber\": \"22222\",\"destinationAccountNumber\": \"11111\",\"amount\": \"34.34\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenTransferRequest_whenAmountIsInvalid_thenBadRequest()
            throws Exception {

        mockMvc.perform(post("/transfers")
                .content("{\"sourceAccountNumber\": \"22222\",\"destinationAccountNumber\": \"11111\",\"amount\": \"34sdf.34\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.errors.[0]", is("amount is not valid")));
    }

    @Test
    public void givenTransferRequest_whenSourceAndDestinationAccountNumberIsSame_thenBadRequest()
            throws Exception {

        mockMvc.perform(post("/transfers")
                .content("{\"sourceAccountNumber\": \"22222\",\"destinationAccountNumber\": \"22222\",\"amount\": \"34.34\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.errors.[0]", is("source and destination accountNumber can not be same")));
    }

    @Test
    public void givenTransferRequest_whenFundIsInsufficient_thenBadRequest()
            throws Exception {

        mockMvc.perform(post("/transfers")
                .content("{\"sourceAccountNumber\": \"22222\",\"destinationAccountNumber\": \"11111\",\"amount\": \"1111134.34\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.errors.[0]", is("Insufficient amount in source account")));
    }
}
