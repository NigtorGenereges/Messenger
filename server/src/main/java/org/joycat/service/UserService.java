package org.joycat.service;

import org.joycat.entity.Message;
import org.joycat.entity.User;
import org.joycat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository personRepository;


    public void saveUser(User user) {
        personRepository.save(user);
    }

    public void deleteUser(User user) { personRepository.delete(user);}


    public User encryptUser(User user) {

        try {
            user.setPassword(this.encrypt(user.getPassword()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    private SecretKeySpec key = new SecretKeySpec("lovecatverymuch".getBytes(), "AES");

    public String encrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] bytesTxt = cipher.doFinal(text.getBytes());
        return Arrays.toString(bytesTxt);   // to UTF-8

    }

    public String decrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher decriptCipher = Cipher.getInstance("AES");
        decriptCipher.init(Cipher.DECRYPT_MODE, key);
        byte [] decryptedBytes = decriptCipher.doFinal(text.getBytes());
        return Arrays.toString(decryptedBytes);
    }
    public User giveUserLogin(String login){
        Optional<User> optionalUser = personRepository.findByLogin(login);

        return optionalUser.orElse(null);
    }

//    public List<Message> giveAllAfkMessage(String login){
//        Optional<User> optionalUser = personRepository.findByLogin(
//                login);
//
//        return optionalUser.orElse(null);
//
//    }

}
