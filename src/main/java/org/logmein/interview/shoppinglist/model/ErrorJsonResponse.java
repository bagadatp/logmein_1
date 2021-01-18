package org.logmein.interview.shoppinglist.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.logmein.interview.shoppinglist.common.ShopListError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorJsonResponse {

    private int code;
    private String description;
    private String message;

    public ErrorJsonResponse() {
        this.code = ShopListError.UNKNOWN_ERROR.value();
        this.description = ShopListError.UNKNOWN_ERROR.getDescription();
    }
    public ErrorJsonResponse(ShopListError code) {
        this.code = code.value();
        this.description = code.getDescription();
    }
    public ErrorJsonResponse(ShopListError code, String message) {
        this.code = code.value();
        this.description = code.getDescription();
        this.message = message;
    }

    public int getCode() { return this.code; }

    public void setCode(ShopListError code) { this.code = code.value(); }

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }
}
