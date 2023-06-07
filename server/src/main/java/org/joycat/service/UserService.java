package org.joycat.service;

import org.joycat.entity.User;
import org.joycat.entity.UserOnline;
import org.joycat.entity.UserShort;
import org.joycat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    private SecretKeySpec key = new SecretKeySpec("lovecatverymuch".getBytes(), "AES");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserOnlineService userOnlineService;

    public boolean addNewUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            // TODO: Change general exception to specific and provide logging
            return false;
        }
    }

    public void deleteUser(User user) { userRepository.delete(user);}

    public boolean signInUser(UserShort userShort, String userIp) {
        User user = userRepository.findByLogin(userShort.getLogin()).orElse(null);
        if (user != null && checkPassword(userShort, user)) {
            userOnlineService.turnOnline(new UserOnline(userShort.getLogin(), userIp));
            return true;
        } else {
            return false;
        }

    }

    public boolean checkPassword(UserShort userShort, User user) {
        return decrypt(user.getPassword()).equals(userShort.getPassword());
    }

    public User encryptUser(User user) {

        try {
            user.setPassword(this.encrypt(user.getPassword()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public String encrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        try {
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte [] bytesTxt = cipher.doFinal(text.getBytes());
            return Arrays.toString(bytesTxt);   // to UTF-8
        }  catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            return null;
        }

    }

    public String decrypt(String text) {
        try {
            Cipher decriptCipher = Cipher.getInstance("AES");
            decriptCipher.init(Cipher.DECRYPT_MODE, key);
            byte [] decryptedBytes = decriptCipher.doFinal(text.getBytes());
            return Arrays.toString(decryptedBytes);
        }  catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            return null;
        }
    }

}
