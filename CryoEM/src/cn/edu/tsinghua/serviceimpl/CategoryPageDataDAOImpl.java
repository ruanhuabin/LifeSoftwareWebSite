package cn.edu.tsinghua.serviceimpl;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.tsinghua.db.MyHibernateSessionFactory;
import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.CategoryPageData;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.entity.PostPageData;
import cn.edu.tsinghua.service.CategoryL1DAO;
import cn.edu.tsinghua.service.CategoryPageDataDAO;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.service.PostPageDataDAO;



public class CategoryPageDataDAOImpl implements CategoryPageDataDAO {
	
	private CategoryL1DAO dao = new CategoryL1DAOImpl();
	private int pageSize = 3;
	
	private static final Logger logger = Logger.getLogger("CategoryL1PageDataDAOLogger");
	

	@Override
	public CategoryPageData getCategoryPageData(int pageNum) 
	{
		// TODO Auto-generated method stub		
		CategoryPageData categoryPageData = new CategoryPageData();        
        int allRows = dao.getTotalRowNum();  
        
        //当删除最后一页的最后一条记录时，为了能够显示上一页的数据，而不是显示空数据，此处需要做个判断处理
        if(pageNum * pageSize == (allRows + pageSize))
        {
        	pageNum --;
        }
        
        logger.info("pageNum = " + pageNum);
        int totalPage = categoryPageData.getTotalPages(pageSize, allRows);        
        int currentPage = categoryPageData.getCurPage(pageNum);        
        int offset = categoryPageData.getCurrentPageOffset(pageSize, currentPage);        
        List<CategoryL1> list = dao.getCategoryDataByPageIndex(offset, pageSize);  
        
        logger.info("category list:" + list);
                
        categoryPageData.setList(list);
        categoryPageData.setAllRows(allRows);
        categoryPageData.setCurrentPage(currentPage);
        categoryPageData.setTotalPage(totalPage);
		return categoryPageData;
	}
	
	
	

}
