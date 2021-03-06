package cn.edu.tsinghua.serviceimpl;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.tsinghua.db.MyHibernateSessionFactory;
import cn.edu.tsinghua.entity.Post;
import cn.edu.tsinghua.entity.PostPageData;
import cn.edu.tsinghua.service.PostDAO;
import cn.edu.tsinghua.service.PostPageDataDAO;



public class PostPageDataDAOImpl implements PostPageDataDAO {
	
	private PostDAO postDAO = new PostDAOImpl();
	private int pageSize = 5;
	
	private static final Logger logger = Logger.getLogger("PostPageDataDAOLogger");
	

	@Override
	public PostPageData getPostPageData(int pageNum) 
	{
		// TODO Auto-generated method stub		
		PostPageData postPageData = new PostPageData();        
        int allRows = postDAO.getTotalRowNum();  
        
        //当删除最后一页的最后一条记录时，为了能够显示上一页的数据，而不是显示空数据，此处需要做个判断处理
        if(pageNum * pageSize == (allRows + pageSize))
        {
        	pageNum --;
        }
        
        logger.info("pageNum = " + pageNum);
        int totalPage = postPageData.getTotalPages(pageSize, allRows);        
        int currentPage = postPageData.getCurPage(pageNum);        
        int offset = postPageData.getCurrentPageOffset(pageSize, currentPage);        
        List<Post> list = postDAO.getPostDataByPageIndex(offset, pageSize);        
                
        postPageData.setList(list);
        postPageData.setAllRows(allRows);
        postPageData.setCurrentPage(currentPage);
        postPageData.setTotalPage(totalPage);
		return postPageData;
	}
	
	
	

}
