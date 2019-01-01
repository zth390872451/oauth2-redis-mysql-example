/**
 * 
 */
package com.company.repository;

import com.company.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	Member findOneByUsername(String username);
}
