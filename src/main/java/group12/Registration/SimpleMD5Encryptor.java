package group12.Registration;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Simple encryptor uses Java's simple MD5 Message-Digest algorithm to encrypts without using salt
//Maybe make a more advanced encryptor with better algorithm and salt if there are time at end, following S.O.L.I.D.
// allows me to be agile and do this.
public class SimpleMD5Encryptor implements IEncrypt {

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
            return input;
        }
    }

}