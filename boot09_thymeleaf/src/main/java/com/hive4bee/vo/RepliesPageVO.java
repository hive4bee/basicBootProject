package com.hive4bee.vo;

import java.util.List;

import com.hive4bee.domain.WebReply;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RepliesPageVO {
	private int cnt;
	private List<WebReply> list;
}
