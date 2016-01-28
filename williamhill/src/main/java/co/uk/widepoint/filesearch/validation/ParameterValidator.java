package co.uk.widepoint.filesearch.validation;
/**
 * 
 * Interface to define a method to be used for validating command line arguments
 * 
 * 
 * @author David Harvey
 * 
 * Copyright Widepoint Computer Services Ltd 2016
 *
 */
public interface ParameterValidator
{
	
	public void validateParameters(String [] args) throws IllegalArgumentException;
	

}
