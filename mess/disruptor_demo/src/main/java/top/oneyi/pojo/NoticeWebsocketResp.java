package top.oneyi.pojo;


import lombok.Data;

@Data
public class NoticeWebsocketResp<T> {


    private String noticeType;


    private T noticeInfo;

}
