package dao;

import java.util.List;

import entity.Student;

public interface StudentDao {
    /**
     * 根据学生帐号和密码查询
     * @param username 学生帐号
     * @param password 学生密码
     * @return
     */
	public Student login(String username,String password);
	/**
	 * 新增
	 * @param s
	 */
	public void addForm(Student s);
	/**
	 * 分页查询数据
	 * @param currrentPage 页码
	 * @param pageSize 每页显示记录数
	 * @return
	 */
	public List<Student> queryByPage(int currrentPage, int pageSize);
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotal();
	
	/**
	 * 根据主键删除数据
	 * @param id 主键编号
	 */
	public void deleteByid(int id);
	
	/**
	 * 根据主键查询
	 * @param id 主键编号
	 * @return
	 */
	public Student queryById(int id);
	/**
	 * 更新数据
	 * @param s
	 */
	public void Update(Student s);
	
	/**
	 * 批量删除
	 */
	public void deleteMore(String ids);
	
	/**
	 * 批量新增
	 */
	public void addMore(List<Student> list);
	
	public List<Student> queryAll();
	
	public boolean checkUsername(String username);
}
















