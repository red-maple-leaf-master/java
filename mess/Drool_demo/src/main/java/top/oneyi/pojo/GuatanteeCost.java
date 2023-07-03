package top.oneyi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuatanteeCost {

    /**
     * 保证金金额
     */
    private Double amount;
    /**
     * 保费金额
     */
    private Double cost;
}
