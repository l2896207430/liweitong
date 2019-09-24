package daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StudentDao;
import entity.Student;
import utl.DBUtil;

public class StudentDaoImpl implements StudentDao{

	@Override
	public Student login(String username,String password) {
		Student s = null;
		Connection con = DBUtil.getConnection();
		String sql = "select * from student where username=? and password=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setSex(rs.getInt("sex"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setBrithday(rs.getDate("brithday"));
				s.setCreaTime(rs.getTimestamp("createTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return s;
	}

	@Override
	public void addForm(Student s) {
		Connection con = DBUtil.getConnection();
		String sql = "insert into student(id,name,age,sex,username,password,brithday,createTime)"
				+ "values(stu_seq1.nextval,?,?,?,?,?,?,sysdate)";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setInt(2, s.getSex());
			pst.setInt(3, s.getAge());
			pst.setString(4, s.getUsername());
			pst.setString(5, s.getPassword());
			pst.setDate(6, new Date(s.getBrithday().getTime()));
			System.out.println("注册成功！");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
	
	}

	@Override
	public List<Student> queryByPage(int currentPage, int pageSize) {
		Student s = null;
		List<Student> list = new ArrayList<Student>();
		Connection con = DBUtil.getConnection();
		String sql = "select ss.* from (select s.*,rownum r from (select * from student order by id) s where rownum<=?) ss where ss.r>?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, currentPage*pageSize);
			pst.setInt(2, (currentPage-1)*pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setSex(rs.getInt("sex"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setBrithday(rs.getDate("brithday"));
				s.setCreaTime(rs.getTimestamp("createTime"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}

		return list;
	}

	@Override
	public int getTotal() {
		Connection con = DBUtil.getConnection();
		String sql = "select count(*) from student ";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		
		return 0;
	}

	@Override
	public void deleteByid(int id) {
		
		Connection con = DBUtil.getConnection();
		String sql = "delete from student where id = ?";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public Student queryById(int id) {
		Student s = null;
		Connection con = DBUtil.getConnection();
		String sql = "select * from student where id = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setSex(rs.getInt("sex"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setBrithday(rs.getDate("brithday"));
				s.setCreaTime(rs.getTimestamp("createTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		return s;
	}

	@Override
	public void Update(Student s) {
		Connection con = DBUtil.getConnection();
		String sql = "update student set name=?,sex=?,age=?,username=?,password=?,brithday=? where id=?";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setInt(2, s.getSex());
			pst.setInt(3, s.getAge());
			pst.setString(4, s.getUsername());
			pst.setString(5, s.getPassword());
			pst.setDate(6, new Date(s.getBrithday().getTime()));
			pst.setInt(7, s.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtil.getConnection();
		String sql = "delete from student where id in("+ids+") ";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}

	@Override
	public void addMore(List<Student> list) {
		Connection con = DBUtil.getConnection();
		String sql = "insert into student(id,name,age,sex,username,password,brithday,createTime)"
				+ "values(stu_seq1.nextval,?,?,?,?,?,?,sysdate)";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				Student s = list.get(i);
				pst.setString(1, s.getName());
				pst.setInt(2, s.getAge());
				pst.setInt(3, s.getSex());
				pst.setString(4, s.getUsername());
				pst.setString(5, s.getPassword());
				pst.setDate(6, new Date(s.getBrithday().getTime()));
				pst.addBatch();
				if(i%100==0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pst, null);
		}
	
	}

	@Override
	public List<Student> queryAll() {
		List<Student> list = new ArrayList<Student>();
		Connection con = DBUtil.getConnection();
		String sql = "select * from student order by id ";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setSex(rs.getInt("sex"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setBrithday(rs.getDate("brithday"));
				s.setCreaTime(rs.getTimestamp("createTime"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public boolean checkUsername(String username) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = null;
		String sql = "select * from student where username=?";
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return false;
	}
}


















