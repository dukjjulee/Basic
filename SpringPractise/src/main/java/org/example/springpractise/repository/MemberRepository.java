package org.example.springpractise.repository;

import org.example.springpractise.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository <Member, Long> {
}
