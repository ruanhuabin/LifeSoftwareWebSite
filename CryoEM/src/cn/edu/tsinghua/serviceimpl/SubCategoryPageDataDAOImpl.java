package cn.edu.tsinghua.serviceimpl;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.tsinghua.db.MyHibernateSessionFactory;
import cn.edu.tsinghua.entity.CategoryL1;
import cn.edu.tsinghua.entity.CategoryL2;
import cn.edu.tsinghua.entity.CategoryPageData;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.entity.PostPageData;
import cn.edu.tsinghua.entity.SubCategoryPageData;
import cn.edu.tsinghua.service.CategoryL1DAO;
import cn.edu.tsinghua.service.CategoryL2DAO;
import cn.edu.tsinghua.service.CategoryPageDataDAO;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.service.PostPageDataDAO;
import cn.edu.tsinghua.service.SubCategoryPageDataDAO;



public class SubCategoryPageDataDAOImpl implements SubCategoryPageDataDAO {
	
	private CategoryL2DAO dao = new CategoryL2DAOImpl();
	private int pageSize = 3;
	
	private static final Logger logger = Logger.getLogger("CategoryL2PageDataDAOLogger");
	

	@Override
	public SubCategoryPageData getSubCategoryPageData(int pageNum) 
	{
		// TODO Auto-generated method stub		
		SubCategoryPageData subCategoryPageData = new SubCategoryPageData();        
        int allRows = dao.getTotalRowNum();  
        
        //当删除最后一页的最后一条记录时，为了能够显示上一页的数据，而不是显示空数据，此处需要做个判断处理
        if(pageNum * pageSize == (allRows + pageSize))
        {
        	pageNum --;
        }
        
        logger.info("pageNum = " + pageNum);
        int totalPage = subCategoryPageData.getTotalPages(pageSize, allRows);        
        int currentPage = subCategoryPageData.getCurPage(pageNum);        
        int offset = subCategoryPageData.getCurrentPageOffset(pageSize, currentPage);        
        List<CategoryL2> list = dao.getSubCategoryDataByPageIndex(offset, pageSize);  
        
        logger.info("sub category list:" + list);
                
        subCategoryPageData.setList(list);
        subCategoryPageData.setAllRows(allRows);
        subCategoryPageData.setCurrentPage(currentPage);
        subCategoryPageData.setTotalPage(totalPage);
		return subCategoryPageData;
	}


	
	

}
