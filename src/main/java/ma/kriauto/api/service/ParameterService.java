package ma.kriauto.api.service;

import ma.kriauto.api.model.Parameter;

public interface ParameterService {
	
    public Parameter fetchParameterByCarId(Long carid);
	public Parameter save(Parameter parameter);
    public void completeParameter(Parameter parameterin, Parameter parameterout);
    public Parameter fetchParameterById(Long id);
}
