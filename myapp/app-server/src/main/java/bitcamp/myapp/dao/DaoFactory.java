package bitcamp.myapp.dao;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class DaoFactory {
  private SqlSession sqlSession;

  public DaoFactory(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public <T> T createObject(Class<T> daoType) throws Exception {

    return (T) Proxy.newProxyInstance(
        this.getClass().getClassLoader(),
        new Class[] {daoType},
        this::invoke
    );
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String namespace = proxy.getClass().getInterfaces()[0].getSimpleName();
    String sqlId = method.getName();
    String statement = String.format("%s.%s", namespace, sqlId);

    Object paramValue = null;
    if (args != null) {
      if (args.length == 1) {
        paramValue = args[0];
      } else {
        Parameter[] params = method.getParameters();
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
          Param anno = params[i].getAnnotation(Param.class);
          map.put(anno.value(), args[i]);
        }
        paramValue = map;
      }
    }

    Class<?> returnType = method.getReturnType();

    if (returnType == List.class) {
      return sqlSession.selectList(statement, paramValue);
    } else if (returnType == int.class || returnType == void.class || returnType == boolean.class) {
      int count = sqlSession.insert(statement, paramValue);
      if (returnType == boolean.class) {
        return count > 0;
      } else if (returnType == void.class) {
        return null;
      } else {
        return count;
      }
    } else {
      return sqlSession.selectOne(statement, paramValue);
    }
  }
}
