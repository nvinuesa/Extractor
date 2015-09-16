package org.gin.extractor.controller;

import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.gin.extractor.model.Extraction;
import org.gin.extractor.repository.ContrastRepository;
import org.gin.extractor.repository.RoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RestController
@RequestMapping("/api")
public class Controller {
	@Autowired
	private ContrastRepository repo;
	
	@Autowired
	private RoiRepository reporoi;

	@RequestMapping(method = RequestMethod.GET)
	/*
	 This is the first GET controller. When the site (index.html) and more particularly AngularJS in that site ask for the components,
	 they will do it as a GET .../api. That is why this method answers with an object containing all the information needed in the front-end
	 to fill the components, lists, checkboxes, etc.
	 */
	public List<Extraction> findContrast() {
		List<Extraction> listExtraction = new ArrayList<Extraction>();
		List<String> listAtlas = repo.findAllAtlas();		
		for (int i = 0; i < listAtlas.size(); i++) {
			Extraction extraction = new Extraction();
			extraction.setId(i);
			extraction.setAtlas(listAtlas.get(i));			
			List<String> listANATcon = repo.findConByContypeAndAtlas("ANAT",listAtlas.get(i));
			List<String> listBOLDcon = repo.findConByContypeAndAtlas("BOLD",listAtlas.get(i));
			List<String> listCORRANATcon = repo.findConByContypeAndAtlas("CORRANAT",listAtlas.get(i));
			List<String> listCORRBOLDcon = repo.findConByContypeAndAtlas("CORRBOLD",listAtlas.get(i));
			extraction.setanatcon(listANATcon);
			extraction.setboldcon(listBOLDcon);
			extraction.setcorranatcon(listCORRANATcon);
			extraction.setcorrboldcon(listCORRBOLDcon);
			List<String> listRoi = reporoi.findRoiByAtlas(listAtlas.get(i));
			extraction.setRoi(listRoi);
			List<String> listRoiAsym = reporoi.findRoiAsymByAtlas(listAtlas.get(i));
			extraction.setRoiAsym(listRoiAsym);
			List<Integer> listLaterality = reporoi.findLateralityByAtlas(listAtlas.get(i));
			extraction.setLaterality(listLaterality);
			extraction.setCustom("");
			extraction.setCustomname("");
			extraction.setAsymmetry(false);
			List<String> listbisroi = new ArrayList<String>();
			extraction.setbisroi(listbisroi);
			listExtraction.add(extraction);
		}		
		/*
		 Here we see the object that is returned. In this case it is a list because a priori we don't know how many atlases we will find
		 in the database. Each element in the list is an atlas, containing its own contrasts and ROI's.
		 */
		return listExtraction;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	/*
	 This is the only POST method. After submitting the form provided by AngularJS in index.html, all we do is a POST to .../api with the JSON information
	 corresponding the picks of the user. That information is processed here and sent to the processing algorithm (in this case matlab).
	 This method also responds with some information which is crucial for multi-user purposes. The id returned here is used later to identify
	 the correct file to be served to the client. Without this id all the files would mix-up.
	 */
	public String addItem(@RequestBody Extraction extraction) throws JsonGenerationException, JsonMappingException, IOException, InterruptedException {

		
//		Use the date to name the .json file (not to mix for multi-user purposes).
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonFilePath = "/usr/local/extractor/data_tmp/";
		String jsonFileId = format.format(date);
		String jsonFileName = jsonFileId + ".json";
		File jsonFile = new File(jsonFilePath+jsonFileName);
		ow.writeValue(jsonFile, extraction);		
		
		String commandMatlab = "/usr/local/extractor/bin/run_extractor.sh";
		String MCRlocation = "/usr/local/extractor/MATLAB/MCR/v7141";
	
		ProcessBuilder pb =
				new ProcessBuilder(commandMatlab, MCRlocation, jsonFilePath+jsonFileName);
//		Map<String, String> env = pb.environment();
//		env.put("VAR1", "myValue");
//		env.remove("OTHERVAR");
//		env.put("VAR2", env.get("VAR1") + "suffix");		
		pb.directory(new File("/usr/local/extractor/data_tmp"));
		File log = new File("log");
		pb.redirectErrorStream(true);
		pb.redirectOutput(Redirect.appendTo(log));
		Process p = pb.start();
		assert pb.redirectInput() == Redirect.PIPE;
		assert pb.redirectOutput().file() == log;
		assert p.getInputStream().read() == -1;
		
		p.waitFor();
		
//		jsonFile.delete();
		
        return "{\"id\":\" " + jsonFileId + "\"}";
	}
	
	@RequestMapping(value = "/downloadFile/{id}", method = RequestMethod.GET)
	/*
	 The last GET method, it only ask for one parameter (id) and responds with a csv file downloadable from the brower by the client.
	 This file is correctly identified in the server using (id).
	 */
    public void downloadCSV(@PathVariable String id, HttpServletResponse response) throws IOException {
 
        String csvFileName = "/usr/local/extractor/data_tmp/"+id+".csv";
 
        response.setContentType("text/csv");
 
        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        FileReader inputStream = null;
        PrintWriter writer = response.getWriter();
        try {
            inputStream = new FileReader(csvFileName);

            int c;
            while ((c = inputStream.read()) != -1) {
                writer.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (writer != null) {
                writer.close();
            }
        } 
        File csvFile = new File(csvFileName);
        csvFile.delete();
    }	
}
