package group12.registration;

import group12.encryption.IEncryptor;

public class EncryptorMock implements IEncryptor {
    @Override
    public String encrypt(String input) {
        return input;
    }
}
