package edu.neu.dh.controller;

import edu.neu.dh.bean.Doctor;
import edu.neu.dh.mapper.DoctorMapper;
import edu.neu.dh.mapper.MyDoctorMapper;
import edu.neu.dh.utils.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    MyDoctorMapper myDoctorMapper;

    @RequestMapping("deleteByPrimaryKey")
    public Map deleteByPrimaryKey(@RequestParam("id") Integer id) {
        return Response.ok(doctorMapper.deleteByPrimaryKey(id));
    }

    @RequestMapping("insert")
    public Map insert(@RequestBody Doctor record) {
        return Response.ok(doctorMapper.insert(record));
    }

    @RequestMapping("selectByPrimaryKey")
    public Map selectByPrimaryKey(@RequestParam("id") Integer id) {
        return Response.ok(doctorMapper.selectByPrimaryKey(id));
    }

    @RequestMapping("selectAll")
    public Map selectAll() {
        return Response.ok(doctorMapper.selectAll());
    }

    @RequestMapping("updateByPrimaryKey")
    public Map updateByPrimaryKey(@RequestBody Doctor record) {
        return Response.ok(doctorMapper.updateByPrimaryKey(record));
    }

    @RequestMapping("login")
    public Map login(@RequestBody Map req){
        String username = (String) req.get("username");
        String password = (String) req.get("password");
        Map<String, Object> res = new HashMap();
        res.put("token", username);
        Doctor doctor = myDoctorMapper.selectByUserName(username);
        if (doctor !=null && doctor.getPassword().equals(password)){
            return Response.ok(res);
        }else{
            return Response.error("用户名或密码错误!");
        }
    }

    @RequestMapping("info")
    public Map info(){
        Map res = new HashMap();
        res.put("roles", new String[]{"admin"});
        return Response.ok(res);
    }
}
