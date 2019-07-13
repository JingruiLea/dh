package edu.neu.dh.controller;

import edu.neu.dh.bean.Patient;
import edu.neu.dh.mapper.PatientMapper;
import edu.neu.dh.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    PatientMapper patientMapper;

    @RequestMapping("deleteByPrimaryKey")
    public Map deleteByPrimaryKey(@RequestParam("id") Integer id) {
        if (patientMapper.deleteByPrimaryKey(id) == 1) {
            return Response.ok();
        }
        return Response.error("");
    }

    @RequestMapping("insert")
    public Map insert(@RequestBody Patient record) {
        if (patientMapper.insert(record) == 1) {
            return Response.ok();
        }
        return Response.ok();
    }

    @RequestMapping("selectByPrimaryKey")
    public Map selectByPrimaryKey(@RequestParam("id") Integer id) {
        Patient patient = patientMapper.selectByPrimaryKey(id);
        return Response.ok(patient);
    }

    @RequestMapping("selectAll")
    public Map selectAll() {
        return Response.ok(patientMapper.selectAll());
    }

    @RequestMapping("updateByPrimaryKey")
    public Map updateByPrimaryKey(@RequestBody Patient record) {
        int res = patientMapper.updateByPrimaryKey(record);
        return Response.ok(res);
    }
}
