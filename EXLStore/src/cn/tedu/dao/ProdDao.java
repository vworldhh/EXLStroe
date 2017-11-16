package cn.tedu.dao;

import java.sql.Connection;
import java.util.List;

import cn.tedu.bean.Product;

public interface ProdDao extends Dao {

	List<Product> findAll(int page);

	 
	boolean updatePnum(String pid, int pnum);
 
	boolean delProd(String pid);

	 
	List<Product> findAllByCondition(String _name, String _category, double _minprice, double _maxprice);

 
	List<Product> findAllBySearch(String search);

	 
	Product findProdById(String pid);

	 
	void changePnum(String product_id, int pnumAdd);

	boolean UpdataProdStat(String pid, String stat);


	int countpages();
}
