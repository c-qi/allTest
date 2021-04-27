package org.zhire.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Date 2021/2/23 21:02
 * @Author by chenqi
 */
@Data
@Accessors(chain = true) // 链式注解 .setXXX().setXXX()
@RequiredArgsConstructor(staticName = "of")
public class UserDTO2 implements Serializable {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    private String password;

    public static void main(String[] args) {
        UserDTO2 dto2 = UserDTO2.of()
                .setId(1L)
                .setName("cq")
                .setPassword("dq");
        System.out.println(dto2);
    }
}