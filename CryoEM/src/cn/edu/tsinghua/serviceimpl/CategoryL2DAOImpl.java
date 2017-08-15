package cn.edu.tsinghua.serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.tsinghua.db.MyHibernateSessionFactory;
import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.CategoryL2;
import cn.edu.tsinghua.service.CategoryL2DAO;

public class CategoryL2DAOImpl implements CategoryL2DAO{

	@Override
	public boolean addL2Category(CategoryL2 cl2) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(cl2);
			tx.commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
			
		}		
		finally
		{
			if(tx != null)
			{
				tx = null;
			}
			
		}
	}

	@Override
	public List<CategoryL2> listAllL2Category() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<CategoryL2> list = null;
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from CategoryL2";
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			return list;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return list;
			
		}
		finally
		{
			if(tx != null)
			{
				
				tx = null;
			}
		}
	}

}