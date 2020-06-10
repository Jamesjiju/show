package show.service.ex;

/**
 * 查询数据不存在异常
 */
public class SelectException extends ServiceException{

	private static final long serialVersionUID = -2111882581918102362L;

	public SelectException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SelectException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SelectException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SelectException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SelectException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
