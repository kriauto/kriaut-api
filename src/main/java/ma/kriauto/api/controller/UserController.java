package ma.kriauto.api.controller;


import ma.kriauto.api.exception.ResourceNotFoundException;
import ma.kriauto.api.model.Device;
import ma.kriauto.api.model.User;
import ma.kriauto.api.service.DeviceService;
import ma.kriauto.api.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController { 
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private DeviceService deviceService;
    
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
      logger.info("--> Start Create User");
  	  logger.debug("--> User : "+user);
  	  String token = userService.generateToken(user);
  	  user.setToken(token);
  	  logger.info("--> End Create User");
      return userService.saveUser(user);
    }
    
    @PostMapping("/login/{identifier}")
    public User login(@PathVariable String identifier, @RequestBody User user) {
      logger.info("--> Start login");
  	  logger.debug("--> User : "+user+" Identifier : "+identifier);
  	  User current = userService.fetchUserByLogin(user.getLogin());
  	  if(null == current){
		throw new ResourceNotFoundException("User not found with id ");
	  }else if(!current.getPassword().equals(user.getPassword())){
		throw new ResourceNotFoundException("Missing password ");
	  }else if(null == identifier){
		throw new ResourceNotFoundException("Identifier Required");
	  }else{
		Device device = new Device();
		if(null == deviceService.fetchDeviceByIdentifier(identifier)){
		  device.setUserid(current.getId());
		  device.setIdentifier(identifier);
		  deviceService.save(device);
		}
	  }
  	  logger.info("--> End login");
      return current;
    }
    
    @PostMapping("/logout/{identifier}")
    public void logout(@PathVariable String identifier, @RequestBody User user) {
      logger.info("--> Start logout");
  	  logger.debug("--> User : "+user+" Identifier : "+identifier);
  	  User current = userService.fetchUserByLogin(user.getLogin());
  	  Device device = deviceService.fetchDeviceByIdentifier(identifier);
  	  if(null == current){
		throw new ResourceNotFoundException("User not found with id");
	  }else if(null == device){
		throw new ResourceNotFoundException("Device not found");
	  }else if(!device.getUserid().equals(current.getId())){
		throw new ResourceNotFoundException("Identifier Missing");
	  }else{
		deviceService.delete(device);
	  }
  	  logger.info("--> End logout");
    }

//    @GetMapping("/Users")
//    public List<User> getUsers() {
//    	logger.info("--> Start getUsers");
//        return (List<User>) userService.findAll();
//    }


//    @PostMapping("/Users")
//    public User createUser(@RequestBody User user) {
//    	logger.info("--> Start Create User");
//    	logger.info("--> User : "+user);
//        return userRepository.save(user);
//    }

//    @PutMapping("/Users/{UserId}")
//    public User updateUser(@PathVariable Long UserId, @Valid @RequestBody User UserRequest) {
//    	logger.info("--> Start updateUser");
//    	logger.info("--> User : "+UserRequest);
//    	User current = userService.fetchUserById(UserId);
//    	if(null == current){
//    		throw new ResourceNotFoundException("User not found with id " + UserId);
//    	}else{
//    		//current = userService.save(current);
//    	}
//        return current;
//    }


//    @DeleteMapping("/Users/{UserId}") 
//    public ResponseEntity<?> deleteUser(@PathVariable Long UserId) {
//    	logger.info("--> Start deleteUser");
//    	logger.info("--> User : "+UserId);
//        return userRepository.findById(UserId)
//                .map(User -> {
//                    userRepository.delete(User);
//                    return ResponseEntity.ok().build();
//                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + UserId));
//    }
}
