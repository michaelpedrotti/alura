package xyz.entities;

import java.io.Serializable;

import com.google.gson.Gson;

public abstract class AbstractEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {

		return (new Gson()).toJson(this);
	}
}
