package org.zhire.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author chendongzhi
 * @date 17:222019/2/27 0027
 * @description 接口返回通用对象
 */
@Data
@ToString
public class ExpertCommonRspVo<T> implements Serializable {
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误描述
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public ExpertCommonRspVo(){ }

    public ExpertCommonRspVo(ResponseCodeEnum code){
        this.code=code.getCode();
        this.message=code.getMessage();
    }
    public ExpertCommonRspVo(T data, ResponseCodeEnum code){
        this.code=code.getCode();
        this.message=code.getMessage();
        this.data=data;
    }
    public ExpertCommonRspVo(T data){
        this(ResponseCodeEnum.SUCCESS);
        this.data=data;
    }



    /**
     * 判断是否成功
     * @return
     */
    public boolean isSuccess(){
        return ResponseCodeEnum.SUCCESS.getCode().equals(this.code);
    }

    /**
     * 获取成功对象
     * @param data
     * @param <C>
     * @return
     */
    public  static <C> ExpertCommonRspVo success(C data){
        ExpertCommonRspVo success=new ExpertCommonRspVo(ResponseCodeEnum.SUCCESS);
        success.data=data;
        return success;
    }


    /**
     * 获取失败对象
     * @return
     */
    public  static ExpertCommonRspVo failed(){
        return failed(null);
    }


    /**
     * 获取失败对象
     * @return
     */
    public  static <D> ExpertCommonRspVo<D> failed(D data){
        ExpertCommonRspVo faild=new ExpertCommonRspVo(data, ResponseCodeEnum.SYSTEM_ERROR);
        return faild;
    }
}