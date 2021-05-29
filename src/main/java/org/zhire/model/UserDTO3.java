package org.zhire.model;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Date 2021/2/23 21:02
 * @Author by chenqi
 */
@Builder
//@Data
public class UserDTO3 implements Serializable {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    private String password;

    public static void main(String[] args) {
        UserDTO3 dto2 = UserDTO3.builder()
                .id(1L)
                .name("cq")
                .password("cqew").build();
        System.out.println(dto2.id);
        System.out.println(dto2.name);
        System.out.println(dto2.password);
    }
}
