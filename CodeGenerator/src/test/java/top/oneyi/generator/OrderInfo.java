package top.oneyi.generator;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/12/14/9:39
 */
@Data
public class OrderInfo {
    private String userId;
    private String orderId;
    private String userName;
    private String goodName;
    private String goodId;
}
