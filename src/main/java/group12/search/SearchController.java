package group12.search;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public SearchResponse getSearchResults(@RequestBody SearchRequest searchRequest) {
        return SearchService.getSearchResponse(searchRequest);
    }
}
