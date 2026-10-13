package dao;

import java.util.List;

public interface CoffeeDAO {
	int insert(Coffee c);
	int update(Coffee c);
//	Coffee delete(String name);//soft delete 軟刪除=>有些企業會做軟刪除，不是真正的刪除，會保留
	int delete(String name);
	List<Coffee> findAll();
	Coffee findByName(String name);
}
