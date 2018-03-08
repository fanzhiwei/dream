package net.fanzhiwei.core.mvc;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author fanzhiwei
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    private boolean success;
    private T data;
    private Integer errCode;
    private String errMsg;

    public ApiResult(boolean success, T content, Integer errCode, String errMsg) {
        this.success = success;
        this.data = content;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public static final <T> ApiResult<T> createSuccessEmptyInstance() {
        return new ApiResult(true, null, null, null);
    }

    public static <T> ApiResult<T> createSuccessInstance(T content) {
        return new ApiResult(true, content, null, null);
    }

    public static final <T> ApiResult<T> createErrorInstance(Integer errCode, String errMsg) {
        return new ApiResult(false, null, errCode, errMsg);
    }

    // ***************<strong> getters and setters </strong>****************<strong> //

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("success=").append(success);
        sb.append("errCode=").append(errCode);
        sb.append("errMsg=").append(errMsg);
        sb.append("data=").append(data);
        return sb.toString();
    }

}
