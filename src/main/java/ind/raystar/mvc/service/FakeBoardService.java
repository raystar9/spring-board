package ind.raystar.mvc.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ind.raystar.mvc.dao.BoardDAOFake;

@Service("FakeBoardService")
public class FakeBoardService extends BoardServiceImpl{

	@Resource(name = "boardDAOFake")
	private BoardDAOFake fakeDAO;
	
	@Autowired
	public void setDAO() {
		super.dao = fakeDAO;
		System.out.println("realDAO" + fakeDAO);
	}
}
