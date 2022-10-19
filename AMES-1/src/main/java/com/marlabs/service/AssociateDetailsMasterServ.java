package com.marlabs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.entity.AssociateDetailsMaster;
import com.marlabs.repo.AssociateDetailsMasterRepo;

@Service

public class AssociateDetailsMasterServ {

	@Autowired
	private AssociateDetailsMasterRepo repo;

	public List<AssociateDetailsMaster> listAll() {
		return repo.findAll();
	}

//	public void getPieChart(Model model) throws IllegalStateException, IOException {
//		repo.save(model);
//	}

	public List<AssociateDetailsMaster> findAssociatesByBatchCode(String batchCode) {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public void savePDF(List<AssociateDetailsMaster> batches, HttpServletResponse response) {
		// TODO Auto-generated method stub
		repo.saveAll(batches);
	}
}
