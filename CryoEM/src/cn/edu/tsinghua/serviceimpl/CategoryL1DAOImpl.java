package cn.edu.tsinghua.serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.tsinghua.db.MyHibernateSessionFactory;
import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.service.CategoryL1DAO;

public class CategoryL1DAOImpl implements CategoryL1DAO{

	@Override
	public boolean addL1Category(CategoryL1 cl1) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(cl1);
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
	public List<CategoryL1> listAllL1Category() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<CategoryL1> list = null;
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from CategoryL1";
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
	@Override
	public boolean isCategoryExist(CategoryL1 cl1) {
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from CategoryL1 where categoryName=?";
			Query query = session.createQuery(hql);
			query.setParameter(0,  cl1.getCategoryName());
			List list = query.list();
			tx.commit();
			
			if(list.size() > 0)
				return true;
			else			
				return false;
				
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
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
			

	

}
