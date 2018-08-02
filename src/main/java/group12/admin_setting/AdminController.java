package group12.admin_setting;

import group12.data_access.Student;
import group12.data_access.Tutor;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    private static final String AUTHORIZED = "AUTHORIZED";
    private static final String UNAUTHORIZED = "UNAUTHORIZED";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final String ERROR = "ERROR";

    private static Logger logger = LogManager.getLogger(AdminController.class);

    IAccessToken decoder;
    IAdminSettingDAO adminDAO;

    @Autowired
    public AdminController(IAdminSettingDAO adminDAO){
        decoder = JWTAccessToken.getInstance();
        this.adminDAO = adminDAO;
    }

    public AdminController(IAccessToken decoder, IAdminSettingDAO adminDAO){
        this.decoder = decoder;
        this.adminDAO = adminDAO;
    }

    @PostMapping(path="admin/setting/access", consumes = "application/json", produces = "text/plain")
    public String authorizeAdmin(@RequestBody Map<String,String> body){
        try{
            boolean isAuthorized = authorizeAdmin(body.get("token"));
            if(isAuthorized){
                return AUTHORIZED;
            }
            else{
                return UNAUTHORIZED;
            }
        }catch (Exception e){
            logger.error(ERROR, e);
        }
        return ERROR;
    }

    private boolean authorizeAdmin(String token){
        if(token == null){
            return false;
        }
        String email = decoder.decodeToken(token);
        int count = adminDAO.countAdminByEmail(email);
        if(count == 1){
            return true;
        }
        else{
            return false;
        }
    }

    @PostMapping(path="admin/setting/password", consumes = "application/json", produces = "text/plain")
    public String changePassword(@RequestBody Map<String,String> body){
        try{
            boolean isAuthorized = authorizeAdmin(body.get("token"));
            if(isAuthorized){
                String email = decoder.decodeToken(body.get("token"));
                IEncryptor encryptor = SimpleMD5Encryptor.getInstance();
                String password = encryptor.encrypt(body.get("password"));
                if(adminDAO.setAdminPassword(email,password)){
                    return SUCCESS;
                }
                else{
                    return FAILURE;
                }
            }
            else{
                return UNAUTHORIZED;
            }
        }catch (Exception e){
            logger.error(ERROR, e);
        }
        return ERROR;
    }

    @PostMapping(path="admin/setting/price", consumes = "application/json", produces = "text/plain")
    public String changePrice(@RequestBody Map<String,String> body){
        try{
            boolean isAuthorized = authorizeAdmin(body.get("token"));
            if(isAuthorized){
                float[] prices = new float[4];
                prices[0] = Float.parseFloat(body.get("priceOne"));
                prices[1] = Float.parseFloat(body.get("priceTwo"));
                prices[2] = Float.parseFloat(body.get("priceThree"));
                prices[3] = Float.parseFloat(body.get("priceFour"));
                for (int i = 0; i < prices.length; i++){
                    if(adminDAO.setSubPlanPrice(i+1,prices[i]) == false){
                        return FAILURE;
                    }
                }
                return SUCCESS;
            }
            else{
                return UNAUTHORIZED;
            }
        } catch (Exception e){
            logger.error(ERROR, e);
        }
        return ERROR;
    }

    @PostMapping(path="admin/setting/ban/student", consumes = "application/json", produces = "text/plain")
    public String banStudent(@RequestBody Map<String,String> body){
        try{
            boolean isAuthorized = authorizeAdmin(body.get("token"));
            if(isAuthorized){
                int id = Integer.parseInt(body.get("studentID"));
                if(adminDAO.setStudentBanStatus(id, true)){
                    return SUCCESS;
                }
                else{
                    return FAILURE;
                }
            }
            else{
                return UNAUTHORIZED;
            }
        } catch (Exception e){
            logger.error(ERROR, e);
        }
        return ERROR;
    }

    @PostMapping(path="admin/setting/unban/student", consumes = "application/json", produces = "text/plain")
    public String unbanStudent(@RequestBody Map<String,String> body){
        try{
            boolean isAuthorized = authorizeAdmin(body.get("token"));
            if(isAuthorized){
                int id = Integer.parseInt(body.get("studentID"));
                if(adminDAO.setStudentBanStatus(id, false)){
                    return SUCCESS;
                }
                else{
                    return FAILURE;
                }
            }
            else{
                return UNAUTHORIZED;
            }
        } catch (Exception e){
            logger.error(ERROR, e);
        }
        return ERROR;
    }

    @PostMapping(path="admin/setting/ban/tutor", consumes = "application/json", produces = "text/plain")
    public String banTutor(@RequestBody Map<String,String> body){
        try{
            boolean isAuthorized = authorizeAdmin(body.get("token"));
            if(isAuthorized){
                int id = Integer.parseInt(body.get("tutorID"));
                if(adminDAO.setTutorBanStatus(id, true)){
                    return SUCCESS;
                }
                else{
                    return FAILURE;
                }
            }
            else{
                return UNAUTHORIZED;
            }
        } catch (Exception e){
            logger.error(ERROR, e);
        }
        return ERROR;
    }

    @PostMapping(path="admin/setting/unban/tutor", consumes = "application/json", produces = "text/plain")
    public String unbanTutor(@RequestBody Map<String,String> body){
        try{
            boolean isAuthorized = authorizeAdmin(body.get("token"));
            if(isAuthorized){
                int id = Integer.parseInt(body.get("tutorID"));
                if(adminDAO.setTutorBanStatus(id, false)){
                    return SUCCESS;
                }
                else{
                    return FAILURE;
                }
            }
            else{
                return UNAUTHORIZED;
            }
        } catch (Exception e){
            logger.error(ERROR, e);
        }
        return ERROR;
    }

    @PostMapping(path="admin/setting/find/student", consumes = "application/json", produces = "application/json")
    public StudentReviews findStudentReviews(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            String email = body.get("email");
            Student student = adminDAO.getStudentByEmail(email);
            if(student == null){
                return new StudentReviews(-1,null,null);
            }
            int sID = student.getStudentID();
            List<ReviewDTO> reviews = adminDAO.getReviewsMadeByStudent(sID);
            return new StudentReviews(sID, email, reviews);
        }
        else{
            return null;
        }
    }

    @PostMapping(path="admin/setting/find/tutor", consumes = "application/json", produces = "application/json")
    public TutorReviews findTutorReviews(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            String email = body.get("email");
            Tutor tutor = adminDAO.getTutorByEmail(email);
            if(tutor == null){
                return new TutorReviews(-1,null,null);
            }
            int tID = tutor.getTutorID();
            List<ReviewDTO> reviews = adminDAO.getReviewsMadeOnTutors(tID);
            return new TutorReviews(tID, email, reviews);
        }
        else{
            return null;
        }
    }

    @PostMapping(path="admin/setting/review/delete", consumes = "application/json", produces = "text/plain")
    public String deleteReview(@RequestBody Map<String,String> body){
        try{
            boolean isAuthorized = authorizeAdmin(body.get("token"));
            if(isAuthorized){
                int reviewID = Integer.parseInt(body.get("reviewID"));
                ReviewDTO review = adminDAO.getReviewByReviewID(reviewID);
                if(review == null){
                    //no such review, so it counts as success
                    return SUCCESS;
                }
                return deleteReviewHelper(review);
            }
            else{
                return UNAUTHORIZED;
            }
        } catch (Exception e){
            logger.error(ERROR, e);
        }
        return ERROR;
    }

    private String deleteReviewHelper(ReviewDTO review){
        if(adminDAO.deleteReviewByID(review.getReviewID())){
            int tutorID = review.getTutorID();
            float reviewRating = review.getRating();
            Tutor tutor = adminDAO.getTutorByID(tutorID);
            float tutorRating = tutor.getRating();
            int tutorTotalRatings = tutor.getTotalRating();
            tutorRating = calculateNewRating(tutorRating, tutorTotalRatings, reviewRating);
            tutorTotalRatings--;
            if(adminDAO.setTutorRatingAndTotalRatings(tutorRating, tutorTotalRatings, tutorID)){
                return SUCCESS;
            }
            else {
                return FAILURE;
            }
        }
        else{
            return FAILURE;
        }
    }

    //this method should probably be private or even refactored back, but I want to test the formula, so it's here.
    protected float calculateNewRating(float tutorRating, int totalRatings, float reviewRating){
        return (tutorRating * totalRatings - reviewRating) / (totalRatings - 1);
    }
}