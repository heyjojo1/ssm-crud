package com.atguigu.crud.service.impl;

import com.atguigu.crud.mapper.EmployeeMapper;
import com.atguigu.crud.pojo.Employee;
import com.atguigu.crud.pojo.EmployeeExample;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jojo
 * @create 2022-11-12 20:02
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    /**
     * 查询员工列表
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<Employee> getAll(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Employee> emps = employeeMapper.selectByExampleWithDept(null);
        PageInfo<Employee> page = new PageInfo<>(emps,5);
        return page;
    }

    /**
     * 添加员工
     * @param employee
     */
    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 校验用户名是否可用
     * @param empName
     * @return true：代表姓名可用，false：代表不可用
     */
    @Override
    public boolean checkEmpName(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        int count = employeeMapper.countByExample(example);
        return count==0;
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @Override
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * 根据ID更新员工信息
     * @param employee
     */
    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
