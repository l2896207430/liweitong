package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dao.StudentDao;
import daoimpl.StudentDaoImpl;
import entity.ListTotalsSpPageCounts;
import entity.ResultMsg;
import entity.Student;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opType = request.getParameter("opType");
		switch (opType) {
		case "checkUsername":
			checkUsername(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
		case "add":
			add(request, response);
			break;
		case "update":
			update(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "deleteMore":
			deleteMore(request, response);
			break;
		case "queryById":
			queryById(request, response);
			break;
		case "queryByPage":
			queryByPage(request, response);
			break;
		default:
			break;
		}
		
	}

	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		StudentDao dao = new StudentDaoImpl();
		boolean flag = dao.checkUsername(username);
		ResultMsg msg = new ResultMsg();
		if (flag) {
			msg.setCode(0);
			msg.setMsg("用户名已存在,请重新输入");
		}else {
			msg.setCode(1);
			msg.setMsg("该用户名可用");
		}
		String json = JSON.toJSONString(msg);
		out.write(json);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		StudentDao dao = new StudentDaoImpl();
		Student s = dao.login(username,password);
		if (s!=null) {
			//登录成功
			System.out.println("登录成功");
			//保存用户登录信息到会话中
		    request.getSession().setAttribute("s", s);
			//分页查询所有记录
		    request.getRequestDispatcher("list.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "用户名或密码错误!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//清除保存在会话中的所有数据
		request.getSession().invalidate();
		//跳转到login登录页面
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String brithday = request.getParameter("brithday");
		//封装参数到对象中
		Student s = new Student();
		//把字符串转换成int类型
		s.setName(name);
		s.setAge(Integer.parseInt(age));
		s.setSex(Integer.parseInt(sex));
		s.setUsername(username);
		s.setPassword(password);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//把字符串转换成日期类型
			s.setBrithday(sdf.parse(brithday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//调用dao保存数据
		StudentDao dao = new StudentDaoImpl();
		dao.addForm(s);
		//页面跳转
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String brithday = request.getParameter("brithday");
		//封装参数到对象中
		Student s = new Student();
		//把字符串转换成int类型
		s.setId(Integer.parseInt(id));
		s.setName(name);
		s.setAge(Integer.parseInt(age));
		s.setSex(Integer.parseInt(sex));
		s.setUsername(username);
		s.setPassword(password);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//把字符串转换成日期类型
			s.setBrithday(sdf.parse(brithday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//调用dao保存数据
		StudentDao dao = new StudentDaoImpl();
		dao.Update(s);
		queryByPage(request, response);
		out.flush();
		out.close();
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		//跳到dao更新数据
		StudentDao dao = new StudentDaoImpl();
		ResultMsg msg = new ResultMsg();
		dao.deleteByid(Integer.parseInt(id));
		msg.setCode(1);
		msg.setMsg("删除成功");
		String json = JSON.toJSONString(msg);
		out.write(json);
		out.flush();
		out.close();
	}

	protected void deleteMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String ids = request.getParameter("ids");
		StudentDao dao = new StudentDaoImpl();
		ResultMsg msg = new ResultMsg();
		dao.deleteMore(ids);
		msg.setCode(1);
		msg.setMsg("删除成功");
		String json=JSON.toJSONString(msg);
		out.write(json);
		out.flush();
		out.close();
	}
	protected void queryById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		StudentDao dao = new StudentDaoImpl();
		Student s = dao.queryById(Integer.parseInt(id));
		request.setAttribute("student", s);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/updateForm.jsp").forward(request, response);
	}
	protected void queryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		     PrintWriter out = response.getWriter();
		        //获取页面上传递过来的页码
				String currentPage = request.getParameter("currentPage");
				StudentDao dao = new StudentDaoImpl();
				//当前页
				int sp = 1;
				//总记录数
				int totals = dao.getTotal();
				//每页显示记录数
				int pageSize = 20;
				//总页数
				int pageCounts = 0;
				//用异常捕获，避免页码传递错误页码
				try {
					sp = Integer.parseInt(currentPage);
				}catch(Exception e) {
					sp = 1;
				}
				pageCounts = totals/pageSize;
				if(totals%pageSize!=0) {
					pageCounts++;
				}
				if(sp>pageCounts) {
					sp = pageCounts;
				}
				if(sp<1) {
					sp = 1;
				}
				List<Student> list = dao.queryByPage(sp, pageSize);
				JSONObject jo = new JSONObject();
				jo.put("list", list);
				jo.put("totals", totals);
				jo.put("pageCounts", pageCounts);
				jo.put("sp", sp);
				response.setContentType("text/html;charset=utf-8");
				String json = JSONObject.toJSONString(jo);
				System.out.println(json);
				out.write(json);
				out.flush();
				out.close();
	}
	
}