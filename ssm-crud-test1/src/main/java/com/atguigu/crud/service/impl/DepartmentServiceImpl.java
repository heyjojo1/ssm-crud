package com.atguigu.crud.service.impl;

import com.atguigu.crud.mapper.DepartmentMapper;
import com.atguigu.crud.pojo.Department;
import com.atguigu.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jojo
 * @create 2022-11-15 19:21
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getalldept() {
        List<Department> depts = departmentMapper.selectByExample(null);
        return depts;
    }
}
