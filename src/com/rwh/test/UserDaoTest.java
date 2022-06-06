package com.rwh.test;

import com.rwh.dao.UserDao;
import com.rwh.pojo.User;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
* UserDao Tester. 
* 
* @author <Authors name> 
* @since <pre>4�� 18, 2022</pre> 
* @version 1.0 
*/ 
public class UserDaoTest { 
    UserDao userDao = new UserDao();
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: queryUsername(String username) 
* 
*/ 
@Test
public void testQueryUsername() throws Exception {


}

@Test
public void testQueryUser() throws Exception {
    User user = userDao.queryUser("小红");
    System.out.println(user.toString());
}
    /**
* 
* Method: queryPhone(String username) 
* 
*/ 
@Test
public void testQueryPhone() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: queryHeadphone(String username) 
* 
*/ 
@Test
public void testQueryHeadphone() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: queryVipdate(String username) 
* 
*/ 
@Test
public void testQueryVipdate() throws Exception { 
//TODO: Test goes here... 
} 



/** 
* 
* Method: updateUsername(String username) 
* 
*/ 
@Test
public void testUpdateUsername() throws Exception { 
    userDao.updateUsername("rwh888","rwh999");
} 

/** 
* 
* Method: updatePassword(String username, String password) 
* 
*/ 
@Test
public void testUpdatePassword() throws Exception {
    int a = userDao.updatePassword("rwh999","777777");
    System.out.println(a);
} 

/** 
* 
* Method: updatePhone(String username, String phone) 
* 
*/ 
@Test
public void testUpdatePhone() throws Exception {
    int a = userDao.updatePhone("rwh999","1234567890");
    System.out.println(a);
} 

/** 
* 
* Method: updateHeadphone(String username, String headphone) 
* 
*/ 
@Test
public void testqueryid() throws Exception {

} 

/** 
* 
* Method: updateVipdate(String username, String vipdate) 
* 
*/ 
@Test
public void testUpdateVipdate() throws Exception {
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);
    System.out.println(strDate);
    int a = userDao.updateVipdate("rwh999",strDate);
    System.out.println(a);
} 



/** 
* 
* Method: saveUser(User user) 
* 
*/ 
@Test
public void testSaveUser() throws Exception { 
    User user = new User(null,"rwh2221","654321",
            "19854711918","img/temp/1.png",null);
    int a=userDao.saveUser(user);
    System.out.println(a);
} 


} 
