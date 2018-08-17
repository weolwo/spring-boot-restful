package com.spring.restful.controller;

import com.spring.restful.dao.DepartmentDao;
import com.spring.restful.dao.EmployeeDao;
import com.spring.restful.entities.Department;
import com.spring.restful.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 员工控制层类
 */
@Controller
public class EmployeeContoller {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    //查询所有员工
    @GetMapping("/emps")
    public String queryEmpList(Model model) {

        Collection<Employee> employees = employeeDao.getAll();

        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //到员工添加页
    @GetMapping("/emp")
    public String empAddPage(Model model) {
        //在回到添加页面时,需要携带部门名称信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        //来到员工列表页面

        //System.out.println("保存的员工信息：" + employee);
        //保存员工
        employeeDao.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面,回显被修改的员工数据
    @GetMapping("/emp/{id}")
    public String toEditEmpPage(@PathVariable("id") Integer id, Model model) {
        //根据id查询员工
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        //回显部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //更新员工,需要提交员工id
    @PutMapping("emp")
    public String updateEmpployee(Employee employee) {
        //System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //删除员工,需要提交员工id
    @DeleteMapping("/emp/{id}")
    public String updateEmpployee(@PathVariable("id") Integer id) {

        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
