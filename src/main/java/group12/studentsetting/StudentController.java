package group12.studentsetting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/student/setting")
public class StudentController {
    private static Logger logger = LogManager.getLogger(StudentController.class);

    @Autowired
    private IStudentSetting studentSetting;

    @PostMapping(path = "/access")
    public String authorizeUser(@RequestBody Map<String, String> body) {
        String response = "Error";
        try {
            response = studentSetting.authorizeStudent(body.get("token"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }

    @PostMapping(path = "/password")
    public String changePassword(@RequestBody Map<String, String> body) {
        String response = "Error";
        try {
            response = studentSetting.changePassword(body.get("token"), body.get("password"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }

    @PostMapping(path = "/email")
    public String changeEmail(@RequestBody Map<String, String> body) {
        String response = "Error";
        try {
            response = studentSetting.changeEmail(body.get("token"), body.get("email"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }

    @PostMapping(path = "/phone")
    public String changePhone(@RequestBody Map<String, String> body) {
        String response = "Error";
        try {
            response = studentSetting.changePhone(body.get("token"), body.get("phone"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }

    @PostMapping(path = "/activation")
    public String checkActivationStatus(@RequestBody Map<String, String> body) {
        String response = "Error";
        try {
            response = studentSetting.checkActivationStatus(body.get("token"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }

    @PostMapping(value = "/reactivation")
    public String resendActivateCode(@RequestBody Map<String, String> body) {
        String response = "Error";
        try {
            response = studentSetting.resendActivateCode(body.get("token"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }
}
