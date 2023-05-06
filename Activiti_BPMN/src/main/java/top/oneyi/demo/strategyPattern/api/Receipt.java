package top.oneyi.demo.strategyPattern.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private String name;
    private String message;
}
