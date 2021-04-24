package com.telnet.project.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.PasswordResetToken;




public interface ResetPasswordTokenRepository  extends MongoRepository<PasswordResetToken,String> {

	PasswordResetToken findByToken(String token);

}

