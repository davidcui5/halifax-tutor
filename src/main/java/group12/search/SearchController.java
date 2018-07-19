package group12.search;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public SearchResponse getSearchResults() {
        return new SearchResponse();
    }
}
