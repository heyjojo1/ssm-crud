package com.atguigu.crud.controller;

import com.atguigu.crud.pojo.Department;
import com.atguigu.crud.pojo.Msg;
import com.atguigu.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jojo
 * @create 2022-11-15 19:19
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> depts = departmentService.getalldept();
        return Msg.success().add("deptList",depts);
    }

}
