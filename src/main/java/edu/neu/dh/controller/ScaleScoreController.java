package edu.neu.dh.controller;

import edu.neu.dh.bean.ScaleScore;
import edu.neu.dh.mapper.ScaleScoreMapper;
import edu.neu.dh.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("scale")
public class ScaleScoreController {

    @Autowired
    ScaleScoreMapper scaleScoreMapper;

    @RequestMapping("deleteByPrimaryKey")
    public Map deleteByPrimaryKey(@RequestParam("id") Integer id) {
        if (scaleScoreMapper.deleteByPrimaryKey(id) == 1) {
            return Response.ok();
        }
        return Response.error("");
    }

    @RequestMapping("insert")
    public Map insert(@RequestBody ScaleScore record) {
        if (scaleScoreMapper.insert(record) == 1) {
            return Response.ok();
        }
        return Response.ok();
    }

    @RequestMapping("selectByPrimaryKey")
    public Map selectByPrimaryKey(@RequestParam("id") Integer id) {
        ScaleScore scaleScore = scaleScoreMapper.selectByPrimaryKey(id);
        return Response.ok(scaleScore);
    }

    @RequestMapping("selectAll")
    public Map selectAll() {
        return Response.ok(scaleScoreMapper.selectAll());
    }

    @RequestMapping("updateByPrimaryKey")
    public Map updateByPrimaryKey(@RequestBody ScaleScore record) {
        int res = scaleScoreMapper.updateByPrimaryKey(record);
        return Response.ok(res);
    }
}
