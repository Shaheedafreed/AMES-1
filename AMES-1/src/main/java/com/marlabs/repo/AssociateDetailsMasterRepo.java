package com.marlabs.repo;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlabs.entity.AssociateDetailsMaster;

public interface AssociateDetailsMasterRepo extends JpaRepository<AssociateDetailsMaster, String> {

	void save(HttpServletResponse response);

}
