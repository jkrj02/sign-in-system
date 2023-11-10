package com.example.demo.repository;

import com.example.demo.entity.Leaveinfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//数据库类
@Repository
public interface LeaveRepository extends CrudRepository<Leaveinfo, Integer> {

}
