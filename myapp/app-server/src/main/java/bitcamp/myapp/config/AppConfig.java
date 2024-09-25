package bitcamp.myapp.config;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoFactory;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan("bitcamp.myapp")
@EnableWebMvc // WebMVC 관련기능 처리코드를 활성화
public class AppConfig {

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setPrefix("/WEB-INF/jsp/");
    vr.setSuffix(".jsp");
    return vr;
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

    return new SqlSessionFactoryProxy(sqlSessionFactory);
  }

  @Bean
  public DaoFactory daoFactory(SqlSessionFactory sqlSessionFactory) throws Exception {
    return new DaoFactory(sqlSessionFactory);
  }

  @Bean
  public UserDao userDao(DaoFactory daoFactory) throws Exception {
    return daoFactory.createObject(UserDao.class);
  }

  @Bean
  public BoardDao boardDao(DaoFactory daoFactory) throws Exception {
    return daoFactory.createObject(BoardDao.class);
  }

  @Bean
  public ProjectDao projectDao(DaoFactory daoFactory) throws Exception {
    return daoFactory.createObject(ProjectDao.class);
  }
}
