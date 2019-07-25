package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.User;
import util.DBUtils;

public class UserDAO {
	
	public void method() {
		String sql="";
		try(PreparedStatement ps=
				DBUtils.getConn().prepareStatement(sql)){
			
			ps.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 根据用户名查询用户信息的方法
	 * @param username
	 * @return true-用户存在 false-用户不存在
	 * @throws Exception 
	 */
	public boolean getUserByUsername(String username) throws Exception {
		
		String sql="select * from t_user where username=?";
		try(Connection conn=DBUtils.getConn()){
			try(PreparedStatement ps=
					conn.prepareStatement(sql)){
				ps.setString(1, username);
				// int i=10/0;
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					return true;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			// 如果出现异常：实际上不知道用户名是否存在
			// true   告知用户用户名已存在
			// false  告知用户用户名不存在
			// 两种结果都不准确
			throw new Exception("验证用户名出现异常",e);
		}

		return false;
	}
	
	
	/**
	 * 根据用户名和密码查用户信息的方法
	 * @param username 
	 * @param password
	 * @return true-用户存在  false-用户不存在
	 */
	public boolean getUserByUAP(String username,
			                  String password) {
		String sql="select * from t_user "
				+ "where username=? and password=?";
		try(Connection conn=DBUtils.getConn()) {
			try(PreparedStatement ps=
					conn.prepareStatement(sql)){
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	
	
	/**
	 * 基于用户的id删除用户的方法
	 * @param id 用户的id
	 * @return true-删除成功 false-删除失败
	 */
	public boolean delUser(int id) {
		String sql="delete from t_user where id=?";
		
		try (Connection conn=DBUtils.getConn()){
			try(PreparedStatement ps=
					conn.prepareStatement(sql)){
				ps.setInt(1, id);
				int i=ps.executeUpdate();
				if(i>0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
	/**
	 * 用来保存用户信息的方法
	 * @param user 封装了用户信息的对象
	 * @return true-添加成功 false-添加失败
	 */
	public boolean saveUser(User user) {
		String sql="insert into t_user values(null,?,?,?)";
		try (Connection conn=DBUtils.getConn()){
			try(PreparedStatement ps=
					conn.prepareStatement(sql)){
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				int i=ps.executeUpdate();
				if(i>0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * 查询所有用户信息的方法
	 * 
	 * @return 封装了用户信息的对象的集合
	 */
	public List<User> listAllUser(){
		// 使用JDBC查询所有用户数据
		String sql="select * from t_user";
		try (Connection conn=DBUtils.getConn()){
			try(PreparedStatement ps=
					conn.prepareStatement(sql)){
				ResultSet rs=ps.executeQuery();
				
				// 创建一个集合，来保存User对象
				List<User> list=new ArrayList<User>();
				
				// 遍历ResultSet
				while(rs.next()) {
					// 每次遍历过程中，创建一个User对象，
					User user=new User();
					
					// 保存数据库中的一行数据
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					
					// 将User对象存入list集合中
					list.add(user);
				}
				// 返回list集合
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	
	
	
}
