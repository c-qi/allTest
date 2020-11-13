package org.zhire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhire.pojo.User;

@Mapper // 声明mapper接口
public interface UserMapper extends BaseMapper<User> {
    @Select("Select * from user where user_name = #{name} and pass_word = #{pass}")
    User selectByParam(@Param("name") String name, @Param("pass") String pass);
}
