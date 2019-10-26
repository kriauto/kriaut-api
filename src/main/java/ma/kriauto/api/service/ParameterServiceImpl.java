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

	@Override
	public void completeParameter(Parameter parameterin, Parameter parameterout) {
		parameterout.setTechcontrol(parameterin.getTechcontrol() != null ? parameterin.getTechcontrol() : parameterout.getTechcontrol());
		parameterout.setEmptyingkm(parameterin.getEmptyingkm() != null ? parameterin.getEmptyingkm() : parameterout.getEmptyingkm());
		parameterout.setInsuranceend(parameterin.getInsuranceend() != null ? parameterin.getInsuranceend() : parameterout.getInsuranceend());
		parameterout.setCirculationend(parameterin.getCirculationend() != null ? parameterin.getCirculationend() : parameterout.getCirculationend());
		parameterout.setMaxspeed(parameterin.getMaxspeed() != null ? parameterin.getMaxspeed() : parameterout.getMaxspeed());
		parameterout.setMaxcourse(parameterin.getMaxcourse() != null ? parameterin.getMaxcourse() : parameterout.getMaxcourse());
		parameterout.setMaxcarburantpri(parameterin.getMaxcarburantpri() != null ? parameterin.getMaxcarburantpri() : parameterout.getMaxcarburantpri());
		parameterout.setMincarburantpri(parameterin.getMincarburantpri() != null ? parameterin.getMincarburantpri() : parameterout.getMincarburantpri());
		parameterout.setMaxcarburantsec(parameterin.getMaxcarburantsec() != null ? parameterin.getMaxcarburantsec() : parameterout.getMaxcarburantsec());
		parameterout.setMincarburantsec(parameterin.getMincarburantsec() != null ? parameterin.getMincarburantsec() : parameterout.getMincarburantsec());
		parameterout.setMaxtempengine(parameterin.getMaxtempengine() != null ? parameterin.getMaxtempengine() : parameterout.getMaxtempengine());
		parameterout.setMintempengine(parameterin.getMintempengine() != null ? parameterin.getMintempengine() : parameterout.getMintempengine());
		parameterout.setMaxtempfrigot(parameterin.getMaxtempfrigot() != null ? parameterin.getMaxtempfrigot() : parameterout.getMaxtempfrigot());
		parameterout.setMintempfrigot(parameterin.getMaxtempfrigot() != null ? parameterin.getMaxtempfrigot() : parameterout.getMaxtempfrigot());
	}

	@Override
	public Parameter fetchParameterById(Long id) {
		return parameterRepository.fetchParameterById(id);
	}

}
