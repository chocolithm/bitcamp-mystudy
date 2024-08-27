package bitcamp.mybatis;

import org.apache.ibatis.session.*;

import java.sql.Connection;

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

    // 1) 현재 스레드 저장소 보관된 SqlSession 객체를 찾는다.
    SqlSession sqlSession = sqlSessionThreadLocal.get();

    // 2) 없으면,
    if (sqlSession == null) {
      //    - 오리지널 객체를 통해 새로 얻는다.
      sqlSession = original.openSession(autoCommit);

      //    - 다음에 이 객체를 사용하기 위해 현재 스레드 보관소에 저장한다.
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
