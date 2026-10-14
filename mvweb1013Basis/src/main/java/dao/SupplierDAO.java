package dao;

import java.util.List;

public interface SupplierDAO {
	int insert(Supplier s);
	int update(Supplier s);
	int delete(int supId);
	List<Supplier> findAll();
	Supplier findById(int supId);
}
