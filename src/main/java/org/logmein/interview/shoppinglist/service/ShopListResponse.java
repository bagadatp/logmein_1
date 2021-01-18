package org.logmein.interview.shoppinglist.service;

import org.logmein.interview.shoppinglist.common.ShopListError;
import org.logmein.interview.shoppinglist.model.EmptyJsonResponse;
import org.logmein.interview.shoppinglist.model.ErrorJsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ShopListResponse {

    public static ResponseEntity createNilResponseOk() {
        return new ResponseEntity(null, HttpStatus.OK);
    }

    public static ResponseEntity createEmptyResponseOk() {
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    public static ResponseEntity createEmptyResponseErr() {
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity createResponseOk(Object obj) {
        return new ResponseEntity(obj, HttpStatus.OK);
    }

    public static ResponseEntity createResponseErr() {
        ErrorJsonResponse errResp = new ErrorJsonResponse();
        return new ResponseEntity(errResp, HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity createResponseErr(ShopListError errCode) {
        ErrorJsonResponse errResp = new ErrorJsonResponse(errCode);
        return new ResponseEntity(errResp, HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity createResponseErr(ShopListError errCode, String errMsg) {
        ErrorJsonResponse errResp = new ErrorJsonResponse(errCode, errMsg);
        return new ResponseEntity(errResp, HttpStatus.BAD_REQUEST);
    }

}
