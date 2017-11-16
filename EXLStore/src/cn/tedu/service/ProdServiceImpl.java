package cn.tedu.service;

import java.util.List;

import cn.tedu.bean.Product;
import cn.tedu.dao.ProdDao;
import cn.tedu.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {
	private ProdDao dao = BasicFactory.getFactory().getInstance(ProdDao.class);

	@Override
	public List<Product> findAll(int page) {
		return dao.findAll(page);
	}

	@Override
	public boolean updatePnum(String pid, int pnum) {
		return dao.updatePnum(pid,pnum);
	}

	@Override
	public boolean delProd(String pid) {
		return dao.delProd(pid);
	}

	@Override
	public List<Product> findAllByCondition(String _name, String _category, double _minprice, double _maxprice) {
		return dao.findAllByCondition(_name, _category, _minprice, _maxprice);
	}

	@Override
	public List<Product> findAllBySearch(String search) {
		return dao.findAllBySearch(search);
	}

	@Override
	public Product findProdById(String pid) {
		return dao.findProdById(pid);
	}

	@Override
	public boolean UpdataProdStat(String pid, String stat) {
		// TODO Auto-generated method stub
		return dao.UpdataProdStat(pid, stat);
	}

	@Override
	public int countpages() {
		// TODO Auto-generated method stub
		return dao.countpages();
	}

}
