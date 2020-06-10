package show.utils;

import java.io.Serializable;

/**
 * 控制器向客户端响应结果的数据类型
 *
 * @param <T> 如果控制器会向客户端响应某些数据，则表示数据的类型
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -2077071413152331484L;

    private Integer state;//1,2
    private String message;//错误信息
    private T data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer state) {
        super();
        this.state = state;
    }

    public ResponseResult(Integer state, T data) {
        super();
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "ResponseResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
}
