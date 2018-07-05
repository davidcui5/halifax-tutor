package group12.Registration;

//Interface for encryptors
//Allows different encryptors to implement this with different encryption algorithms
public interface IEncrypt {

    String encrypt(String input);

}