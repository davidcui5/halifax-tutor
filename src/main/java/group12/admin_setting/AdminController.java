package group12.admin_setting;

import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path="/admin/setting")
public class AdminController {

    IAccessToken decoder;

    public AdminController(){
        decoder = JWTAccessToken.getInstance();
    }

    @PostMapping(path="/access")
    public String authorizeAdmin(@RequestBody Map<String,String> body){
        String email = decoder.decodeToken(body.get("token"));
        /*if(admin table contain this email){
            return AUTHORIZED;
        }
        else{
            return UNAUTHORIZED;
        }*/
        return "AUTHORIZED";
    }

    @PostMapping(path="/password")
    public String changePassword(@RequestBody Map<String,String> body){
        String email = decoder.decodeToken(body.get("token"));
        String password = body.get("password");
        /*if(admin table contain this email){
            return AUTHORIZED;
        }
        else{
            return UNAUTHORIZED;
        }*/
        return "SUCCESS";
    }


}
