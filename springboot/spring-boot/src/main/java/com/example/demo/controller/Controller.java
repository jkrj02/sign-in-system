package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//主程序
@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {
    private final StudentService studentService;
    private final ActivityService activityService;
    private final ClassroomService classroomService;
    private final LeaveService leaveService;
    private final ManagerService managerService;
    private final SignInService signInService;
    private final TeacherService teacherService;
    private final WeatherService weatherService;


    @PersistenceContext
    private EntityManager entityManager;



    @GetMapping("student")
    public Object stu()
    {
        return studentService.getAllStudents();
    }
    @GetMapping("student/add")
    public Object stuadd()
    {
        Student a=new Student();
        a.setCollege("计算机");
        a.setGender(false);
        a.setGrade("大一");
        a.setName("小明");
        a.setMajor("计算机");
        a.setStudentId(1120201198);
        return studentService.addStudent(a);
    }
    @GetMapping("student/id")
    public Object stuGetById(int id)
    {
        return studentService.findById(id);
    }




}
//
// @PutMapping("user/update")//用户更新
//    public boolean update(@RequestBody User user) {
//        return studentService.update(user) ? true : false;
//    }
//
//    @DeleteMapping("user/delete/{id}")//用户删除
//    public boolean deleteById(@PathVariable int id) {
//
//        return studentService.deleteById(id) ? true : false;
//    }
//
//    @PostMapping("course/add")//添加课程
//    public Object addCourse(@RequestBody Course tt) {
//        if (courseService.exists(tt)) {
//            return  new ResponseEntity <> ("课程已存在", HttpStatus.FORBIDDEN);
//        }
//        return courseService.insert(tt)!=null ?new ResponseEntity <> ("创建成功", HttpStatus.OK): new ResponseEntity <> ("创建失败", HttpStatus.FORBIDDEN);
//    }
// @GetMapping("test4")//复杂逻辑,多表查询 要用sql,不能自动生成
//    public Object test4(){
//        String sql ="SELECT * FROM my_like";
//        //String sql = "SELECT * FROM course INNER JOIN user ON course.teacher_name = user.user_name";
//       // Query query = entityManager.createNativeQuery(sql, myLike.class);//指定返回类型
//
//        return null;
//    }
