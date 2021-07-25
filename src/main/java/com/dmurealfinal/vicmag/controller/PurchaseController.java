package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.SinglePurchaseDTO;
import com.dmurealfinal.vicmag.domain.dto.SubscribeDTO;
import com.dmurealfinal.vicmag.service.PurchaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PurchaseService purchaseService;

    /** 단건 구매 API */
    @PostMapping("/single")
    public void postSinglePurchase(HttpServletRequest request, HttpServletResponse response, @RequestBody SinglePurchaseDTO singlePurchaseDTO) throws JsonProcessingException {
        logger.info("[postSinglePurchase] 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("SinglePurchase : " + objectMapper.writeValueAsString(singlePurchaseDTO));
        singlePurchaseDTO.getPurchase().setPurchaseType("single");
        purchaseService.saveSinglePurchase(singlePurchaseDTO);
    }

    /** 구독 구매 API */
    @PostMapping("/subscribe")
    public void postSubscribePurchase(HttpServletRequest request, HttpServletResponse response, @RequestBody SubscribeDTO subscribeDTO) throws JsonProcessingException {
        logger.info("[postSubscribePurchase] 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Subscribe : " + objectMapper.writeValueAsString(subscribeDTO));
        subscribeDTO.getPurchase().setPurchaseType("subscribe");
        purchaseService.saveSubscribe(subscribeDTO);
    }
}