package org.zhire.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Date 2021/2/23 21:02
 * @Author by chenqi
 */
@Data
@Accessors(chain = true) // 链式注解 .setXXX().setXXX()
public class UserDTO implements Serializable {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "姓名不能为空")
    private String name;
    private String password;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误",
            regexp = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
    private String email;
}
