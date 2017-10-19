package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Ms;

public interface MsRepository extends JpaNameRepository<Ms>, JpaSpecificationExecutor<Ms> {
	
	@Query("SELECT ms.name FROM Ms ms")
	List<String> findAllMsNames();

}
