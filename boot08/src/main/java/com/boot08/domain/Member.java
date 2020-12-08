package com.boot08.domain;

import java.time.LocalDateTime;
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
@ToString(exclude="roles")
@Entity
@Table(name="tbl_members")
@EqualsAndHashCode(of="uid")
public class Member {
	@Id
	private String uid;
	private String upw;
	private String uname;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	@UpdateTimestamp
	private LocalDateTime updatedate;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="member")
	private List<MemberRole> roles;
}