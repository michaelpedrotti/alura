package xyz.projections;

import java.time.LocalDate;

public interface EmployeProjection {

	Integer getId();
	String getName();
	LocalDate getCreateAt();
}
