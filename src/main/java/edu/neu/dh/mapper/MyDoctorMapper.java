package edu.neu.dh.mapper;

import edu.neu.dh.bean.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MyDoctorMapper {
    @Select("select username,password from doctor where username = #{username}")
    Doctor selectByUserName(@Param("username") String username);
}
