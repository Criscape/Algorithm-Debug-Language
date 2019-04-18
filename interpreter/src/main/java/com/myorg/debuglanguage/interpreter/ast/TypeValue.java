package com.myorg.debuglanguage.interpreter.ast;

public class TypeValue {
	
	private String dataType;
	private String ioType;
	private Object value;
	private int vectorSize;
	
	public TypeValue(String dataType, String ioType, Object value) {
		super();
		this.dataType = dataType;
		this.ioType = ioType;
		this.value = value;
	}

	public String getDataType() {
		return dataType;
	}

	public String getIoType() {
		return ioType;
	}

	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value){
		this.value = value;
	}

	public int getVectorSize() {
		return vectorSize;
	}

	public void setVectorSize(int vectorSize) {
		this.vectorSize = vectorSize;
	}
	
}
