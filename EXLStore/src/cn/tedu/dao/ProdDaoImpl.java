package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.tedu.bean.Product;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.BeanListHandler;
import cn.tedu.utils.JDBCUtils;
import cn.tedu.utils.TranManager;

public class ProdDaoImpl implements ProdDao {
	int pagesize = 8;
	  

	@Override
	public List<Product> findAll(int page) {
		try {
			String sql="select * from products limit "+(page-1)*this.pagesize+","+this.pagesize;
			return JDBCUtils.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updatePnum(String pid, int pnum) {
		try {
			return JDBCUtils.update("update products set pnum=? where id=?", pnum, pid)>0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean delProd(String pid) {
		String sql = "delete from products where id=?";
		try {
			return JDBCUtils.update(sql, pid) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Product> findAllByCondition(String _name, String _category, double _minprice, double _maxprice) {
		try {
			String sql = "select * from products where name like ? and category like ? and price>? and price<?";
			return JDBCUtils.query(sql, new BeanListHandler<Product>(Product.class), "%"+_name+"%", "%"+_category+"%", _minprice, _maxprice);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Product> findAllBySearch(String search) {
		try {
			String sql = "select * from products where name like ? or category like ? or description like ?";
			return JDBCUtils.query(sql, new BeanListHandler<Product>(Product.class), "%"+search+"%", "%"+search+"%", "%"+search+"%");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product findProdById(String pid) {
		try {
			String sql = "select * from products where id=?";
			return JDBCUtils.query(sql, new BeanHandler<Product>(Product.class), pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	@Override
	public void changePnum(String product_id, int pnumAdd) {
		String sql = "update products set pnum=pnum+? where id=?";
		try {
			JDBCUtils.update(sql, pnumAdd, product_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public boolean UpdataProdStat(String pid, String stat) {

		String sql = "update products set stat=? where id=?";
		try {
			JDBCUtils.update(sql, stat, pid);
			return true;
		} catch (SQLException e) {
			 
			e.printStackTrace();
			 
		}
		return false;
		 
	}
	
	
	//求分页
	
	 int  Productcount = JDBCUtils.AllCountBook();

 

	@Override
	public int countpages() {
		// TODO Auto-generated method stub
		return (this.Productcount%this.pagesize==0?this.Productcount/this.pagesize:this.Productcount/this.pagesize+1);
		
		 		 
	}
	 
	 
	 
	 
	 
	 
	
	
}
