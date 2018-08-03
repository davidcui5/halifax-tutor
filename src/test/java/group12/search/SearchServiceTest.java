package group12.search;

import group12.search.dataaccess.TutorPublicInfoDAO;
import group12.search.dataaccess.TutorPublicInfoDAOMock;
import org.junit.Before;

public class SearchServiceTest {
    private SearchService service;

    @Before
    public void testSetup() {
        TutorPublicInfoDAO dao = new TutorPublicInfoDAOMock();
        service = new SearchService(dao);
    }
}
