package board;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnector {	//connect object 생성, 세팅 (DAO에서 사용)
	public SqlSession sqlSession() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		}catch (IOException e){
			throw new IllegalArgumentException(e);
		}return new SqlSessionFactoryBuilder().build(inputStream).openSession();
	}
}
