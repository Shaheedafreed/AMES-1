package com.marlabs.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marlabs.entity.AssociateDetailsMaster;
import com.marlabs.entity.MentorPhaseMasterTable;
import com.marlabs.helper.ExcelSheet;
import com.marlabs.service.AssociateDetailsMasterServ;
import com.marlabs.service.MentorPhaseService;

@RestController
public class MentorPhaseController {

	@Autowired
	private MentorPhaseService mentorService;

	@Autowired
	private AssociateDetailsMasterServ service;

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
		if (ExcelSheet.checkExcelFormat(file)) {
			// true
			this.mentorService.save(file);

			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}

	@GetMapping("/get")
	public Iterable<MentorPhaseMasterTable> getAll() {
		return this.mentorService.getAll();
	}

//	@GetMapping(value = "/id/{batchCode}")
//	public List<AssociateDetailsMaster> getById(@PathVariable("batchCode") String batchCode) {
//		return this.service.findByBatchCode(batchCode);
//
//	}

//	@GetMapping("/users/export/pdf")
//	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
//		response.setContentType("application/pdf");
//		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//		String currentDateTime = dateFormatter.format(new Date());
//
//		System.out.println("file is loading");
//
//		String headerKey = "Content-Disposition";
//		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//		response.setHeader(headerKey, headerValue);
//		List<AssociateDetailsMaster> listUsers = service.listAll();
//		PDFExporter exporter = new PDFExporter(listUsers);
//
//		System.out.println("file is loaded");
//		exporter.export(response);
//	}

//	@GetMapping("/graph")
//	public String getPieChart(Model model) {
//		Map<String, Integer> graphData = new TreeMap<>();
//		graphData.put("2016", 147);
//		graphData.put("2017", 1256);
//		graphData.put("2018", 3856);
//		graphData.put("2019", 19807);
//		model.addAttribute("chartData", graphData);
//		return "view";
//	}

	@RequestMapping(value = "/savebatchpdf", method = RequestMethod.GET)
	public ResponseEntity<String> saveBatchPDF(@RequestParam(required = false, name = "batchCode") String batchCode,
			HttpServletResponse response) {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		System.out.println("file is loading");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<AssociateDetailsMaster> batches = service.findAssociatesByBatchCode(batchCode);

		service.savePDF(batches, response);
		return ResponseEntity.status(HttpStatus.OK).body("pdf saved successessfully");
	}

}
