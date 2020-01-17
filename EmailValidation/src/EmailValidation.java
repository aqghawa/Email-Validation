
public class EmailValidation {
	
	public static boolean isAlphanumeric(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidPrefixChar(char c){
		if(isAlphanumeric(c) || c == '_' || c == '.' || c == '-') {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static boolean isValidDomainChar(char c) {
		if(isAlphanumeric(c) || c == '.' || c == '-') {
			return true;
		} else {
			return false;
		}
	}

	public static boolean exactlyOneAt(String email) {
		int count = 0;
		for(int i=0; i<email.length();i++) {
			if(email.charAt(i) == '@') {
				count++;
			}
		}
		if (count==1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getPrefix(String email) {
		String prefix = "";
		for(int i=0; i<email.length();i++) {
			if(email.charAt(i) == '@') {
				break;
			}
			prefix = prefix + email.charAt(i);
			
		}
		return prefix;
	}
	
	public static String getDomain(String email) {
		String domain = "";
		for(int i=(email.length()-1); i>=0;i--) {
			if(email.charAt(i) == '@') {
				break;
			}
			domain = email.charAt(i) + domain;
			
		}
		return domain;
	}
	
	public static boolean isValidPrefix(String prefix) {
		if(prefix.length() < 1) {
			return false;
		} if(isAlphanumeric(prefix.charAt(0))== false) {
			return false;
		}
		for(int i =0; i<prefix.length();i++) {
			if(isValidPrefixChar(prefix.charAt(i))== false) {
				return false;
			} if((prefix.charAt(i) == '.' || prefix.charAt(i) == '-' || prefix.charAt(i) == '_') && (!(isAlphanumeric(prefix.charAt(i+1))))) {
				return false;
			}
		} 
		return true;
	}
	
	public static String getP2(String domain) {
		String p2 = "";
		for(int i=(domain.length()-1); i>=0;i--) {
			if(domain.charAt(i) == '.') {
				break;
			}
			p2 = domain.charAt(i) + p2;
			
		}
		return p2;
	}
	
	public static boolean isValidDomain(String domain) {
	//Since i can get the second portion easily, and the requirements for the first portion is more general, I will validate p2 and then p1	
		//check that length is at least 2 for p2
		String p2 = getP2(domain);
		if(p2.length()<2) {
			return false;
		}
		//check that only alphabet is in p2
		for(int i = 0; i<p2.length(); i++) {
			if(!((p2.charAt(i) >= 'a' && p2.charAt(i) <= 'z'))) {
				return false;
			}
		}
		//check first portion has one character
		if(domain.length()-(p2.length()+1)<1) {
			return false;
		}
		//check first portion is alphanumeric and after every period or dash there is an alphanumeric letter
		for(int j = 0; j<domain.length(); j++) {
			if(!isValidDomainChar(domain.charAt(j))) {
				return false;
			}
			if((domain.charAt(j) == '.' || domain.charAt(j) == '-') && (!(isAlphanumeric(domain.charAt(j+1))))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidEmail(String email) {
		String prefix = getPrefix(email);
		String domain = getDomain(email);
		if(exactlyOneAt(email) && isValidPrefix(prefix) && isValidDomain(domain)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(isValidEmail("shalsibaai@gmail.com"));
	}

}
