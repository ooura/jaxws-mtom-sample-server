package com.example.jaxws.service;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.example.jaxws.error.FileNotFound;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface FileTransferer {

	@WebMethod
	void upload(
			@WebParam(name = "dh") DataHandler dh,
			@WebParam(name = "fileName") String fileName
			) throws Exception;

	@WebMethod
	DataHandler download(
			@WebParam(name = "fileName") String fileName
			) throws FileNotFound, Exception;

}
