package j2ee.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Restaurant_typesManageService{
	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void sentMessage(String message, HttpServletRequest req)
			throws ServletException,IOException;

public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException;

	/**
	 * 根据餐厅类型的ID，获得类型的名称---
	 * 数据库当中存储的类型之后：“美食”，...等等的几个，在餐厅注册/修改类型的时候，注意类型的ID和名称要对应
	 * */
	public String getTypeNameById(int id);

	/**
	 * 根据类型的名称，获得类型的ID---
	 * 数据库当中存储的类型之后：“美食”，...等等的几个，在餐厅注册/修改类型的时候，注意类型的ID和名称要对应
	 * */
	public int getIdByTypeName(String name);
}
