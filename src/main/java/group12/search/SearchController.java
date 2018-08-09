package group12.search;

import group12.search.request.IdentityRequest;
import group12.search.request.SearchRequest;
import group12.search.response.IdentityResponse;
import group12.search.response.NoLoginSearchResponse;
import group12.search.response.SearchResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/search")
public class SearchController {
    private static final Logger logger = LogManager.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    @RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public SearchResponse getSearchResults(@RequestBody SearchRequest request) {
        logger.info("Search request received " + request.toString());
        return searchService.getSearchResponse(request);
    }

    @RequestMapping(value = "/identity", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public IdentityResponse getIdentityResult(@RequestBody IdentityRequest request) {
        logger.info("Check identity request received " + request.toString());
        return searchService.getSearchIdentity(request);
    }

    @RequestMapping(value = "/noLogin", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public NoLoginSearchResponse getNoLoginSearchResult() {
        logger.info("Check no auth login request received.");
        return searchService.getNoLoginSearchResponse();
    }
}
