package cn.downrice.graduation.library.model;

import java.io.Serializable;

/**
 * 统一返回值
 * @author 下饭
 * @param <T>
 */
public class ReturnResponse<T> implements Serializable {
    private static final long serialVersionUID = 1064398624968955397L;

    /**
     * 返回状态代码
     */
    private int code;
    /**
     * 返回状态描述
     */
    private String msg;
    private T content;

    public ReturnResponse(){

    }

    public ReturnResponse(int code, String msg, T content){
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
