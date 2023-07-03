package top.oneyi.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.oneyi.pojo.RespCode;

@Data
@AllArgsConstructor
public class RespDto {
    private String code;
    private String message;
    private Object data;

    @Setter
    @Accessors(chain = true)
    public static class Builder {
        // 默认状态：成功
        private String code = RespCode.SUCCESS;
        private String message = "success";
        private Object data;

        public RespDto build() {
            return new RespDto(this.code, this.message, this.data);
        }
    }
}
