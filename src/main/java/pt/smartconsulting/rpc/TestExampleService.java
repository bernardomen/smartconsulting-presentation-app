package pt.smartconsulting.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pt.smartconsulting.model.User;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

@Service
public class TestExampleService {

	@Autowired(required = false)
	HttpSession session;

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ExtDirectMethod
	public String getSuperString() {
		return "SUPER-STRING";
	}

	@ExtDirectMethod
	public ArrayList<User> getUsersFromDB() {

		ArrayList<User> users = new ArrayList<User>();

		String sql = "select * from user order by firstname, lastname asc limit 200";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			User user = new User();
			user.setUserId((String) (row.get("userid")));
			user.setFirstName((String) (row.get("firstname")));
			user.setLastName((String) (row.get("lastname")));
			user.setPhone((String) (row.get("phone")));
			users.add(user);

		}

		return users;
	}

}
