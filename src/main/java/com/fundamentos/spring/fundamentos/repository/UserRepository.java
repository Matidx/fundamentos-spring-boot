package com.fundamentos.spring.fundamentos.repository;

import com.fundamentos.spring.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {


}
