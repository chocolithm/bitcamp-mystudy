package bitcamp.mybatis;


import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory {

  // SqlSession 객체를 담을 스레드 전용 변수
  ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<>();
  private SqlSessionFactory original;

  public SqlSessionFactoryProxy(SqlSessionFactory original) {
    this.original = original;
  }

  @Override
  public SqlSession openSession() {
    return original.openSession();
  }

  @Override
  public SqlSession openSession(boolean autoCommit) {
    SqlSession sqlSession = sqlSessionThreadLocal.get();

    if (sqlSession == null) {
      sqlSession = original.openSession(autoCommit);

      sqlSessionThreadLocal.set(sqlSession);
    }

    return sqlSession;
  }

  @Override
  public SqlSession openSession(Connection connection) {
    return original.openSession(connection);
  }

  @Override
  public SqlSession openSession(TransactionIsolationLevel level) {
    return original.openSession(level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType) {
    return original.openSession(execType);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return original.openSession(execType, autoCommit);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return original.openSession(execType, level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return original.openSession(execType, connection);
  }

  @Override
  public Configuration getConfiguration() {
    return original.getConfiguration();
  }
}
