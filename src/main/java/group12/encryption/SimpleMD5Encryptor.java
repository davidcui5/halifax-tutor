package group12.encryption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Simple encryptor uses Java's simple MD5 Message-Digest algorithm to encrypts without using salt
public class SimpleMD5Encryptor implements IEncryptor {

    private static final IEncryptor INSTANCE = new SimpleMD5Encryptor();
    private MessageDigest digest;
    private static Logger logger = LogManager.getLogger(SimpleMD5Encryptor.class);

    private SimpleMD5Encryptor(){
        try{
            digest = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException e) {
            logger.error("Error", e);
        }
    }

    public static IEncryptor getInstance(){
        return INSTANCE;
    }

    public String encrypt (String input){
        try {
            byte[] inputBytes = input.getBytes();
            digest.update(inputBytes);
            byte[] outputBytes = digest.digest();
            String output = DatatypeConverter.printHexBinary(outputBytes);
            return output;
        }
        catch(Exception e) {
            logger.error("Error", e);
        }
        return input;
    }

}