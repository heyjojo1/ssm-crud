package com.atguigu.crud.service;

import com.atguigu.crud.pojo.Employee;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author jojo
 * @create 2022-11-12 20:48
 */
public interface EmployeeService {

    PageInfo<Employee> getAll(Integer pageNum);

    void saveEmp(Employee employee);

    boolean checkEmpName(String empName);

    Employee getEmp(Integer id);

    void updateEmp(Employee employee);

    void deleteEmp(Integer id);

    void deleteBatch(List<Integer> ids);
}
