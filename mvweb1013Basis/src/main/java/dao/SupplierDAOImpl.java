package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class SupplierDAOImpl implements SupplierDAO{
	String url="jdbc:mysql://localhost:3306/classicmodels";
	String user="root";
	String password="1234";
	List<Supplier> data=new ArrayList<>();

	static {//static程式載入的時候只會執行一次
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver error: "+e.getMessage());
		}
	}

	@Override
	public int insert(Supplier s) {
		String sql="insert into classicmodels.suppliers(SUP_ID,SUP_NAME,STREET,CITY,STATE,ZIP) values(?,?,?,?,?,?)";
		try {
			Connection con=DriverManager.getConnection(url,user,password);
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, s.getSupId());
			st.setString(2, s.getSupName());
			st.setString(3, s.getStreet());
			st.setString(4, s.getCity());
			st.setString(5, s.getState());
			st.setString(6, s.getZip());
			int r=st.executeUpdate();
			con.close();
			return r;//insert成功回傳1
		} catch (SQLException e) {
			System.out.println("insert Error "+e.getMessage());
		}
		return -1;//insert失敗回傳-1
	}

	@Override
	public int update(Supplier s) {
		String sql="update classicmodels.suppliers set SUP_NAME=? ,STREET=? ,CITY=? ,STATE=? ,ZIP=? where SUP_ID=? ;";
		   try {
			   Connection cn=DriverManager.getConnection(url,user,password);
			   PreparedStatement st=cn.prepareStatement(sql);
			   st.setString(1, s.getSupName());
			   st.setString(2, s.getStreet());
			   st.setString(3, s.getCity());
			   st.setString(4, s.getState());
			   st.setString(5, s.getZip());
			   st.setInt(6, s.getSupId());
			   int r=st.executeUpdate();
			   cn.close();
			   return r;
		   }catch(SQLException ex) {
			  System.out.println("update Error "+ex.getMessage());
		   }
		   return -1;
	}

	@Override
	public int delete(int supId) {
		String sql="delete from classicmodels.suppliers where SUP_ID=? ;";
		   try {
			   Connection cn=DriverManager.getConnection(url,user,password);
			   PreparedStatement st=cn.prepareStatement(sql);
			   st.setInt(1,supId);
			   int r=st.executeUpdate();
			   cn.close();
			   return r;
		   }catch(SQLException ex) {
			  System.out.println("delete Error "+ex.getMessage());
		   }
		   return -1;
	}

	@Override
	public List<Supplier> findAll() {
		data=new ArrayList<>();
		String sql="SELECT SUP_ID,SUP_NAME,STREET,CITY,STATE,ZIP FROM classicmodels.suppliers;";

		try {
			Connection con=DriverManager.getConnection(url, user, password);
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				Supplier s1=new Supplier();
				s1.setSupId(rs.getInt("SUP_ID"));
				s1.setSupName(rs.getString("SUP_NAME"));
				s1.setStreet(rs.getString("STREET"));
				s1.setCity(rs.getString("CITY"));
				s1.setState(rs.getString("STATE"));
				s1.setZip(rs.getString("ZIP"));
				data.add(s1);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("findAll Error "+e.getMessage());
		}
		return data;
	}

	@Override
	public Supplier findById(int supId) {
		String sql="select SUP_ID,SUP_NAME,STREET,CITY,STATE,ZIP from classicmodels.suppliers where SUP_ID=?;";
		try {
			Connection con=DriverManager.getConnection(url,user,password);
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, supId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Supplier s1=new Supplier();
				s1.setSupId(rs.getInt("SUP_ID"));
				s1.setSupName(rs.getString("SUP_NAME"));
				s1.setStreet(rs.getString("STREET"));
				s1.setCity(rs.getString("CITY"));
				s1.setState(rs.getString("STATE"));
				s1.setZip(rs.getString("ZIP"));

				con.close();
				return s1;
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("findById Error: "+e.getMessage());
		}

		return null;
	}


}
