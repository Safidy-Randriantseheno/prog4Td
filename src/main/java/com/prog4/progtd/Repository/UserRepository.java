package com.prog4.progtd.Repository;


import com.prog4.progtd.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUsernameAndPassword(String username,String password);
}
