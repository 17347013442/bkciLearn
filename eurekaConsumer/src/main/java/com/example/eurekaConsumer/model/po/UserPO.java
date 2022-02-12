package com.example.eurekaConsumer.model.po;

import com.example.eurekaConsumer.service.ValidType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/** @Author Mu_Mu @Description @Date 2022/2/20 */
public class UserPO {
  // 配置了groups只有controller层group相同时才会触发
  @NotNull(groups = {ValidType.Add.class})
  private Integer id;
  // 没配置groups，controller层设置了group该规则不会被触发
  @Max(value = 150, message = "年龄不能超过150")
  @Min(value = 1, message = "年龄最小不能低于1")
  private Integer age;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "UserPO{" + "id=" + id + ", age=" + age + '}';
  }
}
