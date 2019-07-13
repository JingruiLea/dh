package edu.neu.dh.mapper;

import edu.neu.dh.bean.ScaleScore;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper

public interface ScaleScoreMapper {
    @Delete({
        "delete from scale_score",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into scale_score (patient_id, score, ",
        "`time`)",
        "values (#{patient_id,jdbcType=INTEGER}, #{score,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ScaleScore record);

    @Select({
        "select",
        "id, patient_id, score, `time`",
        "from scale_score",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="patient_id", property="patient_id", jdbcType=JdbcType.INTEGER),
        @Result(column="score", property="score", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR)
    })
    ScaleScore selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, patient_id, score, `time`",
        "from scale_score"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="patient_id", property="patient_id", jdbcType=JdbcType.INTEGER),
        @Result(column="score", property="score", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR)
    })
    List<ScaleScore> selectAll();

    @Update({
        "update scale_score",
        "set patient_id = #{patient_id,jdbcType=INTEGER},",
          "score = #{score,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ScaleScore record);
}