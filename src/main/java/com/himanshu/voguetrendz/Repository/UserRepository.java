package com.himanshu.voguetrendz.Repository;

import com.himanshu.voguetrendz.Entities.User;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.email =:email")
    public User getUserByUsername(@Param("email") String email);

    @Query(value = "INSERT INTO cart (user_id, product_id) VALUES (:userId, :productId)", nativeQuery = true)
    public User addToCart(@Param("userId") int userId, @Param("productId") int productId);
}
