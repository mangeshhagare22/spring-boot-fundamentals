package com.mangesh.lms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mangesh.lms.model.Member;


public interface MemberRepository extends JpaRepository<Member,Long> {

}
