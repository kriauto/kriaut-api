package ma.kriauto.api.controller;

import ma.kriauto.api.common.ErrorLabel;
import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Parameter;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.service.CarService;
import ma.kriauto.api.service.ParameterService;
import ma.kriauto.api.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ParameterController {
	
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private CarService carService;
	
	@CrossOrigin
    @PostMapping("/updateparameter")
    public ResponseEntity<?> updateparameter(@RequestHeader(value="Authorization") String authorization, @RequestBody Parameter parameter) {
      log.info("-- Start Update Parameters :  "+parameter);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  Parameter currentparameter = parameterService.fetchParameterById(parameter.getId());
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!current.getIsActive()){
		  return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_ACTIVE),HttpStatus.NOT_FOUND);
	  }
  	  Parameter param = parameterService.fetchParameterById(parameter.getId());
  	  Car car = carService.fetchCarById(param.getCarid());
  	  parameterService.completeParameter(parameter, currentparameter);
  	  parameterService.save(currentparameter);
  	  car.setDailydistance(0.0);
  	  car.setIndexemptyingkm(1);
  	  carService.save(car);
	  log.info("-- End   Update Parameters --");
  	  return new ResponseEntity(new CustomErrorType(ErrorLabel.DATA_SAVED),HttpStatus.OK);
    }
}
