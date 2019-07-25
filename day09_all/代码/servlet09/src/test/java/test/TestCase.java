package test;

import java.util.List;

import org.junit.Test;

import dao.UserDAO;
import domain.User;

public class TestCase {

	UserDAO dao=new UserDAO();
	
	@Test
	public void testGetUserByUsername() {
		try {
			boolean flag=dao.getUserByUsername("张123");
			if(flag) {
				System.out.println("用户名已经存在");
			}else {
				System.out.println("用户名不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testGetUserByUAP() {
		boolean flag=dao.getUserByUAP("张无忌", "1");
		if(flag) {
			System.out.println("登录成功");
		}else {
			System.out.println("登录失败");
		}
	}
	
	@Test
	public void testDelUser() {
		boolean flag=dao.delUser(1);
		if(flag) {
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
	}
	
	@Test
	public void testSaveUser() {
		User user=new User(-1, "Tom", "123", "1@qq.com");
		boolean flag=dao.saveUser(user);
		if(flag) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
	}
	
	@Test
	public void testListAllUser() {
		List<User> list=dao.listAllUser();
		for(User user:list) {
			System.out.println(user);
		}
	}

}





