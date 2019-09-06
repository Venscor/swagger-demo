package com.mo.swagger;

import com.alibaba.fastjson.JSON;
import com.mo.swagger.vo.BaseResponse;
import com.mo.swagger.vo.Order;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @description:
 * @author: MoXingwang 2018-07-16 16:47
 **/
@RestController
@RequestMapping("index")
public class IndexController {

    @ApiOperation(value = "常规接口")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ResponseEntity<String> test1() {

        return ResponseEntity.ok("11111");
    }

    @ApiOperation(value = "ApiResponses测试泛型对象接口")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = BaseResponse.class),
            @ApiResponse(code = 200, message = "Success", response = Order.class)
    })
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public BaseResponse<Order> test3() {

        return new BaseResponse("200", "success", new Order("11", "22"));
    }

    @ApiOperation(value = "不使用ApiResponses返回泛型对象")
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public BaseResponse<Order> test4() {

        return new BaseResponse("200", "success", new Order("11", "22"));
    }


    @RequestMapping(value = "/fastjson", method = RequestMethod.GET)
    public BaseResponse<Order> fasjson_crash() throws UnsupportedEncodingException {
        System.out.println("1234");
        String line = new String("[{\\x22a\\x22:\\x22a\\xB1ph.\\xCD\\x86\\xBEI\\xBA\\xC3\\xBCiM+\\xCE\\xCE\\x1E\\xDF7\\x1E\\xD9z\\xD9Q\\x8A}\\xD4\\xB2\\xD5\\xA0y\\x98\\x08@\\xE1!\\xA8\\xEF^\\x0D\\x7F\\xECX!\\xFF\\x06IP\\xEC\\x9F[\\x85;\\x02\\x817R\\x87\\xFB\\x1Ch\\xCB\\xC7\\xC6\\x06\\x8F\\xE2Z\\xDA^J\\xEB\\xBCF\\xA6\\xE6\\xF4\\xF7\\xC1\\xE3\\xA4T\\x89\\xC6\\xB2\\x5Cx]");
        line = line.replaceAll("\\\\x", "%");
        String decodeLog = URLDecoder.decode(line, "UTF-8");
        System.out.println(decodeLog);
        try {
            Object obj = JSON.parse(decodeLog);
//            obj = JSON.parse(DEATH_STRING);
        } catch (Exception e) {
        }
        return new BaseResponse("200", "Oops", new Order("11", "22"));
    }

}
