package book.designpattern.ch11.dynamicProxy;

import java.lang.reflect.Proxy;

public class PersonImpl implements Person {

  String name;
  String gender;
  String interests;
  int rating;
  int ratingCount = 0;

  @Override
  public String getName() {
    return name;
  }
  @Override
  public void setName(String name) {
    this.name = name;
  }
  @Override
  public String getGender() {
    return gender;
  }
  @Override
  public void setGender(String gender) {
    this.gender = gender;
  }
  @Override
  public String getInterests() {
    return interests;
  }
  @Override
  public void setInterests(String interests) {
    this.interests = interests;
  }
  @Override
  public int getGeekRating() {
    if (ratingCount == 0) {
      return 0;
    }
    return rating / ratingCount;
  }
  @Override
  public void setGeekRating(int rating) {
    this.rating += rating;
    ratingCount++;
  }

  public Person getOwnerProxy(Person person) {
    return (Person) Proxy.newProxyInstance(
        person.getClass().getClassLoader(),
        person.getClass().getInterfaces(),
        new OwnerInvocationHandler(person));
  }

  public Person getNonOwnerProxy(Person person) {
    return (Person) Proxy.newProxyInstance(
        person.getClass().getClassLoader(),
        person.getClass().getInterfaces(),
        new NonOwnerInvocationHandler(person));
  }
}
