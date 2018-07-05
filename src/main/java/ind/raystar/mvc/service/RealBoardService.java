package ind.raystar.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ind.raystar.mvc.dao.BoardDAOImpl;

@Service("RealBoardService")
public class RealBoardService extends BoardServiceImpl implements DAOConnectable{

	@Autowired
	private BoardDAOImpl realDAO;
	
	@Autowired
	@Override
	public void setDAO() {
		super.dao = realDAO;
	}
	
}
