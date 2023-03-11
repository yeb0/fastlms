package com.zerobase.fastlms.common.model;

import lombok.Data;

@Data
public class ResponseResult { // client 에 내림

    ResponseResultHeader header;
    Object body;

    public ResponseResult(boolean result, String message) {
        header = new ResponseResultHeader(result, message);
    }

    public ResponseResult(boolean result) {
        header = new ResponseResultHeader(result);
    }
}
