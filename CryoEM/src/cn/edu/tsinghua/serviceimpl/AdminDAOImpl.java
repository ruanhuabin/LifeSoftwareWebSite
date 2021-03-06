package cn.edu.tsinghua.serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.tsinghua.db.MyHibernateSessionFactory;
import cn.edu.tsinghua.entity.Admin;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.service.AdminDAO;
import cn.edu.tsinghua.service.PostDAO;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public boolean isAdminValid(Admin admin) {
		
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Admin where username=? and password=?";			
			Query query = session.createQuery(hql);			
			query.setParameter(0,  admin.getUsername());
			query.setParameter(1, admin.getPassword());
			
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

	@Override
	public boolean isAdminExist(Admin admin) {
		
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Admin where username=?";			
			Query query = session.createQuery(hql);			
			query.setParameter(0,  admin.getUsername());
			
			
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
	
	@Override
	public boolean addAdmin(Admin admin) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(admin);
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

	
}
