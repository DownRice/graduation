package cn.downrice.graduation.library.async;

public enum EventType {
    //点赞事件
    LIKE(0),
    //登陆事件
    LOGIN(1),
    //注册事件
    REG(2),
    //文档换事件
    CONVERT(3);

    private int  value;
    EventType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
