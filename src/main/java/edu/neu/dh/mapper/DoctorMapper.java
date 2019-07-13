package edu.neu.dh.mapper;

import edu.neu.dh.bean.Doctor;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface DoctorMapper {
    @Delete({
        "delete from doctor",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into doctor (`name`, username, ",
        "`password`, hospital_name, ",
        "department_name)",
        "values (#{name,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{hospital_name,jdbcType=VARCHAR}, ",
        "#{department_name,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Doctor record);

    @Select({
        "select",
        "id, `name`, username, `password`, hospital_name, department_name",
        "from doctor",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="hospital_name", property="hospital_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_name", property="department_name", jdbcType=JdbcType.VARCHAR)
    })
    Doctor selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, `name`, username, `password`, hospital_name, department_name",
        "from doctor"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="hospital_name", property="hospital_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_name", property="department_name", jdbcType=JdbcType.VARCHAR)
    })
    List<Doctor> selectAll();

    @Update({
        "update doctor",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "username = #{username,jdbcType=VARCHAR},",
          "`password` = #{password,jdbcType=VARCHAR},",
          "hospital_name = #{hospital_name,jdbcType=VARCHAR},",
          "department_name = #{department_name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Doctor record);
}