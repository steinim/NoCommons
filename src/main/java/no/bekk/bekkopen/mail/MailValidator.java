package no.bekk.bekkopen.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.bekk.bekkopen.common.StringNumberValidator;

/**
 * Validates Postnummer and Poststed objects.
 * 
 * @author per k. mengshoel
 */
public class MailValidator extends StringNumberValidator {

	private static final int POSTNUMMER_LENGTH = 4;
	private static Map<?, ?> poststedMap = new HashMap<Object, Object>();
	private static Map<?, ?> postnummerMap = new HashMap<Object, Object>();

	public static void setPostnummerMap(Map<?, ?> aPostnummerMap) {
		MailValidator.postnummerMap = aPostnummerMap;
	}

	public static void setPoststedMap(Map<?, ?> aPoststedMap) {
		MailValidator.poststedMap = new HashMap<Object, Object>(aPoststedMap);
	}

	public static int getAntallPoststed() {
		int size = poststedMap.size();
		return size;
	}

	public static int getAntallPostnummer() {
		int size = postnummerMap.size();
		return size;
	}

	public static boolean isValidPostnummer(String postnummer) {
		try {
			MailValidator.getPostnummer(postnummer);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public static Postnummer getPostnummer(String postnummer) {
		validateSyntax(postnummer);
		return new Postnummer(postnummer);
	}

	public static List<?> getPostnummerForPoststed(String string) {
		Poststed p = new Poststed(string);
		List<?> postnummerList = (List<?>) poststedMap.get(p);
		return (postnummerList == null ? new ArrayList<Object>() : postnummerList);
	}

	private static void validateSyntax(String postnummer) {
		validateLengthAndAllDigits(postnummer, POSTNUMMER_LENGTH);
	}

	public static Poststed getPoststedForPostnummer(String postnummer) {
		Poststed result = null;
		Postnummer pn = getPostnummer(postnummer);
		if (postnummerMap.containsKey(pn)) {
			result = (Poststed) postnummerMap.get(pn);
		}
		return result;
	}

}
