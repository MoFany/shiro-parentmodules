package com.mofany.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description BaseResponse 返回json格式的响应结果工具类
 */
@Setter
@Getter
@ToString
public class BaseResponse {
    private Integer code;
    private String message;
    private Object data;

    /**
     * 私有无参构造器
     * */
    private BaseResponse(){

    }

    /**
     * 成功响应
     * */
    public static BaseResponse ok(){
        BaseResponse baseResponse=new BaseResponse();
        //设置响应状态码
        baseResponse.setCode(2000);
        //设置响应状态码代表的消息
        baseResponse.setMessage("成功");
        return baseResponse;
    }

    /**
     * 自定义成功响应
     * */
    public static BaseResponse ok(Integer code,String message){
        BaseResponse baseResponse=new BaseResponse();
        //设置响应状态码
        baseResponse.setCode(code);
        //设置响应状态码代表的消息
        baseResponse.setMessage(message);
        return baseResponse;
    }

    /**
     * 带响应数据的自定义成功响应
     * */
    public static BaseResponse ok(Integer code,String message,Object data){
        BaseResponse baseResponse=new BaseResponse();
        //设置响应状态码
        baseResponse.setCode(code);
        //设置响应状态码代表的消息
        baseResponse.setMessage(message);
        //设置响应成功后的数据
        baseResponse.setData(data);
        return baseResponse;
    }

    /**
     * 失败响应
     * */
    public static BaseResponse failure(){
        BaseResponse baseResponse=new BaseResponse();
        //设置响应状态码
        baseResponse.setCode(3000);
        //设置响应状态码代表的消息
        baseResponse.setMessage("失败");
        return baseResponse;
    }

    /**
     * 自定义失败响应
     * */
    public static BaseResponse failure(Integer code,String message){
        BaseResponse baseResponse=new BaseResponse();
        //设置响应状态码
        baseResponse.setCode(code);
        //设置响应状态码代表的消息
        baseResponse.setMessage(message);
        return baseResponse;
    }
}
