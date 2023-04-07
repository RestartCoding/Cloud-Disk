package com.xb.cloud.disk.vo;

import lombok.Data;

/**
 * @author jack
 */
@Data
public class ApiResponse<E> {

    private static final String OK = "0";

    private static final String FAIL = "1";

    private String code;

    private String msg;

    private E data;

    public static <E> ApiResponse<E> ok(E data, String msg) {
        return of(OK, data, msg);
    }

    public static <E> ApiResponse<E> ok(E data) {
        return ok(data, "");
    }

    public static <E> ApiResponse<E> fail(E data, String msg) {
        return of(FAIL, data, msg);
    }

    public static <E> ApiResponse<E> of(String code, E data, String msg) {
        ApiResponse<E> apiResponse = new ApiResponse<>();
        apiResponse.setCode(code);
        apiResponse.setMsg(msg);
        apiResponse.setData(data);
        return apiResponse;
    }
}
