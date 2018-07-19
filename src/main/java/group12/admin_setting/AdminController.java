package group12.admin_setting;

import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {

    @PostMapping(path="/admin/setting/access")
    public String authorize(@RequestBody Map<String,String> body){
        String token = body.get("token").toString();
        System.out.println(token);
        IAccessToken decoder = new JWTAccessToken();
        String email = decoder.decodeToken(token);
        System.out.println(email);
        /*if(admin table contain this email){
            return SUCCESS;
        }
        else{
            return FAILURE;
        }*/
        return "SUCCESS";
    }



}
