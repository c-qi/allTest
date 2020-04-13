package org.zhire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zhire.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper // 声明mapper接口
public interface UserMapper extends BaseMapper<User> {
    @Select("Select * from user where name = #{name} and password = #{pass}")
    User selectByParam(@Param("name") String name, @Param("pass") String pass);
}
