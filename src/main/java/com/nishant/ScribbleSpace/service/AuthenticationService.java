package com.nishant.ScribbleSpace.service;

import com.nishant.ScribbleSpace.model.ApplicationUser;
import com.nishant.ScribbleSpace.model.Token;
import com.nishant.ScribbleSpace.model.dto.UserAuthenticatedInformationInputDTO;
import com.nishant.ScribbleSpace.repo.TokenRepo;
import com.nishant.ScribbleSpace.repo.UserRepo;
import com.nishant.ScribbleSpace.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenRepo tokenRepo;

    @Autowired
    MailingService mailingService;

    @Transactional
    public String registerUser(String email, String password) {
        Token emailVerificationToken = tokenRepo.save(new Token());

        userRepo.save(new ApplicationUser(
                email,
                passwordEncoder.encode(password),
                emailVerificationToken,
                false
        ));

        mailingService.sendUserVerificationEmail(email,emailVerificationToken.getId());

        return "Please check email for further instructions";
    }

    @Transactional
    public String verifyUserEmail(String email, String token) {
        ApplicationUser user = userRepo.findByUsername(email);
        if(user.getEmailVerificationToken().getId().equals(token)){
            user.setEnabled(true);
            return "Verification success, please proceed to login";
        }
        else{
            userRepo.deleteById(user.getId());
        }
        return "Invalid verification, please register again";
    }

    public String authenticateUser(String email, String password) {
        ApplicationUser user = userRepo.findByUsername(email);

        if(!user.getEnabled()){
            return "email not verified";
        }
        if(!passwordEncoder.encode(password).equals(user.getPassword())){
            return "invalid credentials";
        }

        user.setAuthenticationToken(new Token(UUID.randomUUID().toString()));

        user = userRepo.save(user);

        return user.getAuthenticationToken().getId();
    }

    public boolean verifyAuthenticatedInformationUser(UserAuthenticatedInformationInputDTO authenticatedInformation){
        return userRepo
                .findById(authenticatedInformation.getId())
                .orElseThrow()
                .getAuthenticationToken()
                .getId()
                .equals(authenticatedInformation.getToken());
    }
}
