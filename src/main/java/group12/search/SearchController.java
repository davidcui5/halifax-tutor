package group12.search;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public SearchResponse getSearchResults(@RequestBody SearchRequest searchRequest) {
        return searchService.getSearchResponse(searchRequest);
    }

    @RequestMapping(value = "/identity", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public IdentityResponse getIdentityResult(@RequestBody IdentityRequest identityRequest) {
        return searchService.getSearchIdentity(identityRequest);
    }

    @RequestMapping(value = "/noLogin", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public NoLoginSearchResponse getNoLoginSearchResult(@RequestBody NoLoginSearchRequest noLoginSearchRequest) {
        return searchService.getNoLoginSearchResponse(noLoginSearchRequest);
    }
}
