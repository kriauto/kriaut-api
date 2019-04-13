package ma.kriauto.api.service;

import ma.kriauto.api.model.Parameter;
import ma.kriauto.api.repository.ParameterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterServiceImpl implements  ParameterService  {
	
	@Autowired
    private ParameterRepository parameterRepository;

	@Override
	public Parameter fetchParameterByCarId(Long carid) {
		return parameterRepository.fetchParameterByCarId(carid);
	}

	@Override
	public Parameter save(Parameter parameter) {
		return parameterRepository.save(parameter);
	}

}
