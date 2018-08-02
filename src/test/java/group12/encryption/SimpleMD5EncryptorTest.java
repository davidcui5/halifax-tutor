package group12.encryption;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleMD5EncryptorTest {
    private IEncryptor encryptor;

    @Before
    public void setUp() {
        this.encryptor = SimpleMD5Encryptor.getInstance();
    }

    @Test
    public void testEncryptGiveSameHash() {
        String input1 = "Qwer1234";
        String output1 = encryptor.encrypt(input1);
        assertEquals(output1, encryptor.encrypt(input1));

        String input2 = "abc@12345";
        String output2 = encryptor.encrypt(input2);
        assertEquals(output2, encryptor.encrypt(input2));
    }

    @After
    public void tearDown() {
        this.encryptor = null;
    }
}