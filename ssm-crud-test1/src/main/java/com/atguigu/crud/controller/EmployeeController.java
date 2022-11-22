package com.atguigu.crud.controller;

import com.atguigu.crud.pojo.Employee;
import com.atguigu.crud.pojo.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author jojo
 * @create 2022-11-12 20:00
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

//    @RequestMapping("/{pageNum}")
//    public String getEmps(@PathVariable("pageNum") Integer pageNum, Model model){
//        PageInfo<Employee> page = employeeService.getAll(pageNum);
//        model.addAttribute("pageInfo",page);
//        return "list";
//    }

    /**
     * 查询员工列表和页码信息
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/emps",method = RequestMethod.POST)
    @ResponseBody
    public Msg getEmps(String pageNum){
        PageInfo<Employee> page = employeeService.getAll(Integer.parseInt(pageNum));
        return Msg.success().add("pageInfo",page);
    }

    /**
     * 添加员工
     * @param employee
     * @param result
     * @return
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        Map<String,Object> map = new HashMap<>();
        if (result.hasErrors()){
            for (FieldError fieldError : result.getFieldErrors()) {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id")Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }

    /**
     * 根据ID更新员工信息
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 单个批量删除 二合一
     * 批量删除：1-2-3
     * 单个删除：1
     * @param ids
     * @return
     */
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids")String ids){
        if (ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String [] str_ids = ids.split("-");
            for (String str : str_ids){
                del_ids.add(Integer.parseInt(str));
            }
            employeeService.deleteBatch(del_ids);
        }else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }

    /**
     * 检查用户名是否可用
     * @param empName
     * @return
     */
    @RequestMapping(value = "/checkuser",method = RequestMethod.POST)
    @ResponseBody
    public Msg checkuser(@RequestParam("empName") String empName){
//        先判断正则表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regx)){
            return Msg.fail().add("vamsg","用户名必须是6-16位数字和字母的组合或2-5位的中文");
        }
//        数据库用户名重复校验
        boolean flag = employeeService.checkEmpName(empName);
        if (flag){
            return Msg.success();
        }else {
            return Msg.fail().add("vamsg","用户名不可用");
        }
    }

}
