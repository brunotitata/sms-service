package br.com.sms.login.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.login.exception.Sha512Exception;

public class Utils {

    public static void argumentNotEmpty(String field, String errMessage) {
	if (field == null || field.isEmpty())
	    throw new ArgumentInvalidException(errMessage);
    }

    public static void argumentNotNull(Object obj, String errMessage) {
	if (obj == null)
	    throw new ArgumentInvalidException(errMessage);
    }

    public static String getSHA512(String input) {

	String toReturn = null;
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-512");
	    digest.reset();
	    digest.update(input.getBytes("utf8"));
	    toReturn = String.format("%040x", new BigInteger(1, digest.digest()));
	} catch (Exception e) {
	    throw new Sha512Exception("Error ao gerar SHA512." + e.getMessage());
	}

	return toReturn;
    }

    public static String checkCharactersCellPhone(String cellphone) {

	if (!cellphone.matches("\\d+"))
	    throw new ArgumentInvalidException(
		    "Numero de telefone deve ter apenas numeros, sem letras ou caracteres especiais.");

	return cellphone;
    }

}
