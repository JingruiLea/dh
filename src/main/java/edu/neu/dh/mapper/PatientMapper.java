package edu.neu.dh.mapper;

import edu.neu.dh.bean.Patient;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface PatientMapper {
    @Delete({
        "delete from patient",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into patient (`name`, sex, ",
        "age, doctor_id, ",
        "disease_name)",
        "values (#{name,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=VARCHAR}, #{doctor_id,jdbcType=INTEGER}, ",
        "#{disease_name,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Patient record);

    @Select({
        "select",
        "id, `name`, sex, age, doctor_id, disease_name",
        "from patient",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.VARCHAR),
        @Result(column="doctor_id", property="doctor_id", jdbcType=JdbcType.INTEGER),
        @Result(column="disease_name", property="disease_name", jdbcType=JdbcType.VARCHAR)
    })
    Patient selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, `name`, sex, age, doctor_id, disease_name",
        "from patient"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.VARCHAR),
        @Result(column="doctor_id", property="doctor_id", jdbcType=JdbcType.INTEGER),
        @Result(column="disease_name", property="disease_name", jdbcType=JdbcType.VARCHAR)
    })
    List<Patient> selectAll();

    @Update({
        "update patient",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=VARCHAR},",
          "doctor_id = #{doctor_id,jdbcType=INTEGER},",
          "disease_name = #{disease_name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Patient record);
}