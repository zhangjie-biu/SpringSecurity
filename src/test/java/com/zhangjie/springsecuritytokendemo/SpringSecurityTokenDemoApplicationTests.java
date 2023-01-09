package com.zhangjie.springsecuritytokendemo;

import com.zhangjie.domain.User;
import com.zhangjie.mapper.MenuMapper;
import com.zhangjie.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityTokenDemoApplicationTests {

	@Autowired
	UserMapper userMapper;


	@Autowired
	MenuMapper menuMapper;


	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManagerBuilder authenticationManagerBuilder;

	@Test
	public void TestUserMapper(){
		List<User> list = userMapper.selectList(null);
		System.out.println(list);
	}


	@Test
	public void TestBCryptPassWordEncoder() {
		String encode1=passwordEncoder.encode("120197");
		String encode2=passwordEncoder.encode("zhangjie");
		System.out.println(encode1);
		System.out.println(encode2);
		System.out.println(passwordEncoder.matches("120197","$2a$10$uaVO98/whjVXuOn2vbxY6uXy5z0HDtMgUO2BU1mbH9KlxoS8yJEz2"));
	}


	@Test
	public void TestSelectPermsByUserId(){
		List<String> list =menuMapper.selectPermsByUserId(2L);
		System.out.println(list);
	}




}
