package com.jdoysu.jdoysu20.repository;

import com.jdoysu.jdoysu20.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Long>{

}
