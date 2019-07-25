package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static BasicDataSource  dataSource;
	static {
		//读取配置文件里面的数据
				Properties prop = new Properties();
				//通过反射得到文件输入流
				InputStream ips = 
						DBUtils.class.getClassLoader()
						.getResourceAsStream("jdbc.properties");
				try {
					prop.load(ips);
					//读取数据
					String driver = prop.getProperty("driver");
					String url = prop.getProperty("url");
					String username = prop.getProperty("username");
					String password = prop.getProperty("password");
					//获取连接
					dataSource = 
								new BasicDataSource();
					//设置数据库连接信息
					dataSource.setDriverClassName(driver);
					dataSource.setUrl(url);
					dataSource.setUsername(username);
					dataSource.setPassword(password);
					//设置初始连接数量
					dataSource.setInitialSize(3);
					//设置最大连接数量
					dataSource.setMaxActive(5);
				} catch (IOException e) {
					e.printStackTrace();
				}//把文件和对象建立关系
			
	}
	public static Connection getConn() throws Exception {
		
		//获取连接对象
		Connection conn = dataSource.getConnection();
		return conn;
	}
}




