package edu.neu.dh.controller;

import edu.neu.dh.bean.TrainScore;
import edu.neu.dh.mapper.TrainScoreMapper;
import edu.neu.dh.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("train")
public class TrainScoreController {

    @Autowired
    TrainScoreMapper trainScoreMapper;

    @RequestMapping("deleteByPrimaryKey")
    public Map deleteByPrimaryKey(@RequestParam("id") Integer id) {
        if (trainScoreMapper.deleteByPrimaryKey(id) == 1) {
            return Response.ok();
        }
        return Response.error("");
    }

    @RequestMapping("insert")
    public Map insert(@RequestBody TrainScore record) {
        if (trainScoreMapper.insert(record) == 1) {
            return Response.ok();
        }
        return Response.ok();
    }

    @RequestMapping("selectByPrimaryKey")
    public Map selectByPrimaryKey(@RequestParam("id") Integer id) {
        TrainScore scaleScore = trainScoreMapper.selectByPrimaryKey(id);
        return Response.ok(scaleScore);
    }

    @RequestMapping("selectAll")
    public Map selectAll() {
        return Response.ok(trainScoreMapper.selectAll());
    }

    @RequestMapping("updateByPrimaryKey")
    public Map updateByPrimaryKey(@RequestBody TrainScore record) {
        int res = trainScoreMapper.updateByPrimaryKey(record);
        return Response.ok(res);
    }

    @RequestMapping("selectByPatientId")
    public Map selectByPatientId(@RequestParam Integer id) {
        List<TrainScore> res = trainScoreMapper.selectByPatientId(id);
        return Response.ok(res);
    }
}
