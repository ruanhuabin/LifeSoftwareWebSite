package cn.edu.tsinghua.serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.tsinghua.db.MyHibernateSessionFactory;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.service.PostDAO;

public class PostDAOImpl implements PostDAO {

	@Override
	public boolean addPost(Post p) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(p);
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
	public List<Post> listAllPost() {
		Transaction tx = null;
		List<Post> list = null;
		try{
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Post";
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
	public int getTotalRowNum() {
		// TODO Auto-generated method stub
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        int allRows = 0;
        String hql = "from Post";
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
	
	@Override
	public List<Post> getPostDataByPageIndex(int offset, int pageSize) {
		// TODO Auto-generated method stub
		
		
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Post> list = null;
        String hql = "from Post";
        
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
	public boolean deletePost(int pid) {
		// TODO Auto-generated method stub
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        
        Post p = new Post();
        p.setPid(pid);
        
        try
        {
            tx = session.beginTransaction();            
            session.delete(p);            
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
	public Post getPost(int pid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Post where pid=?";
			Query query = session.createQuery(hql);
			query.setParameter(0,  pid);
			List<Post> list = query.list();
			tx.commit();
			
			if(list.size() > 0)
				return list.get(0);
			else			
				return null;
		}
		catch(Exception ex)
		{			
			ex.printStackTrace();
		}
		finally
		{
			if(tx != null)
			{

				tx = null;
			}
			
			
		}
		return null;
	}

	@Override
	public boolean updatePost(Post p) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try{			
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
	        tx = session.beginTransaction();
	        session.update(p);
	        tx.commit();
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
		
		
		return true;
	}

	@Override
	public List<Post> getPostByHQL(String hql) {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Post> list = null;
        
        try
        {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(hql);
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

}
