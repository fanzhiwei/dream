package net.fanzhiwei;

import net.fanzhiwei.dao.mapper.mbg.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DreamApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {

	}
}
