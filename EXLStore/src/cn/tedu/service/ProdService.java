package cn.tedu.service;

import java.util.List;

import cn.tedu.bean.Product;

public interface ProdService extends Service {
	 
	List<Product> findAll(int page);
 
	boolean updatePnum(String pid, int pnum);

	 
	boolean delProd(String pid);

	 
	List<Product> findAllByCondition(String _name, String _category, double _minprice, double _maxprice);

 
	List<Product> findAllBySearch(String search);

 
	Product findProdById(String pid);
	
	 
	boolean UpdataProdStat(String pid, String stat);

	int countpages();
}
