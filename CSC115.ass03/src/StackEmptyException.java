/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jason Donald
 */
public class StackEmptyException extends RuntimeException{
	public StackEmptyException(){
		super();
	}
	
	public StackEmptyException(String msg){
		super(msg);
	}
}