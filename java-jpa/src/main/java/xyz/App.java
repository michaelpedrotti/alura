package xyz;

import java.math.BigDecimal;
import java.util.List;

import xyz.entity.ClientEntity;
import xyz.entity.OrderEntity;
import xyz.entity.OrderItemEntity;
import xyz.entity.ProductEntity;
import xyz.entity.rows.OrderRow;
import xyz.repository.ClientRepository;
import xyz.repository.OrderRepository;
import xyz.repository.ProductRepository;

public class App {

	public static void insert() {
		
		System.out.println("app:insert");
		
//		ProductRepository repo = ProductRepository.newInstance();
//		CategoriesRepository repo = CategoriesRepository.newInstance();
		ClientRepository repo = ClientRepository.newInstance();
		
		repo.beginTransaction();
		
		try {

		
//			ProductEntity entity = ProductEntity.newInstance()
//				.setName("Samsung Galaxy A11")
//				.setDescription("Android v11")
//				.setPrice(new BigDecimal(800.00))
//				.setCategory(CategoriesRepository.newInstance().findOrNew(2L).setName("Phone"))
//				.setCreatedAt(LocalDateTime.now());
//
//			CategoryEntity entity = CategoryEntity.newInstance().setName("Hello World");
			
			ClientEntity entity = ClientEntity.newInstance()
				.setName("Beltrano")
				.setDoc("888877776");
			
			repo.save(entity);
			repo.commit();			
		}
		catch(Exception e) {

			repo.rollback();
			e.printStackTrace();
		}
		finally {
			
			repo.close();
		}
	}
	
	
	public static void fetch() {
		
		System.out.println("app:fetch");
		
//		List<CategoryEntity> rows = CategoriesRepository.newInstance().all();
//		List<ProductEntity> rows = ProductRepository.newInstance().fetchCategoryName("phone");
//		List<ProductEntity> rows = ProductRepository.newInstance().fetchCategoryName2("phone");
//		
//		System.out.println("total: " + rows.size());
//		
//		rows.forEach(row -> System.out.println(row.getName()));

//		OrderEntity entity = OrderRepository.newInstance().fetchWithClient(3L);
		
		List<ProductEntity> rows = ProductRepository.newInstance().fetch(null, new BigDecimal(800.00), null);
		
		// Cannot use Gson with Lazy Load
//		Gson gson = new Gson();
		
	
		for(ProductEntity row : rows) {
			
//			System.out.println(gson.toJson(row));
			
			System.out.println(row);
		}
	}
	
	public static void find() {
		
		BigDecimal price = ProductRepository.newInstance().findPriceById(1l);
		
		System.out.println("price: " + price);
	}
	
	public static void order() {
		
		System.out.println("app:order");
		
		ClientRepository repo = ClientRepository.newInstance();
		
		repo.beginTransaction();
		
		try {
			
			ClientEntity client = repo.findOrNew(1L).fill("Michael", "123456789");			
			repo.save(client);
						
			OrderItemEntity item1 = OrderItemEntity.newInstance().fill(
				ProductRepository.newInstance().findOrNew(1L)
			);
			
			OrderItemEntity item2 = OrderItemEntity.newInstance().fill(
					ProductRepository.newInstance().findOrNew(2L)
				);
			
			OrderEntity order = OrderEntity.newInstance()
				.setClient(client)
				.addItem(item1)
				.addItem(item2);
				
			OrderRepository.newInstance().save(order);
				
			repo.commit();
			

			System.out.println("order: " + order.getId());
		}
		catch(Exception e) {
			
			e.printStackTrace();
			repo.rollback();
		}
		finally {
			
			repo.close();
		}
	}
	
	private static void sum() {
		
		OrderRepository repo = OrderRepository.newInstance();

		System.out.println("sum:total: " + repo.sum());
		
//		List<Object[]> score = repo.score();
		
//		for(Object[] row : repo.score()) {
//						
//			System.out.format("product: %s, total: %s, last sell: %s\n", row[0], row[1], row[2]);
//		}
		
		
		for(OrderRow row : repo.score2()) {
			
			System.out.format("product: %s, total: %s, last sell: %s\n", row.getName(), row.getTotal(), row.getLastSellDate());
		}
	}
	
	public static void main(String[] args) {

		System.out.println("app:startup");
		
		insert();
//		fetch();
//		find();
//		order();
//		sum();
		
		System.out.println("app:finish");
	}
}
