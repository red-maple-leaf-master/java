package top.oneyi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private Integer code;
    private String message;
    private Object data;

    public static ResultDTO ok() {
        return new ResultDTO(200, "success", null);
    }

    public static ResultDTO err(String message) {
        return new ResultDTO(400, message, null);
    }
}
