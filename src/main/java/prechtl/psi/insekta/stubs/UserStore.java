
package prechtl.psi.insekta.stubs;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import prechtl.psi.insekta.ex.NotFound;


/**
 *
 * @author Mike Prechtl
 */
@ApplicationScoped
public class UserStore {

    private static final Map<String, UserInformation> USER_STORE = new HashMap() {
	{
	    put("9e78de73", new UserInformation("test@test.com",
		    "9E78DE733C6A51C0CC954C1D956D8929AD1310513E1042D81EDC375219C6A2EF"));
	}
    };

    public boolean isValidUser(String email, String password) {
	Optional<Map.Entry<String, UserInformation>> userInfo = findUser(email, password);
	return userInfo.isPresent();
    }

    public String getUserId(String email, String password) {
	Optional<Map.Entry<String, UserInformation>> userInfo = findUser(email, password);
	if (userInfo.isPresent()) {
	    return userInfo.get().getKey();
	} else {
	    throw new NotFound("User not found!");
	}
    }

    private Optional<Map.Entry<String, UserInformation>> findUser(String email, String password) {
	Optional<Map.Entry<String, UserInformation>> userInfo = USER_STORE.entrySet().stream()
		.filter(entry -> entry.getValue().getEmail().equals(email)
			// ignore case, because hash value is used ;)
			&& entry.getValue().getPassword().equalsIgnoreCase(password))
		 .findAny();
	return userInfo;
    }

    public static class UserInformation {

	private final String email;
	private final String password;

	public UserInformation(String email, String password) {
	    this.email = email;
	    this.password = password;
	}

	public String getEmail() {
	    return email;
	}

	public String getPassword() {
	    return password;
	}

    }

}
