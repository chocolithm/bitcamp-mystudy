package bitcamp.bitbatis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SqlSession {

  Connection con;

  public SqlSession(Connection con) {
    this.con = con;
  }

  public int insert(String sql, Object... values) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(sql)) {

      int inparameterIndex = 1;
      for (Object value : values) {
        stmt.setString(inparameterIndex++, value.toString());
      }

      return stmt.executeUpdate();
    }
  }

  public int insertReturningKey(String sql, Object... values) throws Exception {

    try (PreparedStatement stmt = con.prepareStatement(sql,
        PreparedStatement.RETURN_GENERATED_KEYS)) {

      int inparameterIndex = 1;
      for (Object value : values) {
        stmt.setString(inparameterIndex++, value.toString());
      }

      int count = stmt.executeUpdate();

      ResultSet keyRs = stmt.getGeneratedKeys();
      keyRs.next();
      return keyRs.getInt(1);
    }
  }

  public int update(String sql, Object... values) throws Exception {
    return insert(sql, values);
  }

  public int delete(String sql, Object... values) throws Exception {
    return insert(sql, values);
  }

  public <T> List<T> selectList(String sql, Class<T> type, Object... values) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(sql)) {

      int inparameterIndex = 1;
      for (Object value : values) {
        stmt.setString(inparameterIndex++, value.toString());
      }

      try (ResultSet rs = stmt.executeQuery()) {

        List<String> columnNames = getColumnNames(rs);

        java.util.List<T> list = new ArrayList<>();
        while (rs.next()) {
          T obj = createObject(type);
          for (String columnName : columnNames) {
            setPropertyValue(obj, rs, columnName);
          }
          list.add(obj);
        }
        return list;
      }
    }
  }

  public <T> T selectOne(String sql, Class<T> type, Object... values) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(sql)) {

      int inparameterIndex = 1;
      for (Object value : values) {
        stmt.setString(inparameterIndex++, value.toString());
      }

      try (ResultSet rs = stmt.executeQuery()) {

        List<String> columnNames = getColumnNames(rs);

        if (rs.next()) {
          T obj = createObject(type);
          for (String columnName : columnNames) {
            setPropertyValue(obj, rs, columnName);
          }
          return obj;
        }
        return null;
      }
    }
  }

  private List<String> getColumnNames(ResultSet rs) throws Exception {
    ResultSetMetaData metaData = rs.getMetaData();
    List<String> names = new ArrayList<>();
    for (int i = 1; i <= metaData.getColumnCount(); i++) {
      names.add(metaData.getColumnLabel(i));
    }
    return names;
  }

  private <T> T createObject(Class<T> type) throws Exception {
    Constructor<T> constructor = type.getConstructor();
    return constructor.newInstance();
  }

  private void setPropertyValue(Object obj, ResultSet rs, String columnName)
      throws Exception {
    String[] names = columnName.split("_");

    if (names.length == 1) { // ex) name
      callSetter(obj, rs, names[0]);
    } else { // ex) writer_name
      Method getter = findMethod(obj.getClass(), toGetterName(names[0])); // ex) User getWriter() {}
      Object embeddedObject = getter.invoke(obj);

      if (embeddedObject == null) {
        embeddedObject = getter.getReturnType().getConstructor()
            .newInstance(); // ex) embeddedObject = new User();
        Method setter = findMethod(obj.getClass(), toSetterName(names[0]));
        setter.invoke(obj, embeddedObject); // ex) board.setWriter(embeddedObject);
      }

      callSetter(embeddedObject, names[1], rs, columnName); // ex) getWriter().setName()
    }
  }

  private void callSetter(Object obj, ResultSet rs, String columnName) throws Exception {
    Method setter = findMethod(obj.getClass(), toSetterName(columnName));
    if (setter == null) {
      return;
    }
    Class<?> parameterType = setter.getParameterTypes()[0];
    setter.invoke(obj, getColumnValue(rs, columnName, parameterType));
  }

  private void callSetter(Object obj, String propertyName, ResultSet rs, String columnName)
      throws Exception {
    Method setter = findMethod(obj.getClass(), toSetterName(propertyName));
    if (setter == null) {
      return;
    }
    Class<?> parameterType = setter.getParameterTypes()[0];
    setter.invoke(obj, getColumnValue(rs, columnName, parameterType));
  }

  private String toGetterName(String name) {
    return "get" + Character.toUpperCase(name.charAt(0)) +
        name.substring(1);
  }

  private String toSetterName(String name) {
    return "set" + Character.toUpperCase(name.charAt(0)) +
        name.substring(1);
  }

  private <T> Method findMethod(Class<T> type, String name) {
    Method[] methods = type.getMethods();
    for (Method m : methods) {
      if (m.getName().equals(name)) {
        return m;
      }
    }
    return null;
  }

  private <T> Object getColumnValue(ResultSet rs, String columnName, Class<T> type)
      throws Exception {
    if (type == int.class) {
      return rs.getInt(columnName);
    } else if (type == String.class) {
      return rs.getString(columnName);
    } else if (type == java.util.Date.class || type == java.sql.Date.class) {
      return rs.getDate(columnName);
    } else {
      return null;
    }
  }


}
