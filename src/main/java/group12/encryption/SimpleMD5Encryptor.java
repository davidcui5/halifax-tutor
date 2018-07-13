package group12.encryption;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Simple encryptor uses Java's simple MD5 Message-Digest algorithm to encrypts without using salt
public class SimpleMD5Encryptor implements IEncryptor {

    public String encrypt (String input){
        try {
            //Java's MessageDigest class has algorithms for encryption
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //input needs to be bytes
            byte[] inputBytes = input.getBytes();
            digest.update(inputBytes);
            //output is also in bytes
            byte[] outputBytes = digest.digest();
            //Java's DatatypeConverter has method that converts bytes to string
            String output = DatatypeConverter.printHexBinary(outputBytes);
            return output;
        }
        catch(NoSuchAlgorithmException e) {
            //No such algorithm, return password without encrypting and log this
            Logger logger = LogManager.getLogger(SimpleMD5Encryptor.class);
            logger.log(Level.ERROR, e);
            return input;
        }
    }

}