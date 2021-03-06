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

	@Override
	public boolean isSubCategoryExist(CategoryL2 c2) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from CategoryL2 where parentCategoryName=? and categoryName=?";
			Query query = session.createQuery(hql);
			query.setParameter(0,  c2.getParentCategoryName());
			query.setParameter(1,  c2.getCategoryName());
			
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
	public List<CategoryL2> getSubCategoryDataByPageIndex(int offset, int pageSize) {
		// TODO Auto-generated method stub
		
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<CategoryL2> list = null;
        String hql = "from CategoryL2";
        
        try
        {
            tx = session.beginTransaction();            
            Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);
            
            list = query.list();
            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally
        {
        	if(tx != null)
            {
                tx = null;
            }
        }
        
        
        return list;
	}
			

	@Override
	public boolean deleteSubCategory(int scid) {
		// TODO Auto-generated method stub
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        CategoryL2 c = new CategoryL2();
        c.setScid(scid);
        
        try
        {
            tx = session.beginTransaction();            
            session.delete(c);            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
            return false;
        }
        finally
        {
        	if(tx != null)
            {
                tx = null;
            }
        }
		return true;
	}
	
	
	@Override
	public boolean updateSubCategory(CategoryL2 sc) {
		// TODO Auto-generated method stub
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        
        try
        {
            tx = session.beginTransaction();            
            session.update(sc);            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
            return false;
        }
        finally
        {
        	if(tx != null)
            {
                tx = null;
            }
        }
		return true;
	}
	
	@Override
	public int getTotalRowNum() {
		// TODO Auto-generated method stub
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        String hql = "from CategoryL2";
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);
            
            allRows = query.list().size();
            
            tx.commit();
            
        }
        catch (Exception e)
        {
            if(tx != null)
            {
                tx.rollback();
            }
            
            e.printStackTrace();
        }
        finally
        {
            if(tx != null)
            {
            	tx = null;
            }
        }
        
        return allRows;
		
	}

}
