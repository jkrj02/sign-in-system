package com.example.demo.controller;

import com.example.demo.entity.*;


import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//主程序
@CrossOrigin
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
    {System.out.println("Hello, World!");
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
    {
        Leaveinfo a=new Leaveinfo();
        a.setActivityId(121);
        a.setEndTime("2020/11/11");
        a.setStudentId(112020);
        a.setStartTime("2020/11/10");
        a.setType(true);
        a.setId(55);
        return leaveService.addLeave(a);
    }
    @GetMapping("student/add")
    public Object stuadd() {//添加学生
        int i = 1120200001;
        while (i <= 1120200030)
        {Student a = new Student();
        a.setCollege("计算机");
        a.setGender(false);
        a.setGrade("大一");
        a.setName("小明");
        a.setMajor("计算机");
        a.setStudentId(i);
        i++;
        studentService.addStudent(a);
        }
        return  true;
    }
    @GetMapping("signin/add1/{id}")//为活动添加签到表
    public Object signinbeginadd(@PathVariable int id) {
        int i = 1120200001;
        if(signinService.find(id,1120200001)==false)
            return false;

        while (i <= 1120200030) {

            Signin a = new Signin();
            a.setId(0);
            a.setActivityId(id);
            a.setUserId(i);
            a.setSignin("待签到");
            i++;
            signinService.addSignin(a);
        }
        return  true;
    }
    @GetMapping("signin/add2/{id}")//为活动添加签到表
    public Object signinadd(@PathVariable int id) {
        int i = 1120200001;
        Random random=new Random(10);
        while (i <= 1120200030) {
            Signin a = new Signin();
            a.setId(0);
            a.setActivityId(id);
            a.setUserId(i);
            i++;
           int t=random.nextInt(10);
             if(t<5)
              a.setSignin("已签到");
             else if(t>8)
                 a.setSignin("未签到");
             else
                 a.setSignin("迟到");
            signinService.addSignin(a);
        }
        return  true;
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

    @Transactional
    @PostMapping ("signin")
    public String siggin(@RequestParam int userId, int classroomId, String signintime)
    {   System.out.println("Hello, World!");
        //String returninfo = "noactivity_in_this_room_at_this_time";

        String sql = "SELECT * FROM activity where classroom_id =";
        sql += String.valueOf(classroomId);

        Query query = entityManager.createNativeQuery(sql, Activity.class);//指定返回类型
        List<Activity> temp= query.getResultList();
        int sigsignin = 0;
        String[] results = signintime.split("/|:");
        long acttime = 0;
        for(String sig:results)
        {
            acttime = acttime*100+Integer.parseInt(sig);
        }
        System.out.println(String.valueOf(acttime));
        int activityid=-1;
        if(temp.isEmpty())
            return "parameter error";
        else {
            for (Activity ppp : temp) {

                String starttime =ppp.getTime() ;
                String[] results1 = starttime.split("/|:");
                long actstarttime = 0;
                for(String sig:results1)
                {
                    actstarttime = actstarttime*100+Integer.parseInt(sig);
                }
                String endtime = ppp.getEndTime();
                String[] results2 = endtime.split("/|:");
                long actendtime = 0;
                for(String sig:results2)
                {
                    actendtime = actendtime*100+Integer.parseInt(sig);
                }
                System.out.println(String.valueOf(actstarttime)+String.valueOf(actendtime));
                if(actstarttime<acttime && acttime<actendtime )
                {
                    activityid=ppp.getActivityId();
                    sigsignin = 2;//迟到
                }
                else if( (actstarttime-30)<=acttime && acttime<=actstarttime )
                {
                    activityid=ppp.getActivityId();
                    sigsignin = 1;//签到
                }
            }
        }
        if(activityid ==-1)
            return  "parameter error";

        String sql2 = "SELECT * FROM signin where activity_id = ";
        sql2 += String.valueOf(activityid);
        sql2 += " and user_id = ";
        sql2 += String.valueOf(userId);

        Query query2 = entityManager.createNativeQuery(sql2, Signin.class);//指定返回类型
        List<Signin> temp2= query2.getResultList();
        if(temp2.isEmpty())
            return "parameter error";
        else if (  sigsignin == 1)
        {
            String sql3= "UPDATE signin SET signin = '已签到' where activity_id = ";
            sql3 += String.valueOf(activityid);
            sql3 += " and user_id = ";
            sql3 += String.valueOf(userId);
            entityManager.createNativeQuery(sql3).executeUpdate();
            return "successfully sign";
        }
        else
        {
            String sql3= "UPDATE signin SET signin = '迟到' where activity_id = ";
            sql3 += String.valueOf(activityid);
            sql3 += " and user_id = ";
            sql3 += String.valueOf(userId);
            entityManager.createNativeQuery(sql3).executeUpdate();
            return "late";
        }
    }

    @GetMapping ("checkatt")
    public Object checkatt(@RequestParam int teacherId, String start_time, String end_time)
    {
        System.out.println("Hello, World!");

        String[] results0 = start_time.split("/|:");
        long stime = 0;
        for(String sig:results0)
        {
            stime = stime*100+Integer.parseInt(sig);
        }
        String[] results1 = end_time.split("/|:");
        long etime = 0;
        for(String sig:results1)
        {
            etime = etime*100+Integer.parseInt(sig);
        }

        String sql = "SELECT * FROM activity where organizer_id =";
        sql += String.valueOf(teacherId);
        sql +=" and type = '上课' and approved = '已通过'";
        Query query = entityManager.createNativeQuery(sql, Activity.class);//指定返回类型
        List<Activity> temp= query.getResultList();

        class classandrate
        {
            public String coursename;
            public double attendence;
        }
        List<classandrate> classandrates = new ArrayList<classandrate>();

        for (Activity ppp : temp)
        {
            long tstime = 0 ;
            long tetime = 0 ;
            String[] results2 = ppp.getTime().split("/|:");
            for(String sig:results2)
            {
                tstime = tstime*100+Integer.parseInt(sig);
            }
            String[] results3 = ppp.getEndTime().split("/|:");
            for(String sig:results3)
            {
                tetime = tetime*100+Integer.parseInt(sig);
            }
            if(tstime >= stime && tetime <= etime)//
            {
                classandrate temp2 = new classandrate();
                temp2.coursename = ppp.getName();
                int total = 0;
                int come = 0;
                System.out.println("Hello, World!!!");
                String sql1 = "SELECT * FROM signin where activity_id =";
                sql1 += String.valueOf(ppp.getActivityId());
                Query query1 = entityManager.createNativeQuery(sql1, Signin.class);//指定返回类型
                List<Signin> temp3 = query1.getResultList();
                for (Signin ptp : temp3) {
                    total += 1;
                    if (ptp.getSignin().equals("已签到")) come += 1;
                }
                temp2.attendence = (double)come / (double)total;
                classandrates.add(temp2);
                System.out.println(temp2.coursename+String.valueOf(temp2.attendence )+String.valueOf(come)+String.valueOf(total));
            }
        }
        System.out.println(classandrates);
        return classandrates;
    }

    @GetMapping ("/myattendence/{userId}")
    public Object myattendence(@PathVariable int userId){
        int total = 0;
        int come = 0;
        int lat = 0;
        int abs = 0;
        double rate;
        //String r;
        class returnin
        {
            public double attendencerate;
            public double laterate;
            public double absencerate;
        }
        returnin rrr = new returnin();
        String sql1 = "SELECT * FROM signin where user_id = ";
        sql1 += String.valueOf(userId);
        Query query1 = entityManager.createNativeQuery(sql1, Signin.class);//指定返回类型
        List<Signin> temp3 = query1.getResultList();
        if(temp3.isEmpty())
        {
            rrr.attendencerate = 0;
            rrr.laterate = 0;
            rrr.absencerate = 0;
        }
        else
        {
            for (Signin ptp : temp3) {
                if (!ptp.getSignin().equals("待签到")) total += 1;
                if (ptp.getSignin().equals("已签到")) come += 1;
                if (ptp.getSignin().equals("迟到")) lat += 1;
                if (ptp.getSignin().equals("未签到")) abs += 1;
            }
            rrr.attendencerate= (double)come / (double)total;
            rrr.laterate = (double)lat / (double)total;
            rrr.absencerate = (double)abs / (double)total;
            //r = String.valueOf(rate);
        }
        return rrr;
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
//    }//
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
