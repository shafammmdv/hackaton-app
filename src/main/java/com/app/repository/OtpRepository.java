package com.app.repository;

import com.app.entity.Otp;
import com.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {
}
