package pe.edu.upc.ejemplo.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.ejemplo.entities.BudgeTrip;
import pe.edu.upc.ejemplo.repositories.IBudgeTripRepository;
import pe.edu.upc.ejemplo.serviceinterface.IBudgetripService;
@Service
public class BudgetripServicelmpl implements IBudgetripService{
	@Autowired
	private IBudgeTripRepository budgetripRepository;

	@Override
	public void insert(BudgeTrip budgetrip) {
		// TODO Auto-generated method stub
		budgetripRepository.save(budgetrip);
	}

	@Override
	public List<BudgeTrip> list() {
		// TODO Auto-generated method stub
		return budgetripRepository.findAll();
	}

	@Override
	public void delete(int idBudgetrip) {
		// TODO Auto-generated method stub
		budgetripRepository.deleteById(idBudgetrip);
	}

	@Override
	public Optional<BudgeTrip> listId(int idBudgetrip) {
		// TODO Auto-generated method stub
		return budgetripRepository.findById(idBudgetrip);
	}

	@Override
	public void update(BudgeTrip budgetrip) {
		// TODO Auto-generated method stub
		budgetripRepository.save(budgetrip);
	}
}
