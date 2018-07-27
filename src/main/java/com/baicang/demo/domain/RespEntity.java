package com.baicang.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : zhaipuhong
 * @date : 2018/3/18
 */
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class RespEntity<T> {
	/** 通配符 **/
	public static final String						WILDCARD_ALL	= "*";
	/** 响应状态码 **/
	private volatile String							statusCode		= StatusCode.BAD_REQUEST.getStatusCode();
	/** 响应状态码对应的提示信息 **/
	private volatile String							statusMessage	= StatusCode.BAD_REQUEST.getStatusMessage();
	/** 响应内容 **/
	private volatile T								responseContent	= null;
	private volatile String							prefix      	= null;
	/** 服务器当前时间戳 **/
	private volatile Long 							timestamp		= System.currentTimeMillis();

	@JsonIgnore
	private volatile String							filterFields	= WILDCARD_ALL;

	/**
	 * 自定义导出标题，key需与数据对象的属性名对应
	 */
	@JsonIgnore
	private volatile LinkedHashMap<String, String>	exportTitleMap;

	public RespEntity() {
	}

	public RespEntity(final String statusCode) {
		this(statusCode, null, null);
	}

	public RespEntity(final String statusCode, final String statusMessage) {
		this(statusCode, statusMessage, null);
	}

	public RespEntity(final String statusCode, final String statusMessage, final T responseContent) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.responseContent = responseContent;
	}

	public RespEntity(final String statusCode, final String statusMessage, final T responseContent, final String prefix){
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.responseContent = responseContent;
		this.prefix = prefix;
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity empty() {
		return new RespEntity();
	}

	/**
	 * 成功请求,成功状态码自行指定
	 *
	 * @param ok
	 * @param message
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static RespEntity ok(final StatusCode ok, final String message) {
		return new RespEntity(ok.getStatusCode(), message);
	}

	/**
	 * 失败请求,失败状态码自行指定
	 *
	 * @param fail
	 * @param message
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static RespEntity badRequest(final StatusCode fail, final String message) {
		return new RespEntity(fail.getStatusCode(), message);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity ok() {
		return ok(StatusCode.OK.getStatusCode(), StatusCode.OK.getStatusMessage());
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity ok(final String message) {
		return ok(StatusCode.OK.getStatusCode(), message);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity ok(final String ok, final String message) {
		return new RespEntity(ok, message);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity okPrefix(final Object responseContent,final String prefix){
		return new RespEntity(StatusCode.OK.getStatusCode(), StatusCode.OK.getStatusMessage(),responseContent,prefix);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity error() {
		return error(StatusCode.ERROR.getStatusCode(), StatusCode.ERROR.getStatusMessage());
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity error(final String message) {
		return error(StatusCode.ERROR.getStatusCode(), message);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity error(final String error, final String message) {
		return new RespEntity(error, message);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity error(final StatusCode statusCode) {
		return new RespEntity(statusCode.getStatusCode(), statusCode.getStatusMessage());
	}

    @SuppressWarnings("rawtypes")
    public static RespEntity expire() {
        return new RespEntity(StatusCode.EXPIRE.getStatusCode(), StatusCode.EXPIRE.getStatusMessage());
    }

    @SuppressWarnings("rawtypes")
    public static RespEntity expire(final String message) {
        return error(StatusCode.ERROR.getStatusCode(), message);
    }

    @SuppressWarnings("rawtypes")
    public static RespEntity expire(final String error, final String message) {
        return new RespEntity(error, message);
    }

    @SuppressWarnings("rawtypes")
    public static RespEntity expire(final StatusCode statusCode) {
        return new RespEntity(statusCode.getStatusCode(), statusCode.getStatusMessage());
    }

	@SuppressWarnings("rawtypes")
	public static RespEntity internalServerError() {
		return new RespEntity(StatusCode.INTERNAL_SERVER_ERROR.getStatusCode(), StatusCode.INTERNAL_SERVER_ERROR.getStatusMessage());
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity internalServerError(final String message) {
		return new RespEntity(StatusCode.INTERNAL_SERVER_ERROR.getStatusCode(), message);
	}


    public static RespEntity resourcesUnMatch() {
        return new RespEntity(StatusCode.RESOURCES_UN_MATCH.getStatusCode(), StatusCode.RESOURCES_UN_MATCH.getStatusMessage());
    }

	public static RespEntity resourcesUnMatch(StatusCode statusCode) {
		return new RespEntity(statusCode.getStatusCode(), statusCode.getStatusMessage());
	}

    public static RespEntity resourcesUnMatch(final String message) {
        return new RespEntity(StatusCode.RESOURCES_UN_MATCH.getStatusCode(), message);
    }

    public static RespEntity resourcesNotFound() {
        return new RespEntity(StatusCode.RESOURCES_NOT_FOUND.getStatusCode(), StatusCode.RESOURCES_NOT_FOUND.getStatusMessage());
    }

    public static RespEntity resourcesNotFound(final String message) {
        return new RespEntity(StatusCode.RESOURCES_NOT_FOUND.getStatusCode(), message);
    }

	@SuppressWarnings("rawtypes")
	public static RespEntity badRequest() {
		return badRequest(StatusCode.BAD_REQUEST.getStatusCode(), StatusCode.BAD_REQUEST.getStatusMessage());
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity badRequest(final String message) {
		return badRequest(StatusCode.BAD_REQUEST.getStatusCode(), message);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity badRequest(final String fail, final String message) {
		return new RespEntity(fail, message);
	}

    @SuppressWarnings("rawtypes")
    public static RespEntity resourcesExist() {
        return new RespEntity(StatusCode.RESOURCES_EXIST.getStatusCode(), StatusCode.RESOURCES_EXIST.getStatusMessage());
    }

    @SuppressWarnings("rawtypes")
    public static RespEntity resourcesExist(final String message) {
        return new RespEntity(StatusCode.RESOURCES_EXIST.getStatusCode(), message);
    }

    @SuppressWarnings("rawtypes")
    public static RespEntity resourcesExist(final String fail, final String message) {
        return new RespEntity(fail, message);
    }

    @SuppressWarnings("rawtypes")
    public static RespEntity resourcesCantEmpty() {
        return new RespEntity(StatusCode.RESOURCES_CANT_EMPTY.getStatusCode(), StatusCode.RESOURCES_CANT_EMPTY.getStatusMessage());
    }

    @SuppressWarnings("rawtypes")
    public static RespEntity resourcesCantEmpty(final String message) {
        return new RespEntity(StatusCode.RESOURCES_CANT_EMPTY.getStatusCode(), message);
    }

    @SuppressWarnings("rawtypes")
    public static RespEntity resourcesCantEmpty(final String fail, final String message) {
        return new RespEntity(fail, message);
    }

	@SuppressWarnings("rawtypes")
	public static RespEntity forbidden() {
		return forbidden(StatusCode.FORBIDDEN.getStatusMessage());
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity forbidden(final String message) {
		return new RespEntity(StatusCode.FORBIDDEN.getStatusCode(), message);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity unauthorized() {
		return unauthorized(StatusCode.UNAUTHORIZED.getStatusMessage());
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity unauthorized(final String message) {
		return new RespEntity(StatusCode.UNAUTHORIZED.getStatusCode(), message);
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity serviceUnavailable() {
		return serviceUnavailable(StatusCode.SERVICE_UNAVAILABLE.getStatusMessage());
	}

	@SuppressWarnings("rawtypes")
	public static RespEntity serviceUnavailable(final String message) {
		return new RespEntity(StatusCode.SERVICE_UNAVAILABLE.getStatusCode(), message);
	}

	/**
	 * 给 responseContent 添加内容
	 * <p>
	 * 
	 * <pre>
	 *     ResponseEntity.ok( "success" )
	 *                   .add( "username", "zhaipuhong" )
	 *                   .add( "password", "123456" )
	 *                   .add( "ip", "localhost" );
	 *
	 *     ResponseEntity{statusCode='200', statusMessage='success', filterFields='*', responseContent={password=123456, ip=localhost, username=zhaipuhong}}
	 * </pre>
	 *
	 * @param key : <code>String</code>类型
	 * @param value : <code>Object</code>类型
	 * @return <code>this</code>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RespEntity add(final String key, final Object value) {
		if (null == this.responseContent) {
			this.responseContent = (T) new HashMap<String, Object>();
			Map<String, Object> content = (Map<String, Object>) this.responseContent;
			content.put(key, value);
			return this;
		}
		if (!(this.responseContent instanceof Map)) {
			return this;
		}
		((Map) this.responseContent).put(key, value);
		return this;
	}


	/**
	 * 非分页-设置过滤字段
	 * <p>
	 * <b style="color:red">注意只会过滤 responseContent 中的内容</b>
	 *
	 * @return this
	 */
	@SuppressWarnings("rawtypes")
	public RespEntity setFilterFields(final String filterFields) {
		if (null == filterFields || WILDCARD_ALL.equals(filterFields)) {
			return this;
		}
		StringBuilder builder = new StringBuilder(WILDCARD_ALL).append(",responseContent[");
		builder.append(filterFields.trim()).append("]");
		this.filterFields = builder.toString();
		return this;
	}


	/**
	 * 分页结果-设置过滤字段
	 * <p>
	 * <b style="color:red">注意只会过滤 responseContent 中list下的内容</b>
	 * <p>
	 * 默认指定为{@link com.github.pagehelper.PageInfo#list}
	 *
	 * @return this
	 */
	@SuppressWarnings("rawtypes")
	public RespEntity setFilterFieldsForPaging(final String filterFields) {
		if (null == filterFields || WILDCARD_ALL.equals(filterFields)) {
			return this;
		}
		StringBuilder builder = new StringBuilder(WILDCARD_ALL).append(",responseContent[");
		// 分页对象,则只对分页对象内的结果集进行处理
		builder.append("*,list[").append(filterFields.trim()).append("]]");
		this.filterFields = builder.toString();
		return this;
	}


	/**
	 * 是否成功
	 *
	 * @return 如果状态 <b style="color:red">是<code>StatusCode.OK</code></b> 则返回 <code>true</code>
	 */
	@JsonIgnore
	public boolean isOk() {
		return StatusCode.OK.getStatusCode().equals(this.getStatusCode());
	}

	/**
	 * 是否不成功
	 *
	 * @return "!" {@link #isOk()}
	 */
	@JsonIgnore
	public boolean isNotOk() {
		return !isOk();
	}

	/**
	 * 是否需要过滤字段
	 *
	 * @return "!" {@link #isNotFieldsFilter()}
	 */
	@JsonIgnore
	public boolean isFieldsFilter() {
		return !this.isNotFieldsFilter();
	}

	/**
	 * 是否不需要过滤字段
	 *
	 * @return 如果 <b style="color:red"> null == this.getFilterFields() || {@link #filterFields} <code>equals</code>
	 *         {@link #WILDCARD_ALL} </b>则返回 <code>true</code>
	 */
	@JsonIgnore
	public boolean isNotFieldsFilter() {
		return null == this.getFilterFields() || WILDCARD_ALL.equals(this.getFilterFields());
	}


    public enum StatusCode {
        /** [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。 **/
		OK("10000", "请求成功"),
		ERROR("99999", "请求失败"),
        EXPIRE("20000", "资源过期"),
        /** [POST/PUT/PATCH]：用户新建或修改数据成功。 **/
		CREATED("10201", "操作成功"),
		/**
		 * Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
		 **/
		OK_NOT_HANDLER("10202", "收到请求"),
		/**
		 * NO CONTENT - [DELETE]：用户删除数据成功。
		 **/
		NO_CONTENT("10204", "数据删除成功"),
		/**
		 * INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
		 **/
		BAD_REQUEST("10400", "请求失败"),
		/**
		 * Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
		 **/
		UNAUTHORIZED("10401", "身份验证失败"),
		/**
		 * Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
		 **/
		FORBIDDEN("10403", "权限不足"),
		/**
		 * NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
		 **/
		NOT_FOUND("10404", "记录不存在"),
		METHOD_NOT_ALLOWED("10405", "目标资源不支持该请求方式"),
		/**
		 * Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
		 **/
		NOT_ACCEPTABLE("10404", "请求格式错误"),
		REQUEST_TIME_OUT("10408", "服务器等待客户端发送的请求时间过长,超时"),
		/**
		 * Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
		 **/
		GONE("10410", "请求的资源被永久删除"),
		/**
		 * 请求格式正确，但是由于含有语义错误，无法响应
		 **/
		UNPROCESSABLE_ENTITY("10422", "验证失败"),
		TOO_MANY_REQUESTS("10429", "太多的请求"),
		TRADE_REPETITION("10460", "重复交易"),
		/**
		 * INTERNAL SERVER INTERNAL_SERVER_ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
		 **/
		INTERNAL_SERVER_ERROR("10500", "请求出错"),
		SERVICE_UNAVAILABLE("10503", "由于临时的服务器维护或者过载,服务器当前无法处理请求"),
		RESOURCES_UN_MATCH("10504", "验证码不匹配"),
		RESOURCES_NOT_FOUND("10505", "数据资源不存在"),
        RESOURCES_EXIST("10506", "数据资源已存在"),
        RESOURCES_CANT_EMPTY("10507", "数据资源不能为空"),

		USER_NOT_EXIST("70000", "用户不存在"),
		PAY_PASS_EXIST("70001", "支付密码已存在"),
		PAY_PASS_LENGTH_ERR("70002", "支付密码长度不正确"),
		USER_NOT_IN_GROUP("70003", "用户不属于该客户组，越权操作"),
		INSUFFICIENT_BALANCE("70004", "余额不足"),
        ILLEGAL_AMOUNT("70005", "金额不合法"),;

		private final String	statusCode;
		private final String	statusMessage;

		StatusCode(String statusCode, String statusMessage) {
			this.statusCode = statusCode;
			this.statusMessage = statusMessage;
		}

		public String getStatusMessage() {
			return statusMessage;
		}

		public String getStatusCode() {
			return statusCode;
		}

	}


}
