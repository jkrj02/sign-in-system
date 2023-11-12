package com.example.demo.controller;

import com.example.demo.entity.*;


import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
    private final SigninService signinService;
    private final TeacherService teacherService;
    private final WeatherService weatherService;


    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("login/{id}")//显示个人信息
    public Object get(@PathVariable int id){
        return studentService.findById(id);
    }

    @PostMapping("activity/add")//添加活动  同时添加待签到
    public Object addActivity(@RequestBody Activity tt){
        Activity activity=activityService.addActivity(tt);
        Signin a=new Signin();
        a.setId(0);
        a.setActivityId(activity.getActivityId());
        a.setUserId(activity.getOrganizerId());
        a.setSignin("待签到");
        signinService.addSignin(a);
        return activity;
    }

    @GetMapping("activity/get/{id}")//查看日程
    public Object getActivity(@PathVariable int id){

        //String sql = "SELECT * FROM activity INNER JOIN signin ON activity.activityId = signin.activityId where signin.userId ="+id;
        String sql ="SELECT DISTINCT * FROM signin INNER JOIN activity ON  signin.activity_id= activity.activity_id where user_id ="+id;
        Query query = entityManager.createNativeQuery(sql, returnActivity.class);//指定返回类型
        List<returnActivity> re = query.getResultList();

        return re;
    }
    @PostMapping("sign/add/{userId}/{activityId}")//添加签到
    public Object addSignIn(@PathVariable int userId,int activityId){

        return null;
    }

    @GetMapping("activity/my")//出勤率
    public Object myActivity(int id ){
        return null;
    }



    @GetMapping("student")
    public Object stu()
    {
        return studentService.getAllStudents();
    }
    @GetMapping("activity")
    public Object act()
    {
        return activityService.getAllActivitys();
    }
    @GetMapping("classroom")
    public Object cla()
    {
        return classroomService.getAllClassrooms();
    }
    @GetMapping("leave")
    public Object lea()
    {
        return leaveService.getAllLeaves();
    }
    @GetMapping("manager")
    public Object man()
    {
        return managerService.getAllManagers();
    }
    @GetMapping("signin")
    public Object sig()
    {
        return signinService.getAllSignIns();
    }
    @GetMapping("teacher")
    public Object tea()
    {
        return teacherService.getAllTeachers();
    }
    @GetMapping("weather")
    public Object wea()
    {
        return weatherService.getAllWeathers();
    }
    @GetMapping("leave/add")
    public Object leaveAdd()
    {   Leaveinfo a=new Leaveinfo();
        a.setActivityId(121);
        a.setEndTime("2020/11/11");
        a.setStudentId(112020);
        a.setStartTime("2020/11/10");
        a.setType(true);
        a.setId(55);
        return leaveService.addLeave(a);
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
    @PostMapping("student/test")
    public Object stutest(@RequestBody Student a)
    {
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
