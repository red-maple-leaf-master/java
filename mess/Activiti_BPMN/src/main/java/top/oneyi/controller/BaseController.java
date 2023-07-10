package top.oneyi.controller;


import top.oneyi.pojo.BasePojo;
import top.oneyi.pojo.dto.RespDto;
import top.oneyi.pojo.RespCode;

import java.util.List;

public abstract class BaseController<T extends BasePojo> {
    /**
     * 新增或修改
     *
     * @param t
     * @return
     */
    public abstract RespDto saveOrUpdate(T t);

    /**
     * 批量新增或者修改
     *
     * @param list
     * @return
     */
    public abstract RespDto saveOrUpdatebatch(List<T> list);

    /**
     * 删除
     *
     * @param t
     * @return
     */
    public abstract RespDto delete(T t);

    /**
     * 批量删除
     *
     * @param t
     * @return
     */
    public abstract RespDto deleteBatch(List<T> t);

    /**
     * 据主键查询
     *
     * @param t
     * @return
     */
    public abstract RespDto findById(T t);

    /**
     * 按条件查询一个
     *
     * @param t
     * @return
     */
    public abstract RespDto findOne(T t);

    /**
     * 条件查询
     *
     * @param t
     * @return
     */
    public abstract RespDto findByEntity(T t);

    /**
     * 查询所有
     *
     * @param t
     * @return
     */
    public abstract RespDto queryAll(T t);

    /**
     * 分页查询
     *
     * @param t
     * @return
     */
    public abstract RespDto queryByPage(T t);

    /**
     * 响应成功:无响应数据
     *
     * @return
     */
    public RespDto success() {
        return new RespDto.Builder().build();
    }

    /**
     * 响应成功:有响应数据
     *
     * @param data
     * @return
     */
    public RespDto success(Object data) {
        return new RespDto.Builder().setData(data).build();
    }

    /**
     * 响应失败:自定义失败消息
     *
     * @param message
     * @return
     */
    public RespDto failure(String message) {
        return new RespDto.Builder().setCode(RespCode.FAILURE).setMessage(message).build();
    }

    /**
     * 响应异常: 自行义异常消息
     *
     * @param message
     * @return
     */
    public RespDto error(String message) {
        return new RespDto.Builder().setCode(RespCode.ERROR).setMessage(message).build();
    }

}
