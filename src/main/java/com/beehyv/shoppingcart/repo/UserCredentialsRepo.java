package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserCredentialsRepo extends JpaRepository<UserCredentials, String>
{
  UserCredentials findByEmail(String email);
}
