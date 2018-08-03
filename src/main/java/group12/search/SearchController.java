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
    public SearchResponse getSearchResults(@RequestBody SearchRequest request) {
        return searchService.getSearchResponse(request);
    }

    @RequestMapping(value = "/identity", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public IdentityResponse getIdentityResult(@RequestBody IdentityRequest request) {
        return searchService.getSearchIdentity(request);
    }

    @RequestMapping(value = "/noLogin", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public NoLoginSearchResponse getNoLoginSearchResult(@RequestBody NoLoginSearchRequest request) {
        return searchService.getNoLoginSearchResponse(request);
    }
}
