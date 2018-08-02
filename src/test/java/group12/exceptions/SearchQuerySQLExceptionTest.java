package group12.exceptions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchQuerySQLExceptionTest {
    private SearchQuerySQLException searchQuerySQLException;

    @Before
    public void testSetup() {
        this.searchQuerySQLException =  new SearchQuerySQLException("Testing");
    }

    @Test
    public void test() {
        String message = "Testing";
        assertEquals(message, searchQuerySQLException.getMessage());
    }

    @After
    public void testTeardown() {
        this.searchQuerySQLException = null;
    }
}
