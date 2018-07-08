package group12.encryption;

//Interface for encryptors
//Allows different encryptors to implement this with different encryption algorithms
public interface IEncryptor {

    String encrypt(String input);

}