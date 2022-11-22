import com.atguigu.crud.mapper.DepartmentMapper;
import com.atguigu.crud.mapper.EmployeeMapper;
import com.atguigu.crud.pojo.Department;
import com.atguigu.crud.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.standard.expression.Each;

import java.util.UUID;

/**
 * @author jojo
 * @create 2022-11-12 17:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD(){
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("Spring.xml");
//        DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
//        System.out.println(departmentMapper);
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
//        employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@123.com", 1));
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@123.com",1));
        }
        System.out.println("ok");


    }

    @Test
    public void test01(){
        String [] a = new String[5];
        a[0]="1";
        a[1]="2";
        a[2]="3";
        a[3]="4";
        a[4]="5";
        for (String i :
                a) {
            System.out.println(i);
        }
    }
}
