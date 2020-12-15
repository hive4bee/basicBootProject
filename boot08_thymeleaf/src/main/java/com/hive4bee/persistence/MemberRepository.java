package com.hive4bee.persistence;

import org.springframework.data.repository.CrudRepository;

import com.hive4bee.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
