package edu.neu.dh.mapper;

import edu.neu.dh.bean.TrainScore;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper
public interface TrainScoreMapper {
    @Delete({
        "delete from train_score",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into train_score (patient_id, score, ",
        "`time`, `path`)",
        "values (#{patient_id,jdbcType=INTEGER}, #{score,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=VARCHAR}, #{path,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TrainScore record);

    @Select({
        "select",
        "id, patient_id, score, `time`, `path`",
        "from train_score",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="patient_id", property="patient_id", jdbcType=JdbcType.INTEGER),
        @Result(column="score", property="score", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR)
    })
    TrainScore selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, patient_id, score, `time`, `path`",
        "from train_score"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="patient_id", property="patient_id", jdbcType=JdbcType.INTEGER),
        @Result(column="score", property="score", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR)
    })
    List<TrainScore> selectAll();

    @Update({
        "update train_score",
        "set patient_id = #{patient_id,jdbcType=INTEGER},",
          "score = #{score,jdbcType=VARCHAR},",
          "`time` = #{time,jdbcType=VARCHAR},",
          "`path` = #{path,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TrainScore record);

    @Select({
            "select",
            "id, patient_id, score, `time`, `path`",
            "from train_score ",
            "where patient_id=#{patientId}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="patient_id", property="patient_id", jdbcType=JdbcType.INTEGER),
            @Result(column="score", property="score", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR),
            @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR)
    })
    List<TrainScore> selectByPatientId(@Param("patientId") Integer patientId);
}