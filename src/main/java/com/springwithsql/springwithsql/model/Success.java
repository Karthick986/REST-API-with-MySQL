package com.springwithsql.springwithsql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import javax.persistence.Table;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor

@Entity
@Table(name = "response_table")

public class Success {

	@Id
	public int status_code;
	public String msg;
	
	public Success() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Success(int status_code, String msg) {
		super();
		this.status_code = status_code;
		this.msg = msg;
	}

	public int getStatusCode() {
		return status_code;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "Success [statusCode=" + status_code + ", msg=" + msg + "]";
	}
}
