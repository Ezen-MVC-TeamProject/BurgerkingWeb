package com.burger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.burger.dto.AdminVO;
import com.burger.dto.ProductVO;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import com.burger.dto.EventVO;
import com.burger.dto.MemberVO;
import com.burger.dto.QnaVO;
import com.burger.dto.orderVO;
import com.burger.util.DBman;
import com.burger.util.Paging;

public class AdminDao {
	private AdminDao() { }
	private static AdminDao ist = new AdminDao();
	public static AdminDao getInstance() { 	return ist;	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public AdminVO adminCheck(String adminId) {
		AdminVO avo = null;
		String sql = "select * from admin where id=?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adminId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				avo = new AdminVO();
				avo.setId(rs.getString("id"));
				avo.setPwd(rs.getString("pwd"));
				avo.setName(rs.getString("name"));
				avo.setPhone(rs.getString("phone"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return avo;
	}

	public int getAllCount(String tableName, String fieldName, String key) {
		int count = 0;
		String sql = "select count(*) as cnt from " + tableName + " where "
				+ fieldName + " like '%'||?||'%'";
		// 필드명 like '%?%'에서 ?가 빈칸이거나 널이면, 해당 필드의 조건은 검색하지 않은 것과 같아집니다.
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt("cnt");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return count;
	}

	public ArrayList<ProductVO> listProduct(Paging paging, String key) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		//String sql ="select * from product order by pseq desc";
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, p.* from "
				+ "((select * from product where pname like '%'||?||'%' and kind3 between 1 and 3 order by pseq desc) p)"
				+ ") where rn >=?"
				+ ") where rn <=?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setIndate(rs.getTimestamp("indate"));
				pvo.setPname(rs.getString("pname"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setKind1(rs.getString("kind1"));
				pvo.setKind2(rs.getString("kind2"));
				pvo.setKind3(rs.getString("kind3"));
				pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				list.add(pvo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public ArrayList<ProductVO> listShortProduct(Paging paging, String key) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		//String sql ="select * from product order by pseq desc";
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, p.* from "
				+ "((select * from product where pname like '%'||?||'%' and kind3='4' order by pseq desc) p)"
				+ ") where rn >=?"
				+ ") where rn <=?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setIndate(rs.getTimestamp("indate"));
				pvo.setPname(rs.getString("pname"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setKind1(rs.getString("kind1"));
				pvo.setKind2(rs.getString("kind2"));
				pvo.setKind3(rs.getString("kind3"));
				pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				list.add(pvo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public int getshortPCount(String tableName, String fieldName, String key) {
		int count = 0;
		String sql = "select count(*) as cnt from " + tableName + " where "
				+ fieldName + " like '%'||?||'%' and kind3 = '4'";
		// 필드명 like '%?%'에서 ?가 빈칸이거나 널이면, 해당 필드의 조건은 검색하지 않은 것과 같아집니다.
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt("cnt");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return count;
	}


	public void updateProduct(ProductVO pvo) {
		String sql = "update product set kind1=?, useyn=?, pname=?, price1=?, "
				+ " content=?, image=?, kind2=?, kind3=? where pseq=?";
		
		con = DBman.getConnection();
		try {			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind1());
		    pstmt.setString(2, pvo.getUseyn());
		    pstmt.setString(3, pvo.getPname());
		    pstmt.setInt(4, pvo.getPrice1());
		    pstmt.setString(5, pvo.getContent());
		    pstmt.setString(6, pvo.getImage());
		    pstmt.setString(7, pvo.getKind2());
		    pstmt.setString(8, pvo.getKind3());
		    pstmt.setInt(9, pvo.getPseq());
		    pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBman.close(con, pstmt, rs); 
		}	
		
	}

	public void insertProduct(ProductVO pvo) {
		String sql = "insert into product(pseq, kind1, kind2, kind3, pname, price1, price2, price3,"
				+ " content, image, useyn) values(pseq.nextVal, ?,?,?,?,?,?,?,?,?,?)";
		con = DBman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind1());      
			pstmt.setString(2, pvo.getKind2());
			pstmt.setString(3, pvo.getKind3());
		    pstmt.setString(4, pvo.getPname());
		    pstmt.setInt(5, pvo.getPrice1());
		    pstmt.setInt(6, pvo.getPrice2());
		    pstmt.setInt(7, pvo.getPrice3());
		    pstmt.setString(8, pvo.getContent());
		    pstmt.setString(9, pvo.getImage());  
		    pstmt.setString(10, pvo.getUseyn());
		    pstmt.executeUpdate();
		} catch (SQLException e) {	e.printStackTrace();
		} finally {
			DBman.close(con, pstmt, rs); 	
		}
		
	}

	public int checkShortProductYN(String k1, String k2, String k3) {
		int result=1;
		String sql = "select * from product where kind1 = ?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, k1);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				result = 2;
				return result;
			}
			
			sql = "select * from product where kind1=? and kind2 = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, k1);
			pstmt.setString(2, k2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 3;
				return result;
			}
			
			if(k3.equals("4")) {
				result = 4;
				return result;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return result;
	}

	public int checkShortProductYN2(String k1, String k2) {
		int result=1;
		String sql = "select * from product where kind1=? and kind2 = ?";
		
		try {
			con = DBman.getConnection();			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, k1);
			pstmt.setString(2, k2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 2;
				return result;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return result;
	}

	public ArrayList<MemberVO> listMember(Paging paging, String key) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		//String sql ="select * from product order by pseq desc";
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, m.* from "
				+ "((select * from member where name like '%'||?||'%' order by mseq desc) m)"
				+ ") where rn >=?"
				+ ") where rn <=?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("name"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setMseq(rs.getInt("mseq"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setIndate(rs.getTimestamp("indate"));
				mvo.setLastdate(rs.getTimestamp("lastdate"));
				mvo.setMemberkind(rs.getInt("memberkind"));
				list.add(mvo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public void updateMember(MemberVO mvo) {
		String sql = "update member set name=?, pwd=?, phone=? where mseq=?";
		
		con = DBman.getConnection();
		try {			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mvo.getName());
			pstmt.setString(2, mvo.getPwd());
			pstmt.setString(3, mvo.getPhone());
			pstmt.setInt(4, mvo.getMseq());
		    pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBman.close(con, pstmt, rs); 
		}	
	}

	public ArrayList<EventVO> listEvent(Paging paging, String key) {
		ArrayList<EventVO> list = new ArrayList<EventVO>();
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, e.* from "
				+ "((select * from event where subject like '%'||?||'%' order by eseq desc) e)"
				+ ") where rn >=?"
				+ ") where rn <=?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EventVO evo = new EventVO();
				evo.setEseq(rs.getInt("eseq"));
				evo.setSubject(rs.getString("subject"));
				evo.setContent(rs.getString("content"));
				evo.setImage(rs.getString("image"));
				evo.setStartdate(rs.getTimestamp("startdate"));
				evo.setEnddate(rs.getTimestamp("enddate"));
				evo.setState(rs.getInt("state"));
				list.add(evo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public void updateEvent(EventVO evo) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
        int state = 1;
        if(sdf.format(timestamp).compareTo(sdf.format(evo.getEnddate())) > 0) {
        	state = 2;
        }
		String sql = "update event set subject=?, content=?, image=?, enddate=?, state=? where eseq=?";
		
		con = DBman.getConnection();
		try {			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, evo.getSubject());
			pstmt.setString(2, evo.getContent());
			pstmt.setString(3, evo.getImage());
			pstmt.setTimestamp(4, evo.getEnddate());
			pstmt.setInt(5, state);
			pstmt.setInt(6, evo.getEseq());
		    pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBman.close(con, pstmt, rs); 
		}	
	}

	public void insertEvent(EventVO evo) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
        int state = 1;
        if(sdf.format(timestamp).compareTo(sdf.format(evo.getEnddate())) > 0) {
        	state = 2;
        }
		String sql = "insert into event(eseq, subject, content, startdate, enddate, image, state) "
				+ "values(eseq.nextVal, ?,?, sysdate,?,?,?)";
		con = DBman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, evo.getSubject());
			pstmt.setString(2, evo.getContent());
			pstmt.setTimestamp(3, evo.getEnddate());
			pstmt.setString(4, evo.getImage());
			pstmt.setInt(5, state);
		    pstmt.executeUpdate();
		} catch (SQLException e) {	e.printStackTrace();
		} finally {
			DBman.close(con, pstmt, rs); 	
		}
	}

	public ArrayList<QnaVO> listQna(Paging paging, String key) {
		ArrayList<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, q.* from "
				+ "((select * from qna where id like '%'||?||'%' order by qseq desc) q)"
				+ ") where rn >= ?"
				+ ") where rn <= ?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaVO qvo = new QnaVO();
				qvo.setQseq(rs.getInt("qseq"));
				qvo.setSubject(rs.getString("subject"));
				qvo.setContent(rs.getString("content"));
				qvo.setReply(rs.getString("reply"));
				qvo.setId(rs.getString("id"));
				qvo.setRep(rs.getString("rep"));
				qvo.setIndate(rs.getTimestamp("indate"));
				qvo.setReadcount(rs.getInt("readcount"));
				qvo.setPass(rs.getInt("pass"));
				
				list.add(qvo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public void updateQna(QnaVO qvo) {
		String sql = "update qna set reply=?, rep='2' where qseq=?";
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qvo.getReply());
			pstmt.setInt(2, qvo.getQseq());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
	}

	public ArrayList<orderVO> listOrder(Paging paging, String key) {
		ArrayList<orderVO> list = new ArrayList<orderVO>();
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, o.* from "
				+ "((select * from order_view where mname like '%'||?||'%' order by result, odseq desc) o)"
				+ ") where rn >= ?"
				+ ") where rn <= ?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orderVO ovo = new orderVO();
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setId(rs.getString("id"));
				ovo.setIndate(rs.getTimestamp("indate"));
				ovo.setPseq(rs.getInt("pseq"));
				ovo.setQuantity(rs.getInt("quantity"));
				ovo.setResult(rs.getString("result"));
				ovo.setMname(rs.getString("mname"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setPname(rs.getString("pname"));
				ovo.setPrice1(rs.getInt("price1"));
				list.add(ovo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public void updateOrderResult(String odseq) {
		String sql = "update order_detail set result = '2' where odseq = ?";
		
		try {
			con = DBman.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(odseq));
			pstmt.executeUpdate();
			
			sql = "delete from subproduct_order where odseq = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(odseq));
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBman.close(con, pstmt, rs);
		}
	}

}
