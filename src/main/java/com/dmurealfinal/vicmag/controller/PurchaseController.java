package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.ReadPermissionDTO;
import com.dmurealfinal.vicmag.domain.dto.SinglePurchaseDTO;
import com.dmurealfinal.vicmag.domain.dto.SubscribeDTO;
import com.dmurealfinal.vicmag.service.PurchaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public boolean postSinglePurchase(HttpServletRequest request, HttpServletResponse response, @RequestBody SinglePurchaseDTO singlePurchaseDTO) throws JsonProcessingException {
        logger.info("[postSinglePurchase] 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("SinglePurchase : " + objectMapper.writeValueAsString(singlePurchaseDTO));

        if(purchaseService.isSinglePurchased(singlePurchaseDTO.getPurchase().getUserId(), singlePurchaseDTO.getMagazineSeq())) {
            response.setStatus(HttpStatus.CONFLICT.value());
            return false;
        }

        singlePurchaseDTO.getPurchase().setPurchaseType("single");
        purchaseService.saveSinglePurchase(singlePurchaseDTO);

        return true;
    }

    /** 구독 구매 API */
    @PostMapping("/subscribe")
    public boolean postSubscribePurchase(HttpServletRequest request, HttpServletResponse response, @RequestBody SubscribeDTO subscribeDTO) throws JsonProcessingException {
        logger.info("[postSubscribePurchase] 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Subscribe : " + objectMapper.writeValueAsString(subscribeDTO));

        if(purchaseService.isSubscribed(subscribeDTO.getPurchase().getUserId(), subscribeDTO.getMagazineBoardSeq(), subscribeDTO.getStartDateTime(), subscribeDTO.getEndDateTime())) {
            response.setStatus(HttpStatus.CONFLICT.value());
            return false;
        }

        subscribeDTO.getPurchase().setPurchaseType("subscribe");
        purchaseService.saveSubscribe(subscribeDTO);

        return true;
    }

    /** 단건 구매 취소 API */
    @DeleteMapping("single")
    public void deleteSinglePurchase(HttpServletRequest request, HttpServletResponse response, @RequestBody SinglePurchaseDTO singlePurchaseDTO) throws JsonProcessingException {
        logger.info("[deleteSinglePurchase] 요청");
        purchaseService.deleteSinglePurchase(singlePurchaseDTO.getPurchaseSeq());
    }

    /** 구독 구매 취소 API */
    @DeleteMapping("subscribe")
    public void deleteSubscribe(HttpServletRequest request, HttpServletResponse response, @RequestBody SubscribeDTO subscribeDTO) throws JsonProcessingException {
        logger.info("[deleteSubscribe] 요청");
        purchaseService.deleteSubscribe(subscribeDTO.getPurchaseSeq());
    }

    /** 읽기권한 API */
//    @GetMapping("/permission/{boardSeq}")
//    public ReadPermissionDTO getReadPermission(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer boardSeq) {
//        logger.info("[getReadPermission] 요청");
//        purchaseService.getReadPermission(boardSeq);
//    }
}
