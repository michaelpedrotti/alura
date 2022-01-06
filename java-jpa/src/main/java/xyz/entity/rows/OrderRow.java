package xyz.entity.rows;

import java.time.LocalDateTime;

public class OrderRow {

	private String name;
	private Long total;
	private LocalDateTime lastSellDate;
	
	public OrderRow(String name, Long total, LocalDateTime lastSellDate) {
		super();
		this.name = name;
		this.total = total;
		this.lastSellDate = lastSellDate;
	}

	public String getName() {
		return name;
	}

	public Long getTotal() {
		return total;
	}

	public LocalDateTime getLastSellDate() {
		return lastSellDate;
	}
	
	
}
