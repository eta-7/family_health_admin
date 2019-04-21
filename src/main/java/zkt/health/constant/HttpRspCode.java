package zkt.health.constant;

/**
 * 这里自定义一些响应码，不沿用HTTP的
 */
public enum HttpRspCode {
    SUCCESS(1,"操作成功"),
    BIZ_FAIL(0,"操作失败"),
    REQ_PARAM_IS_NULL(-2,"请求数据为空或不合法"),
    DATA_NOT_EXIST(-1,"数据不存在"),
    DATA_EXIST(-3,"数据已存在")
    ;
    private int code;
    private String desc;

    HttpRspCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
