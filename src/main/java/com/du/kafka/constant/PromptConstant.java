package com.du.kafka.constant;

/**
 * 提示常量类
 *
 * @author dxy
 */
public final class PromptConstant {
    private PromptConstant(){}

    /**
     * 数据为空
     */
    public static final String DATA_IS_NULL = "数据为空";
    /**
     * 成功
     */
    public static final String SUCCESS = "成功";
    /**
     * 失败
     */
    public static final String FAIL = "失败";
    /**
     * 系统错误，请联系管理员
     */
    public static final String SYSTEM_ERROR = "系统错误，请联系管理员";
    /**
     * aop for controller transform data to json error
     */
    public static final String AOP_JSON_ERROR = "aop for controller transform data to json error";
    /**
     * aop exception
     */
    public static final String AOP_EXCEPTION = "aop exception";
    /**
     * mongo批量保存条数为空
     */
    public static final String MONGO_BATCH_SAVE_SIZE_IS_NULL = "mongo批量保存条数为空";
}
