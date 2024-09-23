package bitcamp.myapp.config;

import bitcamp.myapp.annotation.Bean;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoFactory;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.DefaultBoardService;
import bitcamp.myapp.service.DefaultProjectService;
import bitcamp.myapp.service.DefaultUserService;
import bitcamp.myapp.service.ProjectService;
import bitcamp.myapp.service.UserService;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AppConfig {
  @Bean
  public SqlSessionFactory createSqlSessionFactory() throws Exception {
    InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

    return new SqlSessionFactoryProxy(sqlSessionFactory);
  }

  @Bean
  public DaoFactory createDaoFactory(SqlSessionFactory sqlSessionFactory) throws Exception {
    return new DaoFactory(sqlSessionFactory);
  }

  @Bean
  public UserDao createUserDao(DaoFactory daoFactory) throws Exception {
    return daoFactory.createObject(UserDao.class);
  }

  @Bean
  public BoardDao createBoardDao(DaoFactory daoFactory) throws Exception {
    return daoFactory.createObject(BoardDao.class);
  }

  @Bean
  public ProjectDao createProjectDao(DaoFactory daoFactory) throws Exception {
    return daoFactory.createObject(ProjectDao.class);
  }

  @Bean
  public UserService createUserService(UserDao userDao, SqlSessionFactory sqlSessionFactory) throws Exception {
    return new DefaultUserService(userDao, sqlSessionFactory);
  }

  @Bean
  public BoardService createBoardService(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) throws Exception {
    return new DefaultBoardService(boardDao, sqlSessionFactory);
  }

  @Bean
  public ProjectService createProjectService(ProjectDao projectDao, SqlSessionFactory sqlSessionFactory)
      throws Exception {
    return new DefaultProjectService(projectDao, sqlSessionFactory);
  }
}
