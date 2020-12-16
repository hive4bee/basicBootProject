package com.hive4bee.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_members")
@EqualsAndHashCode(of="uid")
public class Member {
	@Id
	private String uid;
	private String upw;
	private String uname;
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER) 
	@JoinColumn(name="member")
	private List<MemberRole> roles;
}
//Member엔티티와 MemberRole 엔티티를 동시에 저장하기 때문에 insert하려고 할 때 에러가 발생한다. 
//따라서 이에 대한 처리는 cascade설정을 추가해야 한다.
//또한 읽을 때 tbl_members와 tbl_member_roles 테이블을 둘 다 조회해야 하기 때문에
//즉시 로딩을 이용해서 조인을 하는 방식으로 처리해야 한다.

//@OneToMany의 mappedBy를 이용하면 컬럼명_식별키 형태로 DB에 입력되고
//@JoinColumn의 name을 이용하면 주어진 값대로 DB에 입력됨 